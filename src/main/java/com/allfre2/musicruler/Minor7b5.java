package com.allfre2.musicruler;

import java.util.List;
import java.util.ArrayList;

/**
 * Implements a minor 7 b5 chord.
 */
public class Minor7b5 extends Chord{

   /**
    * Class Constructor.
    * @param root The root note from which the intervals of the minor 7 b5
    * chord are to be build upon.
    */
    public Minor7b5 (NoteI root){
      notes = new ArrayList<NoteI>();
      notes.add(root);
      notes.add(root.minor(3));
      notes.add(root. dim(5));
      notes.add(root.minor(7));
    }
}
