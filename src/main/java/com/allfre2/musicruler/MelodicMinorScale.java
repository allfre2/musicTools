package com.allfre2.musicruler;

import java.util.List;
import java.util.ArrayList;

public class MelodicMinorScale extends Scale{

    public MelodicMinorScale (NoteI root){
     notes = new ArrayList<NoteI>();
     notes.add(root);
     notes.add(root.major(2));
     notes.add(root.minor(3));
     notes.add(root. just(4));
     notes.add(root. just(5));
     notes.add(root.major(6));
     notes.add(root.major(7));
    }
}
