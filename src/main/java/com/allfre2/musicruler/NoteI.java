package com.allfre2.musicruler;

import java.util.List;

/**
 * NoteI interface.
 * <p>
 * An interface to enable polimorphism for various
 * implementations of a "musical note".
 * @see Note
 */
public interface NoteI{

/**
 * Obtain an int representation of a note.
 * @return returns an <code> int </code> corresponding to the value of the note
 */
 public int getNoteIndex();

/**
 * Accidentals are represented by an int between -2 and 2 that
 * is used to index an array (<code>modifiers</code>)
 * for the corresponding unicode symbol in the {@link Symbols} class.
 * @return return an int corresponding to an "accidental"
 * 𝄪, ♯, ♮, ♭, 𝄫.
 */
 public int getAltIndex();

/**
 * This method provides a way to generate the set of overtones that the
 * <code>NoteI</code> instance produces.
 * @return returns a List of notes corresponding to the series of overtones
 * in order of strenght in the overtone series.
 */
 public List<NoteI> Overtones();

/**
 * <p>
 * Computes the semitones between the current note and the note passed as argument.
 * @param note a <code>NoteI</code> implementation.
 * @return returns an int representing semitones.
 */
 public int semitones(NoteI note);

/**
 * This method can be used to check if to notes are enharmonic
 * within the occidental tempered system.
 * @param note a <code>NoteI</code> to be compared to.
 * @return returns true if the current note and the argument are enharmonic.
 */
 public boolean isEnharmonic(NoteI note);

 public NoteI aug(int interval);
 public NoteI major(int interval);
 public NoteI minor(int interval);
 public NoteI dim(int interval);
 public NoteI just(int interval);
}
