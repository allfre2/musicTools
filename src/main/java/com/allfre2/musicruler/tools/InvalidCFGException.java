package com.allfre2.musicruler.tools;

public class InvalidCFGException extends Exception{
 
 private String error;
 private int index;
 private int line;

 public InvalidCFGException(String msg){
  super(msg);
 }

 public InvalidCFGException(int line, int index, String msg){
  this.line = line;
  this.index = index;
  this.error = msg;
 }

 public String msg(){
 	return this.error;
 }
}
