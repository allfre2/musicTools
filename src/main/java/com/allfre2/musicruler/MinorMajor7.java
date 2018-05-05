package com.allfre2.musicruler;

import java.util.List;
import java.util.ArrayList;

public class MinorMajor7 extends Chord{
    public MinorMajor7 (NoteI root){
      notes = new ArrayList<NoteI>();
      notes.add(root);
      notes.add(root.minor(3));
      notes.add(root. just(5));
      notes.add(root.major(7));
    }
}
