package com.allfre2.musicruler.songs;

public class Soundex{

  public static void main(String[] args){
      //System.out.println(soundex("Soundex"));
      //System.out.println(soundex("Example"));
      //System.out.println(soundex("Sownteks"));
      //System.out.println(soundex("Ekzampul"));
//      String[] words = {"bar", "star", "caviar", "mania", "car", "strange",
//      "fringe", "sting", "trim", "is", "tree", "computing", "studying", "playing",
//      "practicing", "applying", "guarding", "standing", "dying"};
String[] words = {"zach", "zac", "lake", "bake", "brad", "lad", "horrible",
"great", "fuzzy"};

//      for(String word: words)
//       System.out.println(word + " = " + soundex(word));

      for(String s1: words)
       for(String s2: words)
        System.out.println(s1 + " - " + s2 + " = "
                        + soundex(s1) + " - " + soundex(s2) + " = "
                        + diff(soundex(s1), soundex(s2))
                        + ", ld(" + s1 + ", " + s2 + ") = " + ld(s1, s2)
                        + " distance ... = " + distance(s1,s2));
    }

  private static String getCode(char c){
    switch(c){
      case 'B': case 'F': case 'P': case 'V':
        return "1";
      case 'C': case 'G': case 'J': case 'K':
      case 'Q': case 'S': case 'X': case 'Z':
        return "2";
      case 'D': case 'T':
        return "3";
      case 'L':
        return "4";
      case 'M': case 'N':
        return "5";
      case 'R':
        return "6";
      default:
        return "";
    }
  }

  public static String soundex(String s){
    String code, previous, soundex;
    code = s.toUpperCase().charAt(0) + "";

    previous = getCode(s.toUpperCase().charAt(0));

    for(int i = 1;i < s.length();i++){
      String current = getCode(s.toUpperCase().charAt(i));
      if(current.length() > 0 && !current.equals(previous)){
        code = code + current;
      }
      previous = current;
    }
    soundex = (code + "0000").substring(0, 4);
    return soundex;
  }

  public static int diff(String str1, String str2){
   int result = 0;

   String s1 = new String(str1);
   String s2 = new String(str2);

   if(s1.equals(s2)) result = 4;
   else{
    String sub1 = s1.substring(1, 4);
    String sub2 = s1.substring(2, 4);
    String sub3 = s1.substring(1, 3);
    String sub4 = s1.substring(1, 2);
    String sub5 = s1.substring(2, 3);
    String sub6 = s1.substring(3, 4);

    if(s2.indexOf(sub1) > -1)
     result = 3;
    else if(s2.indexOf(sub2) > -1)
     result = 2;
    else if(s2.indexOf(sub3) > -1)
     result = 2;
    else{
      if(s2.indexOf(sub4) > -1)
       result++;
      if(s2.indexOf(sub5) > -1)
       result++;
      if(s2.indexOf(sub6) > -1)
       result++;
    }
    if(s1.substring(0, 1) == s2.substring(0, 1))
     result++;
   }
   return (result == 0) ? 1 : result;
  }

  // Find minimum of 3 ints
  protected static int min(int a, int b, int c){
    int mi;

    mi = a;
    if (b < mi) {
      mi = b;
    }
    if (c < mi) {
      mi = c;
    }
    return mi; 
  }
  // Levenshtein Distance (https://en.wikipedia.org/wiki/Levenshtein_distance)
  // Code "Borrowed" from https://people.cs.pitt.edu/~kirk/cs1501/Pruhs/Spring2006/assignments/editdistance/Levenshtein%20Distance.htm
  public static int ld(String s, String t){
  int d[][]; // matrix
  int n; // length of s
  int m; // length of t
  int i; // iterates through s
  int j; // iterates through t
  char s_i; // ith character of s
  char t_j; // jth character of t
  int cost; // cost

    // Step 1
    n = s.length ();
    m = t.length ();
    if (n == 0) {
      return m;
    }
    if (m == 0) {
      return n;
    }
    d = new int[n+1][m+1];

    // Step 2
    for (i = 0; i <= n; i++) {
      d[i][0] = i;
    }

    for (j = 0; j <= m; j++) {
      d[0][j] = j;
    }

    // Step 3
    for (i = 1; i <= n; i++) {

      s_i = s.charAt (i - 1);

      // Step 4
      for (j = 1; j <= m; j++) {

        t_j = t.charAt (j - 1);

        // Step 5
        if (s_i == t_j) {
          cost = 0;
        }
        else {
          cost = 1;
        }

        // Step 6
        d[i][j] = min (d[i-1][j]+1, d[i][j-1]+1, d[i-1][j-1] + cost);
      }
    }

    // Step 7
    return d[n][m];
  }

  public static int distance(String s1, String s2){
    return 4 - diff(soundex(s1), soundex(s2)) + ld(s1, s2);
  }
}
