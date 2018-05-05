package com.allfre2.musicruler;

import java.util.List;
import java.util.ArrayList;

public class Minor7b5 extends Chord{
    public Minor7b5 (NoteI root){
      notes = new ArrayList<NoteI>();
      notes.add(root);
      notes.add(root.minor(3));
      notes.add(root. dim(5));
      notes.add(root.minor(7));
    }
}
