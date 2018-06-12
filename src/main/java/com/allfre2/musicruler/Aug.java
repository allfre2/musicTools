package com.allfre2.musicruler;

import java.util.List;
import java.util.ArrayList;

/**
 * Implements an augmented triad.
 */
public class Aug extends Chord{

   /**
    * Class Constructor.
    * @param root The root note from which the intervals of the triad
    * are to be build upon.
    */
    public Aug (NoteI root){
      notes = new ArrayList<NoteI>();
      notes.add(root);
      notes.add(root.major(3));
      notes.add(root. aug(5));
    }
}
