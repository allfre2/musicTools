package com.allfre2.musicruler;

import java.util.List;
import java.util.ArrayList;

public class WholeToneScale extends Scale{
    
    public WholeToneScale (NoteI root){
     notes = new ArrayList<NoteI>();
     notes.add(root);
     notes.add(root.major(2));
     notes.add(root.major(3));
     notes.add(root. aug(4));
     notes.add(root. aug(5));
     notes.add(root. aug(6));
	}
}
