package com.allfre2.musicruler.tools;

import com.allfre2.musicruler.*;
import java.util.Random;
import java.util.Scanner;

/**
 * Input Ouput utilities for debuging.
 */
public class io{

 /**
  * Wraps the Sleep method from the Thread class.
  * @param t The time to sleep in milliseconds.
  */
  public static void sleep(int t){
    try{
        Thread.sleep(t);
    }catch(InterruptedException e){
        e.printStackTrace();
    }
  }

 /**
  * Get input from keyboard with the Scanner class using
  * System.in.nextLine
  * @return A String with the user's input.
  */
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

 /**
  * Generates arrays of random notes using the {@link Note#random} static method.
  * @param nArrays How many arrays are to be generated.
  * @param nNotes How many notes are going to generated per array.
  * @return Returns a NoteI[][] containing the randomly generated notes.
  */
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
