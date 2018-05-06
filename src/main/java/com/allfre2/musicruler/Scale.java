package com.allfre2.musicruler;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;

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

 public Chord chord(int deg){
 	List<NoteI> triad = triads(deg);
   return NoteCollectionFactory.makeChordFromList(triad);
 }

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
