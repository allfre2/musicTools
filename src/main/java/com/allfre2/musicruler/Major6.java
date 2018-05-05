package com.allfre2.musicruler;

import java.util.List;
import java.util.ArrayList;

public class Major6 extends Chord{
    public Major6 (NoteI root){
      notes = new ArrayList<NoteI>();
      notes.add(root);
      notes.add(root.major(3));
      notes.add(root. just(5));
      notes.add(root.major(6));
    }
}
