package com.allfre2.musicruler;

import java.util.List;
import java.util.ArrayList;

/**
 * Implements the Scale abstract class and builds a Chromatic scale.
 * The chromatic scale pattern is:
 * H H H H H H H H H H H
 * H = Half Tone.
 * W = Whole Tone.
 */
public class ChromaticScale extends Scale{

    public ChromaticScale (NoteI root){
     notes = new ArrayList<NoteI>();
     notes.add(root);
     notes.add(root.  aug(1));
     notes.add(root.major(2));
     notes.add(root. aug(2));
     notes.add(root.major(3));
     notes.add(root. just(4));
     notes.add(root. aug(4));
     notes.add(root.just(5));
     notes.add(root. aug(5));
     notes.add(root.major(6));
     notes.add(root. aug(6));
     notes.add(root.major(7));
    }
}
