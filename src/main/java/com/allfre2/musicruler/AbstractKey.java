package com.allfre2.musicruler;

import java.util.List;
import java.util.ArrayList;

/**
 * An Interface to define the abstract concept of a "key" or tonality.
 * <p>
 * Builds a "key" starting from a scale and provides basic methods to
 * interact with the harmonic nuances of each key.
 * @see  Key ModalKey
 */
public abstract class AbstractKey{

	protected NoteI root;
    protected Scale rootScale;
    protected List<Chord> chords;
    protected List<Chord> chords7;

   /**
    * Accesor method to get the base note of the key.
    * @return Returns the root field wich contains a the root note of the scale.
    * @see NoteI
    */
	public final NoteI getRoot(){
     return this.root;
    }

   /**
    * Accesor method to get the base scale of the key.
    * @return Returns the root scale from which the given key is built upon.
    * @see Scale
    */
    public final Scale getRootScale(){
     return this.rootScale;
    }

   /**
    * Accesor method to get the set of diatonic triads in the key.
    * @return Returns a List of diatonic triads that come up from the
    * degrees of the root scale.
    * @see Chord
    */
    public final List<Chord> getTriads(){
     return this.chords;
    }

   /**
    * Accesor method to get the set of 7 chords in the key.
    * @return Returns a List of diatonic 7 chords that come up from the
    * degrees of the root scale.
    * @see Chord
    */
    public final List<Chord> get7Chords(){
     return chords7;
    }

   /**
    * Accesor method to get the set of diatonic 7 chords and triads in the key.
    * @param note A note to be matches against the chords.
    * @return Returns all the diatonic triads and 7 chords in the key that
    * include the NoteI passed as parameter.
    */
    public final List<Chord> chords(NoteI note){
     List<Chord> chordList = new ArrayList<>();

     for(Chord chord: getTriads())
      if(chord.getNotes().contains(note))
      	chordList.add(chord);

     for(Chord chord: get7Chords())
      if(chord.getNotes().contains(note))
      	chordList.add(chord);

      return chordList;
    }

    @Override
     public String toString(){
     	return getRootScale().name();
     } 
}