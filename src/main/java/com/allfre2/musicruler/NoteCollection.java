package com.allfre2.musicruler;

import java.util.List;
import java.util.TreeSet;

/**
 * An abstract class to provide a basic set of capabilities to classes
 * implementing the concept of a collection of notes.
 * <p>
 * Chords, Scales, and melodies can be thought of as a collection of notes.
 * This "interface" makes the implementation oof those concepts a little easier
 * and enables a polimorphic way of treating all of them.
 */
public abstract class NoteCollection{
    
 protected List<NoteI> notes;

 /**
 * @return an int corresponding to the size of the collection that holds the
 * NoteI objects.
 */
 public int size(){
  return notes.size();
 }

 /**
 * @return a list of NoteI containing all the notes in underlying collection.
 */
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

 /**
 * @return uses {@link NoteCollectionFactory} class to return a String with
 * a descriptive name of the {@link Chord}, {@link Scale}, etc ...
 */
 public String name(){
 	return notes.get(0) + NoteCollectionFactory.getSymbol(this);
 }
}
