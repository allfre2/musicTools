package com.allfre2.musicruler;

import java.util.List;

import org.junit.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized.Parameter;

@RunWith(Parameterized.class)
public class ChordTest{
    
    private final static int maxTests = 25;

    @Parameters
    public static List<String> testCases(){
     return TestData.genRandomNoteStrings(maxTests);
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

