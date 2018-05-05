package com.allfre2.musicruler;

import java.util.List;
import java.util.ArrayList;

public class Dom7 extends Chord{
    public Dom7 (NoteI root){
      notes = new ArrayList<NoteI>();
      notes.add(root);
      notes.add(root.major(3));
      notes.add(root. just(5));
      notes.add(root.minor(7));
    }
}
