package com.allfre2.musicruler;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized.Parameter;

@RunWith(Parameterized.class)
public class ChordTest{
    
    @Parameters
    public static Collection<String> testCases(){
     // Expected, actual pairs
     String[] tests = {
     	"C", "Dbb", "F", "E#", "F#", "Gb", "A#", "Bb",
     	"C#", "Db", "G#", "Ab", "Cb", "B", "Fx", "G",
     	"D", "Ebb", "D#", "Eb"
     }; 
     return Arrays.asList(tests);
    }

    @Parameter(0)
    public String arg;

    @Test
    public void equalsTest(){
     NoteI note = new Note(arg);
     for(Scale scale: NoteCollectionFactory.getAllModes(note)){
      for(Chord chord: scale.chords(0)){

      }

      for(Chord chord7: scale.chords(7)){
      	
      }
     }
    }
}

