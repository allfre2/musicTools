import com.allfre2.musicruler.*;
import com.allfre2.musicruler.utilities.*;
 
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Tests{

 public static void main(String[] args){
  // NoteTest();
  // ScaleTest();
  ChordTest();
  // semitoneTest();
  // ScaleDegreeTest(0);
  // ScaleDegreeTest(7);
  // KeyTest();
  // ProgressionTest(); // FAIL
  // ChordFunctionAnalysis();
  MelodyHarmonizationTest();
 }

 public static void NoteTest(){
  NoteI[][] notes = io.genRandomNotes(2,7);

    for(NoteI note: notes[0]){
     System.out.println("\n=== " + note + " ===\n");
     System.out.println("Overtones: ");
     System.out.println(note.Overtones());
       System.out.println("\n-- Major Intervals --");
       for (int i =1; i < 8; ++i) {
            System.out.print(note.major(i) + " ");
       }
       System.out.println("\n-- Minor Intervals --");
       for (int i =1; i < 8; ++i) {
            System.out.print(note.minor(i) + " ");
       }
       System.out.println("\n-- Augmented Intervals --");
       for (int i =1; i < 8; ++i) {
            System.out.print(note.aug(i) + " ");
       }
       System.out.println("\n-- Diminished Intervals --");
       for (int i =1; i < 8; ++i) {
            System.out.print(note.dim(i) + " ");
       }
     if( !io.input().isEmpty() ) return;
    }
 }

 public static void ScaleTest(){
  NoteI[] notes = io.genRandomNotes(1,8)[0];
    for (NoteI note: notes){
        System.out.println("== Scales for " + note + " ==");
     for (Class scale: NoteCollectionFactory.scales){
      try{
        Scale s = (Scale) scale.getConstructor(NoteI.class).newInstance(note);
        System.out.println(scale.getSimpleName() + " : " + s + " / " + s.name());
        for(int deg = 1; deg <= s.size(); ++deg){
            System.out.print("" + deg + ": " + s.degree(deg) + " ");
        }
        System.out.println("degreeOf(" + new Note("C") + ") = " + s.degreeOf(new Note("C")));
        System.out.println();
      }catch(Exception e){
        e.printStackTrace();
      }
     }
     if( !io.input().isEmpty() ) return;
    }
 }

 public static void ChordTest(){
  NoteI[] notes = io.genRandomNotes(1,8)[0];
  Chord prev = new Major(new Note("C"));
  for (NoteI note : notes) {
    Scale s = new MajorScale(note);
    System.out.println("== Chords for " + note + " ==");
    for (Class chord : NoteCollectionFactory.chords) {
      try{
        Chord c = (Chord) chord.getConstructor(NoteI.class).newInstance(note);
        System.out.println(chord.getSimpleName() + " : " + c + " / " + c.name());
        System.out.println(c.Overtones());
        System.out.println("Common notes: with prev: " + prev + " = " + c.commonNotes(prev));
        System.out.println("... Common Overtones = " + c.commonOvertones(prev));
        prev = c;
        System.out.println();
      }catch(Exception e){
        e.printStackTrace();
      }
    }
     if( !io.input().isEmpty() ) return;
  }
 }

 public static void semitoneTest(){
  NoteI[][] notes = io.genRandomNotes(3,8);
  for(int i = 0; i < notes.length; ++i){
   for(int j = 0; j < notes[i].length-1; ++j){
     System.out.println("Semitones between " + notes[i][j] + " and " + notes[i][j+1] + " = "
       + notes[i][j].semitones(notes[i][j+1]));
   }
  }
 }

 public static void ScaleDegreeTest(int tension){
  NoteI[] _notes = {new Note("C"), new Note("Bb"), new Note("Db"), new Note("E")};
  NoteI[] notes = io.genRandomNotes(1,2)[0];
  List<String> names;
  List<String> degrees;
  Scale tmpScale;
  for (NoteI note : notes) {
    for (Class scale : NoteCollectionFactory.modes) {
      try{
        Scale s = (Scale) scale.getConstructor(NoteI.class).newInstance(note);
        System.out.println(s.name());
        names = new ArrayList<>();
        degrees = new ArrayList<>();
        for(Chord c: s.chords(tension)){ // Play with the 7
          names.add(c.name());
          degrees.add(c.name(new MajorScale(note)));
        }
        System.out.println(names);
        System.out.println(degrees);
        System.out.println("=====");
        if( !io.input().isEmpty() ) return;
      }catch(Exception e){
        e.printStackTrace();
      }
    }
  }
 }

 public static void KeyTest(){
  // NoteI root = Note.random();
  System.out.println(new Note("Bbb").isEnharmonic(new Note("A")));
  NoteI root = new Note("c");
  KeyTest k = new KeyTest(root);
  System.out.println(k.getTriads());
  System.out.println();
  List<String> deg = new ArrayList<>();
  List<String> deg7 = new ArrayList<>();
  System.out.println((new Major(new Note("C"))).getClass() );
  System.out.println(((Chord)new Major(new Note("C"))).getClass() );
  System.out.println(((NoteCollection)new Major(new Note("C"))).getClass() );
  for (Chord c: k.getTriads()) {
    deg.add(c.name() + "(" + c.name(k.getRootScale()) + ")");
  }
  for (Chord c: k.getChords7()) {
    deg7.add(c.name() + "(" + c.name(k.getRootScale()) + ")");
  }
  System.out.println(deg.size() + " triads available");
  System.out.println(deg);
  System.out.println();
  System.out.println("Tonic Chords: " +
  k.getTonicChords()
   .stream()
   .map(c -> c.name(k.getRootScale()))
   .collect(Collectors.toList()) + "\n");

  System.out.println("SubDominant Chords: " +
  k.getSubDominantChords()
   .stream()
   .map(c -> c.name(k.getRootScale()))
   .collect(Collectors.toList()) + "\n");

  System.out.println("Dominant Chords: " +
  k.getDominantChords()
   .stream()
   .map(c -> c.name(k.getRootScale()))
   .collect(Collectors.toList()) + "\n");

  int x = 5;

  System.out.println(x + " Random verses: ");
  for(int i = 0; i < x; ++i){
    System.out.println(k.getVerse().stream().map(c->c.name(k.getRootScale())).collect(Collectors.toList()));
  }
  System.out.println(deg7.size() + " 7th chords available");
  System.out.println(deg7);
  System.out.println("=============");
  System.out.println("Chan, Chan, Chaaaaaaan! ...");
  Chord c2 = k.getRootScale().chord(2);
  System.out.println(c2);
  System.out.println(k.chordListToString(k.getAdjChordsTo(c2)));
  k.printAdjChords();

 }

 public static void ProgressionTest(){
  KeyTest k = new KeyTest("Eb");
  System.out.println("====");
  System.out.println("Common Chords between C and D");
  System.out.println(k.commonChordsWith(new KeyTest("D")));
  System.out.println("====");
  Chord start = k.getRootScale().chord(1);
  Chord finish = k.getRootScale().chord(5);

  System.out.println(
  k.getTriads()
   .stream()
   .map(c -> c.name(k.getRootScale()))
   .collect(Collectors.toList()));

  System.out.println(
  k.getChords7()
   .stream()
   .map(c -> c.name(k.getRootScale()))
   .collect(Collectors.toList()));

  k.printModeTable(0);
  io.input();
  int len = 3;
  int limit = 15;
  int i = 0;
  List<List<Chord>> all = k.Paths(start, finish, len, limit);
  // k.printAdjChords();
  System.out.println(all.size() + " progressions");
  for(List<Chord> progression: all){
    System.out.println("Progression #" + (++i) + " of max length: "
                        + len + " from " + start.name() + " to "  + finish.name());
    System.out.println(k.chordListToString(progression));
    List<String> p =
    progression.stream()
               .map(chord -> chord.name())
               .collect(Collectors.toList());
    System.out.println(p);
    if( !io.input().isEmpty() ) return;
  }
 }

  public static void ChordFunctionAnalysis(){
   List<Scale> modes = NoteCollectionFactory.getAllModes(new Note("C"));
   KeyTest k = new KeyTest(new Note("C"));
   for(Scale scale: modes){
    k.printModeTable(0);
    System.out.println(scale.name() + "\n");
    for(int i = 1; i < 8; ++i){
     Chord chord = scale.chord(i);
     System.out.println(chord.name(modes.get(0)) + " " + chord.name());
     for(NoteI note: chord.getNotes()){
      // System.out.println(note.Overtones());
     }
     System.out.println("Common overtones with I: " + chord.commonOvertones(modes.get(0).chord(1)));
     System.out.println("Common notes with I: " + chord.commonNotes(modes.get(0).chord(1)));
     System.out.println("--------------------------");  
    }
    if( !io.input().isEmpty() ) return;
   }
  }

  public static void MelodyHarmonizationTest(){

    String[] melodies = {
      //Last note is the key
      "eb d c b c bb ab g eb",
      "d d f# b a c# bb e f g"
    };
    Arrays.asList(melodies)
          .stream()
          .forEach((m) -> {
            List<String> notes = new ArrayList<>(Arrays.asList(m.split(" ")));
            KeyTest key = new KeyTest(notes.get(notes.size()-1));
            notes.remove(notes.size()-1);
            System.out.println("Melody: " + notes);
            System.out.println("Key: " + key);
            notes.stream()
                 .forEach((note) -> {
                  System.out.print(new Note(note));
                  System.out.println(
                   key.getTriads()
                      .stream()
                      .filter((chord) ->
                        chord.getNotes().contains(new Note(note)))
                      .map(c -> c.name(key.getRootScale()))
                      .collect(Collectors.toList()));
                 });
          });
  }
}
