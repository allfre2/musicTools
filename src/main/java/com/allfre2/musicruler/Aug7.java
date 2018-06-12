package com.allfre2.musicruler;

import java.util.List;
import java.util.ArrayList;

/**
 * Implements an augmented 7 chord.
 */
public class Aug7 extends Chord{

   /**
    * Class Constructor.
    * @param root The root note from which the intervals of the augmented 7
    * chord are to be build upon.
    */
    public Aug7 (NoteI root){
      notes = new ArrayList<NoteI>();
      notes.add(root);
      notes.add(root.major(3));
      notes.add(root. aug(5));
      notes.add(root.major(7));
    }
}
