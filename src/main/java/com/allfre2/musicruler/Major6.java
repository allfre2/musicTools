package com.allfre2.musicruler;

import java.util.List;
import java.util.ArrayList;

/**
 * Implements a major 6 chord. Which is an inversion of the "vii" chord
 * of the major scale or "i" chord if the minor scale.
 */
public class Major6 extends Chord{

   /**
    * Class Constructor.
    * @param root The root note from which the intervals of the major 6
    * chord are to be build upon.
    */
    public Major6 (NoteI root){
      notes = new ArrayList<NoteI>();
      notes.add(root);
      notes.add(root.major(3));
      notes.add(root. just(5));
      notes.add(root.major(6));
    }
}
