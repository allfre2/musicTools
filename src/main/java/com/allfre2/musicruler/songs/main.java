package com.allfre2.musicruler.songs;

import com.allfre2.musicruler.tools.io;

public class main{

 public static void main(String[] args){

 	String test = 
 	 "A = b B | C A 5 (F-3-1 Z (R | E-0-0 x) s | o);" +
 	 "F = C o;" +
 	 "F-3-1 = F | R;" +
 	 "R = 1 | 2 | 3 | 4;" +
 	 "Z = R s (o (A | 0));" +
 	 "x = > | <;" +
 	 "o = 6 (o | E);" +
 	 "E-0-0 = E-0-0;" +
 	 "s = x x F;" +
 	 "B = i;" +
 	 "b = (r) | " + CFG.Empty + ";" +
 	 "Z = u | (d d d);"; // fix mark node as a group, (parentesis)

 	String test2 = 
 	 "phoneNumber = o area d d d - d d d d;" +
 	 "o = 1 | ^;" +
 	 "d = 0 | 1 | 2 |3 |4 |5 |6 |7 |8|9;" +
 	 "area = 809 | 829 | 849;";

 	String test3 = 
 	 "prog = ( ( seq (((y)))) | chord) (chord | ^) T (prog |^);  " +
 	 "chord = T | t | SD | D;" +
 	 "seq = (T | t) (SD | D) (T | t | ^) (SD | D | ^);" +
 	 "T = I | i | Imaj7 | i-7;" +
 	 "t = iii | vi;" +
 	 "SD = ii | ii-7 | IV | IVmaj7 | iv;" +
 	 "D = V | v | V7 | viio ;";

  String test4 = "poem = verse-1-23-23 nl verse-9-8-7 nl verse-2-23-5;";

  String test5 =  "verse = prog;"
                 +"prog = (T | t | D | SD ) (T | t | SD ) (D | t | SD) T;"
                 +"T = I | Imaj7;"
                 +"T = i | i-7;"
                 +"t = iii | iii-7 | vi | bVI | bIII | bIII+;"
                 +"SD = IV | iv | ii | iio | ii-7b5 | ii-7;"
                 +"D = V | V7 | viio | viio7 | v | v-7;"
                 +"n = 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9;";

  try{
  // CFG cfg = new CFG("G = A B C; \n\n== A \n= B\n| C|G;\n\n\nF= (V)| (A| C);; =A;G  ");
  CFG cfg = new CFG("G = A B C; \n\nA \n= B\n| C|G;\n\n\nF= (V)| (A| C);\n\nF=(G) (Z) V b;");
  System.out.println("Random: " + cfg.genRandom("F", 5));

  CFG t = new  CFG(test2);
  System.out.println("Random: " + t.genRandom("phoneNumber", 5));

  CFG t3 = new  CFG(test3);
  System.out.println("Random: " + t3.genRandom("prog", 5));

  CFG t4 = new  CFG(test4);
  System.out.println("Random: " + t4.genRandom("poem", 5));

  CFG t5 = new CFG(test5);
  for(int i = 0; i < 6; ++i){
   System.out.println("Random: " + t5.genRandom("verse", 7));
   io.input();
  }

  Lyrics poem = new Limerick(new URLDataSource("https://www.gnu.org/licenses/gpl-3.0.txt"));
  // Limerick poem = new Limerick(new FileDataSource("/home/allfre2/a.txt"));
  System.out.println(poem.generate() + "\n");
  System.out.println(poem.generate() + "\n");
  System.out.println(poem.generate() + "\n");
  }catch(InvalidCFGException e){
  	e.printStackTrace();
  }
 }
}
