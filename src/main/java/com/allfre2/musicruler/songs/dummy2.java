package com.allfre2.musicruler.songs;

import com.allfre2.musicruler.tools.io;

public class dummy2{
    
 public static final String[] cfgs = {
 	"verse = prog | (prog | ^);"
   +"prog = chord | (chord | ^);"
   +"chord = 1;"
   +"chord = 2;"
   +"chord = 3;"
   +"chord = 4;"
   +"chord = 5;"
   +"chord = 6;"
   +"chord = 7;"
   +"1 = I (V 5) | (V7 5) | (iii 3) | (vi 6) | (ii 2) | (viio 7) | (IV 4);"
   +"2 = ii (V 5) | (V7 5) | (IV 4) | (bII7 (2 | 5)) | (vi 6) | (viio 7);"
   +"3 = iii (vi 6) | (V 5) | (V7 5) | (I 1);"
   +"4 = IV (V 5) | (V7 5) | (viio 7) | (ii 2);"
   +"5 = (V | V7) (I 1)| (iii 3) | (vi 6) | (viio 7);"
   +"6 = vi (IV 4) | (I 1) | (iii 3) | (V 5);"
 };

 public static void main(String[] args){
 	CFG c = null;
  for(String cfg: cfgs){
  	try{
  	 c = new CFG(cfg);
  	}catch(Exception e){ e. printStackTrace(); }
  	 c.printGrammar();
  	 for(;;){
  	 	System.out.println(c.genRandom("verse", 8) + "\n\n");
  	 	if(!io.input().isEmpty()) break;
  	 }
  }
 }
}