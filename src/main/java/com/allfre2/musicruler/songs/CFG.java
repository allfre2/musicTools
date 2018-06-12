package com.allfre2.musicruler.songs;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
import java.util.HashMap;
import java.util.Random;

/**
 * Implements a simple lexer and parser for context free grammars.
 * <p>
 *  It has or (|), asingment (=), parentesis and semicolon operators
 * for writting CFGs.
 * It is intended to be used with {@link Markov<T>} to generate
 * text for certain types of poem and with {@link AbstractKey} implementations
 * to generate harmony for songs.
 */
public class CFG{
    
    public static final String Identifier = "^\\w+$";

    public static final String Production = "=";
    public static final String Or = "|";
    public static final String OpenParen = "(";
    public static final String CloseParen = ")";
    public static final String End = ";";
    public static final String Space = " ";
    public static final String Empty = "^"; // To make optional rules ???
    public static final String λ = "";
    public static final String Exp = "x";

    public final Random rnd = new Random();

    protected HashMap<String, List<Node>> grammar;

 /**
  * Class Constructor
  * @param cfg specifies a context free grammar according to the syntax
  * defined above.
  * @throws InvalidCFGException
  * @see InvalidCFGException
  */
  public CFG(String cfg) throws InvalidCFGException{

     grammar = new HashMap<>();

     cfg = format(cfg);

     List<List<String>> productions = lexer(cfg);

     validate(productions);

     List<Node> nodes;
     
     for(List<String> production: productions){

      Node node = new Node(production);
      String sym = node.getSymbol();

      if(grammar.containsKey(sym)){
        nodes = grammar.get(sym);
        nodes.add(node);
      }else{
        nodes = new ArrayList<>();
        nodes.add(node);
        grammar.put(sym, nodes);
      }
     }
     printGrammar();
  }

    boolean isIdentifier(String token){
     return token.matches(Identifier);
    }

   /**
    * Validates the produtions in the grammar. Checks for unbalanced parentesis,
    * faulty or expressions and foreign tokens.
    * @param  productions a List of Lists of Strings containing the tokens
    * extrated with the lexer method.
    * @throws InvalidCFGException
    */
    public void validate(List<List<String>> productions)
     throws InvalidCFGException
    {
      for(List<String> production: productions){

        int line, index;
        
        line = productions.indexOf(production);

       if(production.size() < 3 || !(isIdentifier(production.get(0))) ||
        !(production.get(1).equals(Production)) || production.get(2).equals(Or) ||
          production.get(production.size()-1).equals(Or))

         throw new InvalidCFGException("Invalid production", line, 0);

       int equalsOp = 0, orOperators = 0;
       for(String token: production){

        index = production.indexOf(token);

        if(token.equals(Production)) ++equalsOp;
        if(equalsOp > 1)
          throw new InvalidCFGException("Multiple asignments in production", line, index);

        if(token.equals(Or)) ++orOperators;
        else orOperators = 0;
        if(orOperators > 1)
          throw new InvalidCFGException("Consecutive Or operators", line, index);
       }
      }
    }

   /**
    * Removes extra whitespace, newlines. Separates operators from
    * identifiers to make the lexer's job easier.
    * @param cfg The context free grammar string to be formated.
    * @return returns the formated cfg ready for the lexer method.
    */
    public String format(String cfg){
      String[] operators =
      {
       Production, "\\"+Or, "\\"+OpenParen,
       "\\"+CloseParen, Empty, End
      };
                       
      cfg = cfg.replaceAll("\n+", Space);
      cfg = cfg.replaceAll(End + "+", End);

       // FIX! for some cases this loop doesn't do the job with
       // one pass ... :/
      int i = 0;
       String prev = "";
       while(!prev.equals(cfg)){
        for(String s: operators){
         prev = new String(cfg);
         cfg =
          cfg.replaceAll("(.)("+s+")(.)", "$1"+Space+"$2"+Space+"$3");
         cfg = cfg.replaceAll(" +", Space);

         ++i;
        }
       }
      return cfg;
    }

