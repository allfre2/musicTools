package com.allfre2.musicruler;

import java.util.List;
import java.util.ArrayList;

/**
 * Implements a suspened 2nd triad. Which is an inversion of the suspended 4 triad.
 */
public class Sus2 extends Chord{

   /**
    * Class Constructor.
    * @param root The root note from which the intervals of the suspended 2nd
    * triad are to be build upon.
    */
    public Sus2 (NoteI root){
      notes = new ArrayList<NoteI>();
      notes.add(root);
      notes.add(root.major(2));
      notes.add(root.just(5));
    }
}
