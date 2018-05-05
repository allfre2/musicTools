package com.allfre2.musicruler;

import java.util.List;
import java.util.TreeSet;

public abstract class NoteCollection{
    
 protected List<NoteI> notes;

 public int size(){
  return notes.size();
 }

 public final List<NoteI> getNotes(){
  return notes;
 }

 @Override
 public int hashCode(){
  return this.toString().hashCode();
 }
 
 @Override
 public boolean equals(Object o){
  return this.notes.equals(((NoteCollection)o).getNotes());
 }

 @Override
 public String toString(){
 	return notes.toString();
 }

 public String name(){
 	return notes.get(0) + NoteCollectionFactory.getSymbol(this);
 }
}
