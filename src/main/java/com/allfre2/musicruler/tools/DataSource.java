package com.allfre2.musicruler.tools;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;

public abstract class DataSource{

 List<Token<String>> tokens;

 // TODO improve this
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

 public List<Token<String>> getTokens(){
  return tokens;
 }

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