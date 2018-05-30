package com.allfre2.musicruler;

import java.util.List;

import org.junit.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized.Parameter;

@RunWith(Parameterized.class)
public class ScaleTest{
    
    private final static int maxTests = 30;

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
      Assert.assertTrue(scale.equals(NoteCollectionFactory.makeScale(scale.getClass(),note)));
     }
    }
 
    @Test
    public void hashCodeTest(){
     NoteI note = new Note(arg);
     for(Scale scale: NoteCollectionFactory.getAllModes(note)){
      Assert.assertTrue(scale.hashCode() ==
      	 (NoteCollectionFactory.makeScale(scale.getClass(),note)).hashCode());
     }
    }

    @Test
    public void toStringTest(){
     NoteI note = new Note(arg);
     for(Scale scale: NoteCollectionFactory.getAllModes(note)){
      Assert.assertTrue(scale.toString()
      	                     .equals(NoteCollectionFactory.makeScale(scale.getClass(),note)
      	                     .toString()));
     }
    }

    @Test
    public void degreeTest(){
     for(Scale scale: NoteCollectionFactory.getAllModes(new Note(arg))){
      for(NoteI note: scale.getNotes()){
     	Assert.assertTrue(scale.getNotes().indexOf(note)+1 == scale.degreeOf(note));
     	Assert.assertTrue(scale.degree(scale.getNotes().indexOf(note)+1).equals(note));
      }
     }
    }
}