   /**
    * Scans through the cfg String and splits it into a list of String tokens.
    * It builds a List of tokens for each production. (Line containing an = operator).
    * @param cfg The context free grammar String.
    * @return returns a List of Lists of Strings that correspond to the tokens
    * extracted from the cfg String passed to the constructor.
    */
    public List<List<String>> lexer(String cfg){
     List<String> lines = Arrays.asList(cfg.split(End));
     return
      lines.stream()
           .map(s -> Arrays.asList(s.split(Space)))
           .map(s ->
            s.stream()
             .filter(str -> !(str.isEmpty() || str.equals(Space)))
             .collect(Collectors.toList()))
           .collect(Collectors.toList());
    }

   /**
    * Recursively generates a String belonging to the grammar.
    * It first fetches a token that matches the start symbol from the
    * parse tree and calls genRandom with a Node object as argument.
    * @param start The start symbol for production.
    * @param depth The maximum number of recursive calls to be made.
    * @return returns a randomly generated String that fits the already parsed grammar.
    */
    public String genRandom(String start, int depth){

     if(depth < 1) return "";

     List<Node> options;

     String sentence = start;
     String next;

     while(depth-- > 0){
      next = "";
      for(String token: sentence.split(Space)){
        if(grammar.containsKey(token)){
          options = grammar.get(token);
          next += genRandom(options.get(rnd.nextInt(options.size()))) + Space;
        }else next += token + Space;
      }
      sentence = next.replaceAll(" +", " ");
     }
     return sentence;
    }

   /**
    * Recursively generates a String belonging to the grammar.
    * It start from a {@link CFG.Node} object previously created by the parser.
    * @param node The node object that is going to be expanded by recursively
    * calling the method on it's children nodes.
    * @return returns a randomly generated String that fits the already parsed grammar.
    */
    public String genRandom(Node node){
     List<Node> children = node.getChildren();

     if(children == null || children.isEmpty())
      return node.getSymbol();

     String str = "";

     if(node.getType().equals(Or))
      str += genRandom(children.get(rnd.nextInt(children.size())));
     else
       for(Node n: children)
        str += genRandom(n) + Space;

     return str;
    }

   /**
    * Prints the set of productions that conform the already parsed grammar.
    */
    public void printGrammar(){
     System.out.println("\n\n== Complete Parsed Grammar ==\n");
     for(String key: grammar.keySet())
        for(Node n: grammar.get(key))
            System.out.println("key:" + key + " -> " + n.getStr());

     System.out.println();
    }

   /**
    *
    * Class Node.
    * Implements a parse tree.
    * 
    */

    protected class Node{

      protected String type;
      protected String symbol;
      protected List<Node> children;
  
     /**
      * Class Contructor.
      * @param terminal A String containing a terminal token.
      */
      public Node(String terminal){
       this.type = λ;
       this.symbol = terminal;
       this.children = null;
      }
  
     /**
      * Class Contructor.
      * @param type Specifies the type of the Node to be created.
      * Types are Exp (Expresion), Or (Logical or expresion),
      * OpenParen (An expression enclosed in parentesis),
      * Empty (A way of expecifying a node that doesn't evaluate to nothing),
      * λ (An empty String, equivalent to `Empty`).
      * @param symbol Contains a String corresponding to an identifier.
      * In Nodes that contains a group of child nodes that must be evaluated
      * against some operator (Or) it has the operators symbol.
      * @param  children A list of child nodes.
      */
      public Node(String type, String symbol, List<Node> children){
        this.type = type;
        this.symbol = symbol;
        this.children = children;
      }

     /**
      * Class Contructor.
      * Calls the {@link Node#parse} method to build the parse tree for each production.
      * @param  production A List of String (tokens) that make a production. 
      * @throws InvalidCFGException
      */
      public Node(List<String> production) throws InvalidCFGException{
         this.type = Production;
         this.symbol = production.get(0);
         this.children = new ArrayList<>();
         this.children.add(parse(production.subList(2,production.size())));

      }

     /**
      * Used to get the terminal or non-terminal symbol representing the node.
      * @return returns the Symbol String for this node.
      */
      public String getSymbol(){
       return symbol.equals(Empty) ? "" : symbol;
      }

     /**
      * Used to get the type of the node.
      * @return returns the type field of the node.
      */
      public String getType(){
       return type;
      }

     /**
      * Used to get the children nodes of this node.
      * @return Returns the List of children nodes of this node.
      */
      public List<Node> getChildren(){
       return children;
      }

