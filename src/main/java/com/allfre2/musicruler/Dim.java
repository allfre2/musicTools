package com.allfre2.musicruler;

import java.util.List;
import java.util.ArrayList;

/**
 * Implements a diminished chord.
 */
public class Dim extends Chord{

   /**
    * Class Constructor.
    * @param root The root note from which the intervals of the diminished
    * triad are to be build upon.
    */
    public Dim (NoteI root){
      notes = new ArrayList<NoteI>();
      notes.add(root);
      notes.add(root.minor(3));
      notes.add(root. dim(5));
    }
}
