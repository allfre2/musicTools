package com.allfre2.musicruler;

import java.util.List;
import java.util.ArrayList;

public class Sus2 extends Chord{
    public Sus2 (NoteI root){
      notes = new ArrayList<NoteI>();
      notes.add(root);
      notes.add(root.major(2));
      notes.add(root.just(5));
    }
}
