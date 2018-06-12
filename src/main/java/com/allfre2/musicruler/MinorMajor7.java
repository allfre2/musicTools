package com.allfre2.musicruler;

import java.util.List;
import java.util.ArrayList;

/**
 * Implements a minor major 7 chord.
 */
public class MinorMajor7 extends Chord{

   /**
    * Class Constructor.
    * @param root The root note from which the intervals of the minor major 7
    * chord are to be build upon.
    */
    public MinorMajor7 (NoteI root){
      notes = new ArrayList<NoteI>();
      notes.add(root);
      notes.add(root.minor(3));
      notes.add(root. just(5));
      notes.add(root.major(7));
    }
}
