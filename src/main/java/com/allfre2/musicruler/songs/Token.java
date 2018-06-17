package com.allfre2.musicruler.songs;

/**
 * A Token Class used to make the Markov generator
 * generic and not only to be used with Strings.
 * @see Markov
 */
public class Token<T> implements Comparable<Token<T>>{

  T value;
  protected int count;
  protected double probability;

 /**
  * Class Constructor.
  * @param value Contains a T object that is intended to be wrapped as a Token.
  */
  public Token(T value){
    this(value, 1);
  }

 /**
  * Class Constructor.
  * @param value Contains a T object that is intended to be wrapped as a Token.
  * @param count Integer with the number of Token with the same value.
  */
  public Token(T value, int count){
   this.value = value;
   this.count = count;
   this.probability = 0.0;
  }

 /**
  * Copy Constructor.
  * @param token A Token that is going to be copied.
  */
  public Token(Token<T> token){
   this.value = token.get();
   this.count = token.getCount();
   this.probability = token.getP();
  }

 /**
  * Getter method.
  * @return Returns the T value contained by the Token.
  */
  public T get(){
    return value;
  }

 /**
  * Increments the count field of the Token by 1.
  * @return Returns the new value of the count.
  */
  public int incr(){
   return ++count;
  }

 /**
  * Decrements the count field of the Token by 1.
  * @return Rturns the new value of the count.
  */
  public int decr(){
   return --count;
  }

 /**
  * Getter method.
  * @return Returns the current count of Token with the same value.
  */
  public int getCount(){
   return count;
  }

 /**
  * Setter method.
  * @param newCount the new count to be asigned to the count field.
  */
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
