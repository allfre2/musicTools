package com.allfre2.musicruler.utilities;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
import java.util.HashMap;
import java.util.Random;

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
    public static final String Exp = λ;

    public final Random rnd = new Random();

    protected HashMap<String, List<Node>> grammar;

  public CFG(String cfg) throws InvalidCFGException{

     grammar = new HashMap<>();

     cfg = format(cfg);

   List<List<String>> productions = lexer(cfg);

     validate(productions);

     List<Node> nodes;
     
     for(List<String> production: productions){

      System.out.println(production);
      Node node = new Node(production);
      System.out.println(node.getStr());

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

    public void validate(List<List<String>> productions)
     throws InvalidCFGException
    {
      for(List<String> production: productions){
       if(production.size() < 3 || !(isIdentifier(production.get(0))) ||
        !(production.get(1).equals(Production)) || production.get(2).equals(Or) ||
          production.get(production.size()-1).equals(Or))
       {
        System.out.println(production);
         throw new InvalidCFGException("Invalid production");
       }

       int equalsOp = 0;
       if(production.contains(Production))
        if(++equalsOp > 1)
           throw new InvalidCFGException("Multiple asignments in production");
      }
    }

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
      System.out.println("Did "+i+" Passes!");

      System.out.println("cfg after format:");
      System.out.println(cfg);

      return cfg;
    }

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

    public String genRandom(Node node){
     List<Node> children = node.getChildren();

     if(children == null || children.isEmpty())
      return node.getSymbol();

     String str = "";

     if(node.getType().equals(Or)){
      str += genRandom(children.get(rnd.nextInt(children.size())));
     }else{
       for(Node n: children){
        str += genRandom(n) + Space;
       }
     }
     return str;
    }

    public void printGrammar(){
     System.out.println("\n\n== Complete Parsed Grammar ==\n");
     for(String key: grammar.keySet()){
        for(Node n: grammar.get(key)){
            System.out.println("key:" + key + " -> " + n.getStr());
        }
     }
     System.out.println();
    }

    //     
    // Class Node
    // Implements a simple parser.
    //

    protected class Node{

      protected String type;
      protected String symbol;
      protected List<Node> children;
  
      public Node(String terminal){
       this.type = λ;
       this.symbol = terminal;
       this.children = null;
      }

      public Node(String type, String symbol, List<Node> children){
        this.type = type;
        this.symbol = symbol;
        this.children = children;
      }

      public Node(List<String> production) throws InvalidCFGException{

         this.type = Production;
         this.symbol = production.get(0);
         this.children = new ArrayList<>();
         this.children.add(parse(production.subList(2,production.size())));

      }

      public String getSymbol(){
       return symbol.equals(Empty) ? "" : symbol;
      }

      public String getType(){
       return type;
      }

      public List<Node> getChildren(){
       return children;
      }

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
         if(finalNodes.size() > 1){
          return new Node(Exp, λ, new ArrayList<Node>(finalNodes));
         }else return finalNodes.pop();
      }

      int findMatch(List<String> tokens, int i){
        Stack<String> paren = new Stack<>();
        paren.push(tokens.get(i));

        ++i;
        while(i < tokens.size() && !paren.isEmpty()){

          String token = tokens.get(i);

          if(token.equals(OpenParen))
              paren.push(OpenParen);
          else if(token.equals(CloseParen)){
            if(paren.isEmpty()){
             i = -1;
             break;
            }
              paren.pop();
          }
         ++i;
        }

       return paren.isEmpty() ? i-1: -1;
      }

      // Get a String of the node in prefix/polish notation
      public String getStr(){
       String str, prefix = "", postfix = "";

       if(!type.isEmpty()){
        prefix = "(";
        postfix = ")";
       }

       str = prefix+getSymbol()+type;

       if(children != null && !children.isEmpty())
        for(Node node: children)
           str += (str.isEmpty() ? "" : " ")
                   + node.getStr();

        str += postfix;

        return str;
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
