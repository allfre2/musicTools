package com.allfre2.musicruler.songs;

import com.allfre2.musicruler.tools.io;

public class dummy2{
    
 public static final String[] cfgs = {
   "verse = chord;"
   +"chord = 1 | 2 | 3 | 4 | 5 | 6 | 7;"
   +"1 = I  5 | 3 | 6 | 2 | 7 | 4;"
   +"2 = (ii (5 | 4 | 6 | 7)) | (bII7 5 );"
   +"3 = iii 6 | 5 | 1 | 7;"
   +"4 = IV 5 | 7 | 2 | 6;"
   +"5 = (V | V7 | bII7) 1 | 3 | 6 | 7;"
   +"6 = vi 4 | 1 | 3 | 5;"
   +"7 = (viio | bVII) 1 | 5 | 3;"
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