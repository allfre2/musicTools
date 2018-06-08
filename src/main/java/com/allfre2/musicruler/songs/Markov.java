package com.allfre2.musicruler.songs;

import java.util.Collections;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.Random;

public abstract class Markov<T>{

  protected Random rnd = new Random();
  protected List<Token<T>> tokens;
  protected List<Token<T>> tokenCount;
  protected List< HashMap<List<Token<T>>, List<Token<T>>> > markovTable;
  protected int order;

  public int getOrder(){
    return this.order;
  }

  public void setOrder(int order){
    this.order = order;
  }

  public void buildTable(List<Token<T>> tokens){

    System.out.println("Building Table ...");
    this.tokens = tokens;

    for(int i = 1; i < order; ++i)
      buildTable(i);
  }

  protected void buildTable(int order){
    if(tokens.size() < order+2)
     return; // throw ? give some more info

    if(markovTable == null)
     markovTable = new ArrayList<>();
    
    if(markovTable.size() < order)
      for(int i = markovTable.size(); i < order; ++i)
        markovTable.add(new HashMap<>());

    HashMap<List<Token<T>>, List<Token<T>>> table = markovTable.get(order-1);

    LinkedList<Token<T>> token = new LinkedList<>();
    List<Token<T>> adj;

    for(int i = 0; i < order; ++i)
      token.add(tokens.get(i));

    for(int i = order; i < tokens.size()-order-1; ++i){
     if(table.containsKey(token)){
      adj = table.get(token);

      if(adj.contains(tokens.get(i)))
        adj.get(adj.indexOf(tokens.get(i))).incr();
      else adj.add(new Token<T>(tokens.get(i)));

     }
     else{
      adj = new ArrayList<>();
      adj.add(new Token<T>(tokens.get(i)));

      table.put(new ArrayList<>(token), adj);
     }

     // Slide...
     token.removeFirst();
     token.add(new Token<T>(tokens.get(i)));
    }
  }

  public void addToTable(List<Token<T>> tokens){
    this.tokens.addAll(tokens);
    buildTable(order);
  }

  private Token<T> selectRandom(List<Token<T>> tokens){
   return tokens.get(rnd.nextInt(tokens.size()));
  }

  public Token<T> randomToken(){
   List<List<Token<T>>> tmp = new ArrayList<List<Token<T>>>(markovTable.get(0).keySet());

   List<Token<T>> keys = tmp.get(rnd.nextInt(tmp.size()));
   return selectRandom(keys);
  }

  public List<Token<T>> generate(int len){
    return generate(len, order, null);
  }

  // TODO use keyTokens, ending words, rhymes...
  public List<Token<T>> generate(int len, int order){
    System.out.println("Generating text of length " + len);
    return generate(len, order, null);
  }

  public List<Token<T>> generate(List<Token> firstWord, List<Token<T>> lastWord,
    List<Token<T>> keyTokens, int len){
    return null;
  }

  public List<Token<T>> generate(int len, int order, List<Token<T>> keyTokens){

   if(markovTable == null || markovTable.size() < order){
    for(int i = markovTable.size(); i <= order; ++i)
     buildTable(i);
    this.order = order;
   }

   List<Token<T>> result = new ArrayList<>();
   LinkedList<Token<T>> window = new LinkedList<>(); // Sliding window
   Token<T> start = randomToken();

   result.add(start);
   window.add(start);

   List<Token<T>> adj;
   Token<T> next;

   int o = 1;
   for(int i = 0; i < len; ++i){
    
    adj = markovTable.get(o-1).get(window);

    // Token is not present ... what to do ...
    if(adj == null){
      // For now start with a fresh randmly selected token
      {
        window = new LinkedList<Token<T>>();
        window.add(randomToken());
        o = 1;
        --i;
      }
       continue;
    }

    if(keyTokens != null && !keyTokens.isEmpty()){
     List<Token<T>> adjKeyTokens = new ArrayList<>(keyTokens); 
     adjKeyTokens.retainAll(adj);
     if(!adjKeyTokens.isEmpty()) adj = adjKeyTokens;
    }

    next = selectRandom(adj);

    result.add(next);

    if(o < order)
     ++o;
    else // Slide ...
     window.removeFirst();

     window.add(new Token<T>(next));
   }
   System.out.println("\n\nHealth of generated text is: " + health(result) + "\n\n");
   return result;
  }

  public double health(List<Token<T>> text){
    List<Double> avrgs = new ArrayList<>();

    int i;
    for(int o = 1; o < order; ++o){
     HashMap<List<Token<T>>, List<Token<T>>> table = markovTable.get(o-1);
 
     LinkedList<Token<T>> token = new LinkedList<>();
     List<Token<T>> adj;

     double hits = 0;

      for(i = 0; i < o; ++i)
        token.add(text.get(i));

      for(;i < text.size()-o-1; ++i){
       if(table.containsKey(token)){
        adj = table.get(token);
  
        if(adj.contains(text.get(i)))
          hits += 1;
       }
       // Slide
       token.removeFirst();
       token.add(text.get(i));
      }

      avrgs.add(new Double(hits / i));
    }
    double health = 0;
    for(i = 0; i < avrgs.size(); ++i)
      health += avrgs.get(i);

   return health / avrgs.size();
  }

  protected void countTokens(){
   tokenCount = new ArrayList<>();
   for(Token<T> token: tokens){
    if(tokenCount.contains(token))
      tokenCount.get(tokenCount.indexOf(token)).incr();
    else tokenCount.add(new Token<T>(token));
    Collections.sort(tokenCount);
   }
  }

  public void printTable(int order){
    HashMap<List<Token<T>>, List<Token<T>>> table = markovTable.get(order-1);
    order = order > this.order ? this.order : order;

    System.out.println("Table of order "+ order);
    for(List<Token<T>> key: table.keySet())
      System.out.println(key + " " + table.get(key));
  }
}