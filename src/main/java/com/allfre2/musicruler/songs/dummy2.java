package com.allfre2.musicruler.songs;

import com.allfre2.musicruler.tools.io;
import java.util.Random;

public class dummy2{
    
 public static final Random rnd = new Random();

 public static final String[] cfgs = {
 	""
 	//Major
   // +"major = W W W ;"
   +"major = W W ;"
   +"W = (t) (D | SD);"
   +"W = (D | SD) (T | t);"
   +"T = I;"
   +"t = iii | vi;"
   +"SD = IV | ii;"
   +"D = V | V7 | viio;"
   //Minor
   // +"minor = w w w ;"
   +"minor = w w ;"
   +"w = (Tm | tm) (Dm | SDm);"
   +"w = (Dm | SDm) (Tm| tm);"
   +"Tm = i;"
   +"tm = III | VI;"
   +"SDm = iv | iio;"
   +"Dm = v | VII;"
   //Mixolydian
   // +"mixo = Wx Wx Wx ;"
   +"mixo = Wx Wx ;"
   +"Wx = (T | tx) (Dm | SD);"
   +"Wx = (Dm | SD) (tx);"
   +"tx = iiio | vi;"
   //Dorian
   // +"dorian = Wd Wd Wd ;"
   +"dorian = Wd Wd ;"
   +"Wd = (Tm | td) (Dm | SD);"
   +"Wd = (Dm | SD) (td);"
   +"td = III | vio;"
   //Frygian
   // +"frygian = Wf Wf Wf ;"
   +"frygian = Wf Wf ;"
   +"Wf = (Tm | tm) (Df | SDf);"
   +"Wf = (Df | SDf) (tm);"
   +"SDf = iv | bII;"
   +"Df = vo | vii;"
   //Lydian
   // +"lydian = Wl Wl Wl ;"
   +"lydian = Wl Wl ;"
   +"Wl = (T | t) (Dl | SDl);"
   +"Wl = (Dl | SDl) ( t);"
   +"SDl = #ivo | II;"
   +"Dl = V | vii;"
   // Modal Interchange
   +"modalx = Wmx Wmx;"
   +"Wmx = (tmx) (Dmx | SDmx);"
   +"Wmx = (Dmx | SDmx) (T | tmx);"
   +"Tmx = I | i;"
   +"tmx = iii | iiio | bIII | vi;"
   +"SDmx = IV | iv | ii | bII;"
   +"Dmx = V | v | V7 | bII7 | viio;"
 };

 static final String[] modes = {"major", "minor"};//, "dorian", "frygian", "mixo", "lydian"};

 public static void main(String[] args){
   Limerick poem = new Limerick(new FileDataSource("/home/allfre2/a.txt"));
 	CFG c = null;
  for(String cfg: cfgs){
  	try{
  	 c = new CFG(cfg);
  	}catch(Exception e){ e. printStackTrace(); }
  	 c.printGrammar();
  	 for(;;){
  	 	// System.out.println(c.genRandom("verse", 8) + "\n\n");
       String p = poem.generate();
       for(String s: p.split("\n")){
       String mode = modes[rnd.nextInt(modes.length)];
         System.out.println(c.genRandom("modalx",8));
         // System.out.println(c.genRandom(mode,8));
         System.out.println(s);
       }
  	 	if(!io.input().isEmpty()) break;
  	 }
  }
 }
}