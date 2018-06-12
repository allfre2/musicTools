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

 public NoteI degree(int deg){
  if(deg < 1) deg = 1;
  deg = (deg / (notes.size()+1)) + (deg % (notes.size()+1));
  return notes.get(deg-1);
 }

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

 public Chord chord6(int deg){
 	List<NoteI> triad = triads(deg);
 	triad.add(degree(deg+5));
   return NoteCollectionFactory.makeChordFromList(triad);
 }

/**
 * @return returns a list of all the diatonic chords in the scale.
 */
 public List<Chord> chords(){
 	return chords(0);
 }

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
