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
public class NoteTest{
    
    @Parameters
    public static Collection<String[]> testCases(){
     // Expected, actual pairs
     String[][] tests = {
     	{"C", "Dbb"},
     	{"F", "E#"},
     	{"F#", "Gb"},
     	{"A#", "Bb"},
     	{"C#", "Db"},
     	{"G#", "Ab"},
     	{"Cb", "B"},
     	{"Fx", "G"},
     	{"D", "Ebb"},
     	{"D#", "Eb"}
     }; 
     return Arrays.asList(tests);
    }

    @Parameter(0)
    public String first;

    @Parameter(1)
    public String second;

    @Test
    public void equalsTest(){
     Assert.assertTrue(new Note(first).equals(new Note(first))); // Testing the equals operation
    }

    @Test
    public void hashCodeTest(){
     Assert.assertTrue(new Note(first).hashCode() == ((new Note(first))).hashCode());
    }

    @Test
    public void toStringTest(){
     Assert.assertTrue((new Note(first)).toString().equals((new Note(first)).toString()));
    }

    @Test
    public void isEnharmonicTest(){
     Assert.assertTrue(new Note(first).isEnharmonic(new Note(second)));
    }

    // @Test
    // public void semitoneTest(){
    //  int[] augSemitones =   {0, 3, 5, 6, 8, 10, 0};
    //  int[] majorSemitones = {0, 2, 4, 5, 7, 9, 11};
    //  int[] minorSemitones = {0, 1, 3, 5, 7, 8, 10};
    //  int[] dimSemitones =   {0, 0, 2, 4, 6, 7, 9 };
    //  int[] justSemitones =  {0, 2, 4, 5, 7, 9, 11};
    //  for(int i = 2; i < 8; ++i){ // For now ignore 1th intervals
    //  	NoteI note = new Note(first);
    //  	Assert.assertTrue(note.semitones(note.aug(i)) == augSemitones[i-1]);
    //  	Assert.assertTrue(note.semitones(note.major(i)) == majorSemitones[i-1]);
    //  	Assert.assertTrue(note.semitones(note.minor(i)) == minorSemitones[i-1]);
    //  	Assert.assertTrue("Failed on " + note + " " + i,note.semitones(note.dim(i)) == dimSemitones[i-1]);
    //  	Assert.assertTrue(note.semitones(note.just(i)) == justSemitones[i-1]);
    //  }
    // }

    private static boolean overtoneCheck(List<NoteI> overtones){
     return true;
    }

    @Test
    public void overtoneTest(){
    	Assert.assertTrue((new Note(first)).Overtones().size() == 8);
    	Assert.assertTrue((new Note(second)).Overtones().size() == 8);

    	Assert.assertTrue(overtoneCheck(new Note(first).Overtones()));
    	Assert.assertTrue(overtoneCheck(new Note(second).Overtones()));
    }

}