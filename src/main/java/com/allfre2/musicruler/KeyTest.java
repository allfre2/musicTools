package com.allfre2.musicruler;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.Comparator;

public class KeyTest{

    protected final NoteI root;
    protected final Scale rootScale;
    protected final List<Chord> chords;   // 25
    protected final List<Chord> chords7;  // 31
    protected final List<Chord> allChords;// 56
    protected HashMap<Chord, TreeSet<Chord>> adjChords;
    protected Comparator<Chord> comparator;

    public KeyTest (NoteI root){
        this.root = root;
        this.rootScale = new MajorScale(this.root);
        this.chords = new ArrayList<>();
        this.chords7 = new ArrayList<>();
        this.allChords = new ArrayList<>();
        List<Scale> modes = NoteCollectionFactory.getAllModes(this.root);
        for (Scale scale: modes) {
         for (Chord c: scale.chords()) {
          if( !chords.contains(c) ){
            chords.add(c);
            allChords.add(c);
          }
         }
         for (Chord c: scale.chords(7)) {
          if( !chords7.contains(c) ){
            chords7.add(c);
            allChords.add(c);
          }
         }
        }
        generateAjdChords();
    }

    public final NoteI getRoot(){
     return this.root;
    }

    public final Scale getRootScale(){
     return this.rootScale;
    }

    public final List<Chord> getTriads(){
     return chords;
    }

    public final List<Chord> getChords7(){
     return chords7;
    }

    public final List<Chord> getAllChords(){
     return allChords;
    }

    private Comparator<Chord> createComparator(Chord keyChord){
     return (Chord a, Chord b) -> b.commonOvertones(keyChord)+b.commonNotes(keyChord)
                                - a.commonOvertones(keyChord)+a.commonNotes(keyChord);
    }

    public void generateAjdChords(){
     adjChords = new HashMap<Chord, TreeSet<Chord>>();
     for (Chord c: allChords) {
      TreeSet<Chord> treeset =
       new TreeSet<>(createComparator(c));
        for (Chord cc: allChords) {
         if(!cc.equals(c)){
          treeset.add(cc);
         }
        }
      adjChords.put(c, treeset);
     }
    }

    public final List<Chord> getAdjChordsTo(Chord chord){
     return new ArrayList<Chord>(adjChords.get(chord));
    }

    public List<String> chordListToString(List<Chord> chords){
     return chords
             .stream()
             .map(c -> c.name(this.rootScale))
             .collect(Collectors.toList());
    }

    public void printAdjChords(){
     int i = 0;
     for (Chord chord: allChords) {
       System.out.println("Chord #" + (++i) + ": " + chord + "(" + chord.name(this.rootScale) + ")");
       System.out.println(chordListToString(getAdjChordsTo(chord)));
       com.allfre2.musicruler.utilities.io.input();
     }
    }

    public List<List<Chord>> Paths(Chord start, Chord end, int max_len, int limit){
      List<List<Chord>> paths = new ArrayList<>();
      List<Chord> path = new ArrayList<>();
      path.add(start);
      paths.add(path);
      paths = _Paths(paths, end, max_len-1, limit);
      return paths;
    }

    public List<List<Chord>> _Paths(List<List<Chord>> paths, Chord end, int max_len, int limit){
      List<List<Chord>> newPaths = new ArrayList<>();
      for(int i = 0; i < paths.size(); ++i){
        List<Chord> path = paths.get(i);
        if(path.get(path.size()-1).equals(end)){ // Terminal path
          newPaths.add(path);
          continue;
        }
        Chord lastVisited = path.get(path.size()-1);
        for(Chord chord: getAdjChordsTo(lastVisited)){
          if(!path.contains(chord)){
            List<Chord> l = new ArrayList<>();
            l.addAll(path);
            l.add(chord);
            newPaths.add(l);
          }
        }
      }
      if(max_len > 0){
        return _Paths(newPaths, end, max_len-1, limit);
      }
      else{
       List<List<Chord>> finalPaths = new ArrayList<>();
       for(List<Chord> path: newPaths){
        if(path.get(path.size()-1).equals(end)){
          finalPaths.add(path);
        }
       }
       return finalPaths;
      }
    }

    @Override
    public String toString(){
     return new Major(this.root).toString();
    }

    public void printModeTable(int type){
      String space = " ";
     List<Scale> modes = NoteCollectionFactory.getAllModes(this.root);
     Scale base = modes.get(0);
     modes.stream()
          .forEach((Scale scale) -> {
            String format = "%1$-15s%2$-6s%3$-6s%4$-6s%5$-6s%6$-6s%7$-6s%8$-6s\n";
             System.out.format(format,
                NoteCollectionFactory.getSymbol(scale),
                scale.chord(1).name(base),
                scale.chord(2).name(base),
                scale.chord(3).name(base),
                scale.chord(4).name(base),
                scale.chord(5).name(base),
                scale.chord(6).name(base),
                scale.chord(7).name(base));
          });
    }
}
