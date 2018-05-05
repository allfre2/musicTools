package com.allfre2.musicruler;

import java.util.List;
import java.util.ArrayList;

public class Aug7 extends Chord{
    public Aug7 (NoteI root){
      notes = new ArrayList<NoteI>();
      notes.add(root);
      notes.add(root.major(3));
      notes.add(root. aug(5));
      notes.add(root.major(7));
    }
}
