package com.allfre2.musicruler;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Note implements NoteI{

 public static final String[] Notes = {"c", "d", "e", "f", "g", "a", "b"};
 public static final int[] semitones = {0 ,  2 ,  4 ,  5 ,  7 ,  9 ,  11};

 protected boolean useUnicode;
 protected boolean upperCase;

 protected int noteIndex;
 protected int altIndex;

 public Note(String note){
    this(note.substring(0,1).toLowerCase(),
         note.substring(1));
 }

 public Note(String note, String alt){
  useUnicode = true;
  upperCase = true;
  noteIndex = Arrays.asList(Notes).indexOf(note.toLowerCase());
  if(noteIndex < 0){
    noteIndex = 0; // C
  }
  altIndex = 2; // default to natural

  for (int i = 0; i < Symbols.modifiers.length; ++i){
    if(Arrays.asList(Symbols.modifiers[i]).indexOf(alt) > -1){
        altIndex = i;
    }
  }
 }

 public int getNoteIndex(){
  return this.noteIndex;
 }

 public int getAltIndex(){
  return this.altIndex;
 }

 public List<NoteI> Overtones(){
   List<NoteI> overtones = new ArrayList<NoteI>();
   overtones.add(major(1));
   overtones.add( just(5));
   overtones.add(major(3));
   overtones.add(minor(7));
   overtones.add(major(2));
   overtones.add(  aug(4));
   overtones.add(major(6));
   overtones.add(major(7));

   return overtones;
 }

 public int semitones(NoteI note){
  int dist = distance(note.getNoteIndex());
  dist = dist < 0 ? dist + 12 : dist;
  dist += -2 + note.getAltIndex();
  return dist;
 }

 public boolean isEnharmonic(NoteI note){
  int semitones = semitones(note);
  if(semitones == 12 || semitones == 0)
    return true;
  return false;
 }

 public NoteI aug(int interval){
  interval = (interval < 1 ? 1 : interval);
  int distance = semitones[ (interval -1)%Notes.length ] - semitones[0] + 1;
  return makeNote(interval, distance);
 }

 public NoteI major(int interval){
  interval = (interval < 1 ? 1 : interval);
  int distance = semitones[ (interval -1)%Notes.length ] - semitones[0];
  return makeNote(interval, distance);
 }

 public NoteI minor(int interval){
  int i = interval %8;
  if(i < 1 || i == 4 || i == 5)
   return just(interval);
  int distance = semitones[ (interval -1)%Notes.length ] - semitones[0]
   - 1; // minor
  return makeNote(interval, distance);
 }

 public NoteI dim(int interval){
  interval = (interval < 1 ? 1 : interval);
  int distance = semitones[ (interval -1)%Notes.length ] - semitones[0] -2;
   int i = interval %8;
   if(i < 1 || i == 4 || i == 5)
    distance += 1;
  return makeNote(interval, distance);
 }

 public NoteI just(int interval){
  return major(interval);
 }

 // Notation setters
 private void useUnicode(boolean value){ // Always use Unicode for now
  this.useUnicode = value;
 }

 public void upperCase(boolean value){
  this.upperCase = value;
 }

 // Helper functions
 public static NoteI random(){
  Random rnd = new Random();
  int note = rnd.nextInt(Notes.length);
  int alt = rnd.nextInt(Symbols.modifiers.length -3) +1;

  return new Note(Notes[note] + Symbols.modifiers[alt][1]);
 }
 // Returns the number of semitones between the current note and the
 // new one before applying alterations
 private int distance(int newNoteIndex){
    int dist;
    if(newNoteIndex < noteIndex){
      dist = 1 + (semitones[semitones.length-1] - semitones[noteIndex]);
      dist += semitones[newNoteIndex];
    }
    else{
        dist = semitones[newNoteIndex] - semitones[noteIndex];
    }
    dist += 2 + -altIndex;
    return dist;
 }

 private int intervalIndex(int interval){
  return (noteIndex + ((interval -1)%Notes.length)) % Notes.length;
 }

 private String getLetter(int interval){
   interval = (interval < 1 ? 1 : interval);
   return Notes[ intervalIndex(interval) ];
 }

 private String getAlt(int interval, int semitones){
   int dist = distance( intervalIndex(interval) );
   int alt = 2 - dist + semitones; // FIX!
   alt = (alt < 0 || alt > 4) ? 5 : alt;
   return Symbols.modifiers[ alt ][ useUnicode ? 1 : 0 ];
 }

 private NoteI makeNote(int interval, int semitones){
    return new Note(getLetter(interval), getAlt(interval, semitones));
 }

 @Override
 public boolean equals(Object o){
  Note note = (Note) o;
  if(noteIndex == note.getNoteIndex() && altIndex == note.getAltIndex()){
    return true;
  }
  return false;
 }

 @Override
 public int hashCode(){
  return toString().hashCode();
 }

 @Override
 public String toString(){
    String note = Notes[noteIndex];
    if(upperCase) note = note.toUpperCase();
    if(altIndex != 2){
        note += Symbols.modifiers[altIndex][useUnicode ? 1 : 0];
    }
    return note;
 }
}
