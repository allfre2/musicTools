package com.allfre2.musicruler;

import java.util.List;
import java.util.ArrayList;

public class Major extends Chord{
    public Major (NoteI root){
      notes = new ArrayList<NoteI>();
      notes.add(root);
      notes.add(root.major(3));
      notes.add(root. just(5));
    }
}
