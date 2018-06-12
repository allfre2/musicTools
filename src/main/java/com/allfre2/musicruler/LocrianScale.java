package com.allfre2.musicruler;

import java.util.List;
import java.util.ArrayList;

/**
 * Implements the Scale abstract class and builds a Locrian scale.
 * The Locrian scale pattern is:
 * H W W H W W
 * H = Half Tone.
 * W = Whole Tone.
 */
public class LocrianScale extends Scale{

    public LocrianScale (NoteI root){
     notes = new ArrayList<NoteI>();
     notes.add(root);
     notes.add(root.minor(2));
     notes.add(root.minor(3));
     notes.add(root. just(4));
     notes.add(root. dim(5));
     notes.add(root.minor(6));
     notes.add(root.minor(7));
    }
}
