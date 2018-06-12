package com.allfre2.musicruler.songs;

/**
 * Exception to be thrown wen context free grammars are not
 * in the correct format or violate some fundamental property.
 * @see CFG
 */
public class InvalidCFGException extends Exception{

 public InvalidCFGException(String msg){
  super(msg);
 }

 public InvalidCFGException(String msg, int line, int index){
  super(msg + " (line: " + line + ", index: " + index + ")");
 }
}
