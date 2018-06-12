package com.allfre2.musicruler.songs;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Provides an interface to the source of the text used by
 * {@link Markov<T>} to generate tables.
 */
public abstract class DataSource{

 List<Token<String>> tokens;

/**
 * Splits the data String into tokens.
 * @param data The String containing the source text.
 */
 protected void tokenize(String data){
  	Scanner s = new Scanner(System.in);
    data = data.replaceAll("[\n|\r\n]"," ");
    data = data.replaceAll("[ ]+"," ");
    data = data.replaceAll("\\W|[0-9]"," ");
 
    tokens = new ArrayList<>();
 
    for(String str: data.split(" ")){
     if(!str.isEmpty()){
      tokens.add(new Token<String>(str.toLowerCase()));
     }
    }
 } 

/**
 * @return Returns a List {@link Token<String>} containing the tokens
 * extracted from the data String by {@link DataSource#tokenize}.
 */
 public List<Token<String>> getTokens(){
  return tokens;
 }

/**
 * Method to read all lines of a surce into a single String.
 * @return Returns lines read from a file or url or any BufferedReader
 * as a single String.
 */
 public static String readLines(BufferedReader br)
  throws IOException{
   String line, data = "";
   while((line = br.readLine()) != null)
     data += line + " ";

  return data;
 }

 // public List<Token<String>> getWords();
 // public List<Token<String>> getSentenceEndTokens();
 // public List<Token<String>> getThemeTokens();//?
}