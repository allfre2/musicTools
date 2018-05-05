package com.allfre2.musicruler;

import java.util.List;

public interface NoteI{

 public int getNoteIndex();
 public int getAltIndex();

 public List<NoteI> Overtones();

 public int semitones(NoteI note);
 public boolean isEnharmonic(NoteI note);

 public NoteI aug(int interval);
 public NoteI major(int interval);
 public NoteI minor(int interval);
 public NoteI dim(int interval);
 public NoteI just(int interval);
}
