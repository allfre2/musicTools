package com.allfre2.musicruler;

import java.util.List;
import java.util.ArrayList;

/**
 * Implements a minor triad.
 */
public class Minor extends Chord{

   /**
    * Class Constructor.
    * @param root The root note from which the intervals of the minor triad
    * are to be build upon.
    */
    public Minor (NoteI root){
      notes = new ArrayList<NoteI>();
      notes.add(root);
      notes.add(root.minor(3));
      notes.add(root. just(5));
    }
}
