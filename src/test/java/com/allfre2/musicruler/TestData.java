package com.allfre2.musicruler;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class TestData{
    
   public static List<NoteI> genRandomNotes(int max){
   	 Random rnd = new Random();
     List<NoteI> tests = new ArrayList<>();
     for(int i = 0; i < max; ++i){
      tests.add(Note.random());
     }
     return tests;
   }

   public static List<String> genRandomNoteStrings(int max){
   	 List<NoteI> notes = genRandomNotes(max);
     List<String> tests = new ArrayList<>();
     for(int i = 0; i < max; ++i){
      tests.add(notes.get(i).toString());
     }
     return tests;
   }
}