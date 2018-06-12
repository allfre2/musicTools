package com.allfre2.musicruler;

import java.util.List;

/**
 * An Interface to be implemented by audio packages to play notes and chords.
 * <p>
 * // Talk about the fields...
 */
public abstract class AudioOutput{

    protected String instrument;
    protected String timeUnitStr;
    protected int timeUnit;
    protected double secondsPerTimeUnit;
    protected int beatsPerMeasure;

    public void setTimeUnit(int figure){
     this.timeUnitStr = Symbols.figureTable.get(figure);
     if(this.timeUnitStr == null){
        this.timeUnitStr = Symbols.figureTable.get(4); // quarter note
        this.timeUnit = 4;
     }else{
        this.timeUnit = figure;
     }
    }

    public void setSecondsPerTimeUnit(double seconds){
     this.secondsPerTimeUnit = seconds;
    }

    public void setTimeSignature(int number, int figure){
     this.beatsPerMeasure = number < 0 ? 4 : number;
     setTimeUnit(figure);
    }

    public void setInstrument(String instrument){
     this.instrument = instrument;
    }

    // Abstract Methods
    public abstract void play(NoteCollection notes, int figure);
    public abstract void play(List<Chord> chords, int figure);
}
