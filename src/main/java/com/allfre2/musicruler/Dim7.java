package com.allfre2.musicruler;

import java.util.List;
import java.util.ArrayList;

/**
 * Implements a diminished 7 chord.
 */
public class Dim7 extends Chord{

   /**
    * Class Constructor.
    * @param root The root note from which the intervals of the diminished 7
    * chord are to be build upon.
    */
    public Dim7 (NoteI root){
      notes = new ArrayList<NoteI>();
      notes.add(root);
      notes.add(root.minor(3));
      notes.add(root. dim(5));
      notes.add(root. dim(7));
    }
}
