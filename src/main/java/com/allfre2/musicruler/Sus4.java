package com.allfre2.musicruler;

import java.util.List;
import java.util.ArrayList;
/**
 * Implements a suspened 4th triad. Which is an inversion of the suspended 2 triad.
 */
public class Sus4 extends Chord{

   /**
    * Class Constructor.
    * @param root The root note from which the intervals of the suspended 4th
    * triad are to be build upon.
    */
    public Sus4 (NoteI root){
      notes = new ArrayList<NoteI>();
      notes.add(root);
      notes.add(root. just(4));
      notes.add(root.just(5));
    }
}
