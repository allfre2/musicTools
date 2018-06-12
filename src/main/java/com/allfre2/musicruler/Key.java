package com.allfre2.musicruler;

import java.util.List;
import java.util.ArrayList;

/**
 * An AbstractKey implementation that build a "key" based on a root scale.
 * The scale can be any of the modal scales strating form any root note.
 */
public class Key extends AbstractKey{

   /**
    * Class Constructor.
    * @param rootScale A Scale object that specifies the degrees that will
    * be roots for the diatonic triads and chord belonging to the key.
    */
	public Key (Scale rootScale){
        this.root = rootScale.getNotes().get(0);
        this.rootScale = rootScale;
        this.chords = this.rootScale.chords();
        this.chords7 = this.rootScale.chords(7);
	}
}