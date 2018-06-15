package com.allfre2.musicruler;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;

/**
 * An implementation of the concept of scale conforming to the NoteCollection interface.
 * <p>
 * Implements various methods that are useful when dealing with scales
 * get the note corresponding to a given degree of the scale,
 * get the degree of a given note,
 * get a fully build diatonic {@link Chord} corresponding to a given degree.
 * @see NoteCollection
 */
public abstract class Scale extends NoteCollection{

/**
 * Get the (deg)th note in the scale.
 * @param deg Integer encoding the degree wanted.
 * @return Returns the NoteI corresponding to the degree passed as argument.
 */
 public NoteI degree(int deg){
  if(deg < 1) deg = 1;
  deg = (deg / (notes.size()+1)) + (deg % (notes.size()+1));
  return notes.get(deg-1);
 }

/**
 * Find out which degree corresponds to the note passed as argument.
 * @param note The note to be searched in the NoteCollection.
 * @return Returns the index+1 of the argument NoteI int the List
 * of notes.
 */
 public int degreeOf(NoteI note){
  for(int i = 1; i < size()+1; ++i){
    if(note.getNoteIndex() == degree(i).getNoteIndex()){
        return i;
    }
  }
  return 0; // fail
 }

 private List<NoteI> triads(int deg){
    return new ArrayList<NoteI>
      (Arrays.asList(
     new NoteI[]{
        degree(deg),
        degree(deg+2),
        degree(deg+4)
     }));
 }

/**
 * Uses the NoteCollectionFactory class to build a chord from
 * a list of notes.
 * @param deg the degree of the diatonic chord that is wanted.
 * @return returns a diatonic {@link Chord} corresponding to the given
 * degree as an integer argument.
 */
 public Chord chord(int deg){
    List<NoteI> triad = triads(deg);
   return NoteCollectionFactory.makeChordFromList(triad);
 }

/**
 * Uses the NoteCollectionFactory class to build a chord from
 * a list of notes.
 * @param deg the degree of the diatonic 7 chord that is wanted.
 * @return returns a diatonic 7 {@link Chord} corresponding to the given
 * degree as an integer argument.
 */
 public Chord chord7(int deg){
    List<NoteI> triad = triads(deg);
    triad.add(degree(deg+6));
   return NoteCollectionFactory.makeChordFromList(triad);
 }

/**
 * Uses NoteCollectionFactory class to build a chord from a list
 * of notes.
 * @param deg The degree of the wanted chord.
 * @return Returns the 6 {@link Chord} corresponding to the deg argument.
 */
 public Chord chord6(int deg){
    List<NoteI> triad = triads(deg);
    triad.add(degree(deg+5));
   return NoteCollectionFactory.makeChordFromList(triad);
 }

/**
 * Calls the overloaded chords method with an int (any, 6 or 7)
 * corresponding to the type of chords that the caller wants.
 * @return Returns a list of all the diatonic chords in the scale.
 */
 public List<Chord> chords(){
    return chords(0);
 }

/**
 * A method for return specific types of chords belonging to the root scale.
 * @param chordType Specifies if the chords to be return are 7th chords (chorType = 7),
 * 6th chords (chordType = 6), or simple triads (chordType = any other int).
 * @return Returns a List of diatonic chords in the scale according to the
 * chordType argument
 */
 public List<Chord> chords(int chordType){
    Function<Integer, Chord> f;
    if(chordType == 7){
     f = this::chord7;
    }
    else if(chordType == 6){
     f = this::chord6;
    }else{
     f = this::chord;
    }
    return chords(f);
 }

 private List<Chord> chords(Function<Integer,Chord> f){
   List<Chord> degrees = new ArrayList<>();
  for(int i = 1; i < size()+1; ++i){
   degrees.add(f.apply(i));
  }
  return degrees;
 }
}
