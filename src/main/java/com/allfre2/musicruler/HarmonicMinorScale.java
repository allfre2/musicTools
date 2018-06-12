package com.allfre2.musicruler;

import java.util.List;
import java.util.ArrayList;

/**
 * Implements the Scale abstract class and builds a Harmonic Minor scale.
 * The Harmonic Minor scale pattern is:
 * W H W W H W+ (last one is an augmented 2nd 1 Tone and 1/2)
 * H = Half Tone.
 * W = Whole Tone.
 */
public class HarmonicMinorScale extends Scale{

    public HarmonicMinorScale (NoteI root){
     notes = new ArrayList<NoteI>();
     notes.add(root)            ;
     notes.add(root.major(2));
     notes.add(root.minor(3));
     notes.add(root. just(4));
     notes.add(root. just(5));
     notes.add(root.minor(6));
     notes.add(root.major(7));
    }
}
