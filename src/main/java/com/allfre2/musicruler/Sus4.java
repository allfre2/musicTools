package com.allfre2.musicruler;

import java.util.List;
import java.util.ArrayList;

public class Sus4 extends Chord{
    public Sus4 (NoteI root){
      notes = new ArrayList<NoteI>();
      notes.add(root);
      notes.add(root. just(4));
      notes.add(root.just(5));
    }
}
