package com.allfre2.musicruler.tools;

public class main{

 public static void main(String[] args){

 	String test = 
 	 "A = b B | C A 5 (F Z (R | E x) s | o);" +
 	 "F = C o;" +
 	 "F = F | R;" +
 	 "R = 1 | 2 | 3 | 4;" +
 	 "Z = R s (o (A | 0));" +
 	 "x = > | <;" +
 	 "o = 6 (o | E);" +
 	 "E = E;" +
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

  try{
  // CFG cfg = new CFG("G = A B C; \n\n== A \n= B\n| C|G;\n\n\nF= (V)| (A| C);; =A;G  ");
  CFG cfg = new CFG("G = A B C; \n\nA \n= B\n| C|G;\n\n\nF= (V)| (A| C);\n\nF=(G) (Z) V b;");
  System.out.println("Random: " + cfg.genRandom("F", 5));

  CFG t = new  CFG(test2);
  System.out.println("Random: " + t.genRandom("phoneNumber", 5));

  CFG t3 = new  CFG(test3);
  System.out.println("Random: " + t3.genRandom("prog", 5));

  }catch(InvalidCFGException e){
  	e.printStackTrace();
  }
 }
}