     /**
      * A recursive, hand written parser, that builds a parse tree
      * from a List of String tokens.
      * @return Returns Node that acts as the root of a parse
      * tree corresponding to the tokens passed as argument.
      * @param tokens The List of Strings (tokens) to be parsed.
      */
      Node parse(List<String> tokens)
       throws InvalidCFGException
      {
        String nodeType = Exp;
        if(tokens.get(0).equals(OpenParen) &&
            tokens.get(tokens.size()-1).equals(CloseParen))
            nodeType = OpenParen;

        List<Node> nodes = new ArrayList<>();
        
        for(int i = 0; i < tokens.size(); ++i){

         String token = tokens.get(i);

         if(token.equals(OpenParen)){
          int end = findMatch(tokens, i);

          if(end < 0 || (i+1 >= end))
           throw
            new InvalidCFGException("Unbalanced or empty `" + OpenParen + "` expression");
 
          nodes.add(parse(tokens.subList(i+1,end)));
          i = end;

         }else{
           nodes.add(new Node(token));
         }
        }

        Node orNode = new Node(Or);

        if(nodes.contains(orNode))
           return parseOrExpr(nodes);
        else
           return new Node(nodeType, λ, nodes);
      }

     /**
      * This method parses a List of Node objects and collapses
      * them into a single node if there are or operator in between.
      * @param nodes A List of Node that is to be parsed.
      * @return Returns the result of parsing a List of Node that
      * contains one or several Or operators
      */
      Node parseOrExpr(List<Node> nodes)
       throws InvalidCFGException
      {
        if(nodes.get(0).getSymbol().equals(Or) ||
           nodes.get(nodes.size()-1).getSymbol().equals(Or))
            throw new InvalidCFGException("Incomplete Or expression");

        Stack<Node> finalNodes = new Stack<>();
        Stack<Node> tmp = new Stack<>();

        int consecutive = 0;

        for(Node node: nodes){
          if(node.getSymbol().equals(Or)){
            tmp.push(finalNodes.pop());
            consecutive = 0;
          }else{
            if(consecutive > 0 && !tmp.isEmpty()){
              tmp.push(finalNodes.pop());
              finalNodes.push(new Node(Or, λ, new ArrayList<Node>(tmp)));
              tmp = new Stack<>();
              consecutive = 0;
            }
            finalNodes.push(node);
            consecutive++;
          }
         }

         if(!tmp.isEmpty()){
           tmp.push(finalNodes.pop());
           finalNodes.push(new Node(Or, λ, new ArrayList<Node>(tmp)));
         }

         if(finalNodes.size() > 1)
          return new Node(Exp, λ, new ArrayList<Node>(finalNodes));
         else
          return finalNodes.pop();
      }

     /**
      * Find matching parentesis to parse groups of tokens.
      * @param tokens List of String (token).
      * @param i the index of the opening parentesis.
      * @return Returns the index of the closing parentesis that matches
      * the i parameter in the tokens List.
      */
      int findMatch(List<String> tokens, int i){
        Stack<String> paren = new Stack<>();
        paren.push(tokens.get(i));

        while(++i < tokens.size() && !paren.isEmpty()){

          String token = tokens.get(i);

          if(token.equals(OpenParen))
            paren.push(OpenParen);
          else if(token.equals(CloseParen)){
            if(paren.isEmpty()){
             i = 0;
             break;
            }
            paren.pop();
          }
        }
       return i-1;
      }

     /**
      * Used to get a text based representation of a node's parse tree.
      * The Syntax is polish notation similar to that of lisp.
      * @return Returns a String of the node in prefix/polish notation.
      */
      public String getStr(){
       String str, prefix = "", postfix = "";

       if(!type.isEmpty()){
        prefix = "(";
        postfix = ")";
       }

       str = prefix+getSymbol()+(type == Exp ? "" : type);

       if(children != null && !children.isEmpty())
        for(Node node: children)
           str += (str.isEmpty() ? "" : " ")
                   + node.getStr();

        return str + postfix;
      }
  
      @Override
      public int hashCode(){
       return getSymbol().hashCode();
      }

      @Override
      public boolean equals(Object o){
       return getSymbol().equals(((Node)o).getSymbol());
      }

      @Override
      public String toString(){
          return getSymbol() + (type.isEmpty() ? "" : ("/" + this.type));
      }
    }
}
