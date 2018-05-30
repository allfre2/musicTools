package com.allfre2.musicruler;

import java.util.List;
import java.util.ArrayList;

public abstract class AbstractKey{

	protected NoteI root;
    protected Scale rootScale;
    protected List<Chord> chords;
    protected List<Chord> chords7;

	public final NoteI getRoot(){
     return this.root;
    }

    public final Scale getRootScale(){
     return this.rootScale;
    }

    public final List<Chord> getTriads(){
     return this.chords;
    }

    public final List<Chord> get7Chords(){
     return chords7;
    }

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