package com.allfre2.musicruler;

import java.util.List;
import java.util.ArrayList;

public class Key extends AbstractKey{

	public Key (Scale rootScale){
        this.root = rootScale.getNotes().get(0);
        this.rootScale = rootScale;
        this.chords = this.rootScale.chords();
        this.chords7 = this.rootScale.chords(7);
	}
}