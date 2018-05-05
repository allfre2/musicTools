package com.allfre2.musicruler;

import java.util.List;
import java.util.ArrayList;

public class Dim7 extends Chord{
    public Dim7 (NoteI root){
      notes = new ArrayList<NoteI>();
      notes.add(root);
      notes.add(root.minor(3));
      notes.add(root. dim(5));
      notes.add(root. dim(7));
    }
}
