package com.allfre2.musicruler;

import java.util.List;
import java.util.ArrayList;

public class Dim extends Chord{
    public Dim (NoteI root){
      notes = new ArrayList<NoteI>();
      notes.add(root);
      notes.add(root.minor(3));
      notes.add(root. dim(5));
    }
}
