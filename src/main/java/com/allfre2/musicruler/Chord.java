package com.allfre2.musicruler;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

public abstract class Chord extends NoteCollection{

 //TODO: Keep order
 public List<NoteI> Overtones(){
 	HashSet<NoteI> overtones = new HashSet<>();
 	for (NoteI note: notes) {
 	 for(NoteI overtoneNote: note.Overtones()){
 	 	overtones.add(overtoneNote);
 	 }
 	}
   return new ArrayList<NoteI>(overtones);
 }

 public int commonOvertones(Chord b){
 	List<NoteI> common = new ArrayList<>(Overtones());
 	common.retainAll(b.Overtones());
 	return common.size();
 }

 public int commonNotes(Chord b){
 	List<NoteI> common = new ArrayList<>(notes);
 	common.retainAll(b.getNotes());
  return common.size();
 }

 public String name(Scale s){
 	NoteI root = s.degree(1);
 	int deg = s.degreeOf(notes.get(0));
 	int diff = s.degree(deg).getAltIndex() - notes.get(0).getAltIndex();
 	String roman = Symbols.romanNumerals[deg-1];
 	String nameStr = 
 	  ((diff == 0) ? "" : Symbols.modifiers[2 + -diff][1])
 	  + 
 	  (NoteCollectionFactory.isMinor(this) ? roman.toLowerCase() : roman)
 	  +
 	  (this.getClass() == Minor.class ? "" : NoteCollectionFactory.getSymbol(this));
 	  
 	return nameStr;
 }
}
