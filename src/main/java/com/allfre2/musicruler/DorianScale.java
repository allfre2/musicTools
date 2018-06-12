package com.allfre2.musicruler;

import java.util.List;
import java.util.ArrayList;

/**
 * Implements the Scale abstract class and builds a Dorian scale.
 * The Dorian scale pattern is:
 * W H W W W H
 * H = Half Tone.
 * W = Whole Tone.
 */
public class DorianScale extends Scale{

    public DorianScale (NoteI root){
     notes = new ArrayList<NoteI>();
     notes.add(root);
     notes.add(root.major(2));
     notes.add(root.minor(3));
     notes.add(root. just(4));
     notes.add(root. just(5));
     notes.add(root.major(6));
     notes.add(root.minor(7));
    }
}
