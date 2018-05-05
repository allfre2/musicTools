package com.allfre2.musicruler;

import java.util.List;
import java.util.ArrayList;

public class Minor extends Chord{
    public Minor (NoteI root){
      notes = new ArrayList<NoteI>();
      notes.add(root);
      notes.add(root.minor(3));
      notes.add(root. just(5));
    }
}
