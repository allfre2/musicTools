package com.allfre2.musicruler;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 * An implementation of the concept of chord conforming to the NoteCollection interface.
 * @see NoteCollection
 */
public abstract class Chord extends NoteCollection{

/**
 * @return Returns a List of NoteI containing the set of overtones
 * produced by playing all the notes in the chord.
 */
 public List<NoteI> Overtones(){
    LinkedHashSet<NoteI> overtones = new LinkedHashSet<>();
    for (NoteI note: notes)
     for(NoteI overtoneNote: note.Overtones())
        overtones.add(overtoneNote);

   return new ArrayList<NoteI>(overtones);
 }

/**
 * @param b the chord to count common overtones with.
 * @return returns the number of common overtones between the chord
 * and the argument chord.
 */
 public int commonOvertones(Chord b){
    List<NoteI> common = new ArrayList<>(Overtones());
    common.retainAll(b.Overtones());
    return common.size();
 }

/**
 * @param b the chord to count common notes with.
 * @return returns the number of common notes between the chord
 * and the argument chord.
 */
 public int commonNotes(Chord b){
    List<NoteI> common = new ArrayList<>(notes);
    common.retainAll(b.getNotes());
  return common.size();
 }

 /**
 * @param s the scale that the chord will be named relative to.
 * @return returns a string in roman numerals with some modifiers like
 * b, #, 7, -7, located in the {@link Symbols} class that describe
 * the degree, the mode (minor, mayor, etc) and other qualities of the
 * current chord.
 */
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
