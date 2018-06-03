package com.allfre2.musicruler.tools;

import com.allfre2.musicruler.*;
import java.util.Random;
import java.util.Scanner;

public class io{

  public static void sleep(int t){
    try{
        Thread.sleep(t);
    }catch(InterruptedException e){
        e.printStackTrace();
    }
  }

  public static String input(){
    Scanner keyboard = new Scanner(System.in);
    String input = "";
    try{
      input = keyboard.nextLine();
    }catch(Exception e){
        e.printStackTrace();
    }
    return input;
  }

  public static NoteI[][] genRandomNotes(int nArrays, int nNotes){
   NoteI[][] notes = new NoteI[nArrays][nNotes];
   for(int i = 0;i<nArrays;++i){
    for(int j = 0;j<nNotes;++j){
     notes[i][j] = Note.random();
    }
   }
   return notes;
  }
}
