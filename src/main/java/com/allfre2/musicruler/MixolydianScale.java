package com.allfre2.musicruler;

import java.util.List;
import java.util.ArrayList;

/**
 * Implements the Scale abstract class and builds a Melodic Minor scale.
 * The Melodic Minor scale pattern is:
 * W W H W W H
 * H = Half Tone.
 * W = Whole Tone.
 */
public class MixolydianScale extends Scale{

    public MixolydianScale (NoteI root){
     notes = new ArrayList<NoteI>();
     notes.add(root);
     notes.add(root.major(2));
     notes.add(root.major(3));
     notes.add(root. just(4));
     notes.add(root. just(5));
     notes.add(root.major(6));
     notes.add(root.minor(7));
    }
}
