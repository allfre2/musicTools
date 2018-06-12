package com.allfre2.musicruler;

import java.util.List;
import java.util.ArrayList;

/**
 * Implements the Scale abstract class and builds a Pentatonic scale.
 * The Pentatonic scale pattern is:
 * W W W W+ W
 * H = Half Tone.
 * W = Whole Tone.
 */
public class PentatonicScale extends Scale{

    public PentatonicScale (NoteI root){
     notes = new ArrayList<NoteI>();
     notes.add(root);
     notes.add(root.major(2));
     notes.add(root. just(4));
     notes.add(root. just(5));
     notes.add(root.major(6));
    }
}
