package com.allfre2.musicruler;

import java.util.List;
import java.util.ArrayList;

/**
 * Implements the Scale abstract class and builds a Melodic Minor scale.
 * The Melodic Minor scale pattern is:
 * W H W W W W
 * H = Half Tone.
 * W = Whole Tone.
 */
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
