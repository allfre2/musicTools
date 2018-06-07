package com.allfre2.musicruler.tools;

public class InvalidCFGException extends Exception{

 public InvalidCFGException(String msg){
  super(msg);
 }

 public InvalidCFGException(String msg, int line, int index){
  super(msg + " (line: " + line + ", index: " + index + ")");
 }
}
