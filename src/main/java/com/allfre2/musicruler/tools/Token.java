package com.allfre2.musicruler.tools;

public class Token<T> implements Comparable<Token<T>>{
    
  T value;
  protected int count;
  protected double probability;

  public Token(T value){
  	this(value, 1);
  }

  public Token(T value, int count){
   this.value = value;
   this.count = count; 
   this.probability = 0.0;
  }

  public Token(Token<T> token){
   this.value = token.get();
   this.count = token.getCount();
   this.probability = token.getP();
  }

  public T get(){
  	return value;
  }

  public int incr(){
   return ++count;
  }

  public int decr(){
   return --count;
  }

  public int getCount(){
   return count;
  }

  public void setCount(int newCount){
  	this.count = newCount;
  }

  public double getP(){
  	return probability;
  }

  public void setP(double p){
  	probability = p;
  }

  @Override
  public int compareTo(Token<T> token){
   return count - token.getCount();
  }

  @Override
  public int hashCode(){
   return (value.toString() + count).hashCode();
  }

  @Override
  public boolean equals(Object token){
    @SuppressWarnings("unchecked")
     boolean eq = value.equals(((Token<T>)token).get());
    return eq;
  }

  @Override
  public String toString(){
  	return "" + value + " (" + count + "/" + probability + ")";
  }

  public String simpleString(){
  	return value+ "";
  }
}