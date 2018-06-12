package com.allfre2.musicruler;

import java.util.List;
import java.util.ArrayList;

/**
 * Implements the Scale abstract class and builds a Prhygian scale.
 * The Prhygian scale pattern is:
 * H W W W H W
 * H = Half Tone.
 * W = Whole Tone.
 */
public class PhrygianScale extends Scale{

    public PhrygianScale (NoteI root){
     notes = new ArrayList<NoteI>();
     notes.add(root);
     notes.add(root.minor(2));
     notes.add(root.minor(3));
     notes.add(root. just(4));
     notes.add(root. just(5));
     notes.add(root.minor(6));
     notes.add(root.minor(7));
    }
}
