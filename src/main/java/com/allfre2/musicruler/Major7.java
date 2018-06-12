package com.allfre2.musicruler;

import java.util.List;
import java.util.ArrayList;

/**
 * Implements a major 7 chord.
 */
public class Major7 extends Chord{

   /**
    * Class Constructor.
    * @param root The root note from which the intervals of the major 7
    * chord are to be build upon.
    */
    public Major7 (NoteI root){
      notes = new ArrayList<NoteI>();
      notes.add(root);
      notes.add(root.major(3));
      notes.add(root. just(5));
      notes.add(root.major(7));
    }
}
