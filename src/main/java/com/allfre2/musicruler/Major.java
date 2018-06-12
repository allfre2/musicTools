package com.allfre2.musicruler;

import java.util.List;
import java.util.ArrayList;

/**
 * Implements a major chord.
 */
public class Major extends Chord{

   /**
    * Class Constructor.
    * @param root The root note from which the intervals of the major
    * chord are to be build upon.
    */
    public Major (NoteI root){
      notes = new ArrayList<NoteI>();
      notes.add(root);
      notes.add(root.major(3));
      notes.add(root. just(5));
    }
}
