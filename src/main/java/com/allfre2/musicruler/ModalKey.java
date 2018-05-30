package com.allfre2.musicruler;

import java.util.List;
import java.util.ArrayList;

public class ModalKey extends AbstractKey{

	public ModalKey (String noteStr){
	 this(new Note(noteStr));
	}

	public ModalKey (NoteI root){
	 this(new MajorScale(root));
	}

	public ModalKey (Scale rootScale){
        this.root = rootScale.getNotes().get(0);
        this.rootScale = rootScale;
        this.chords = new ArrayList<>();
        this.chords7 = new ArrayList<>();

        List<Scale> modes = NoteCollectionFactory.getAllModes(this.root);

        for(Scale scale: modes){

         for (Chord c: scale.chords())
          if( !chords.contains(c) )
            chords.add(c);

         for (Chord c: scale.chords(7))
          if( !chords7.contains(c) )
            chords7.add(c);

        }
	}
}