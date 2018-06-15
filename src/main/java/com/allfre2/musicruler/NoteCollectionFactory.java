package com.allfre2.musicruler;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class NoteCollectionFactory{

    public static final Class[] scales =
    {
        MajorScale.class, DorianScale.class, PhrygianScale.class,
        LydianScale.class, MixolydianScale.class, NaturalMinorScale.class,
        MelodicMinorScale.class, HarmonicMinorScale.class, LocrianScale.class,
        PentatonicScale.class, ChromaticScale.class, WholeToneScale.class
    };

    public static final Class[] modes =
    {
        MajorScale.class, DorianScale.class, PhrygianScale.class,
        LydianScale.class, MixolydianScale.class, NaturalMinorScale.class,
        LocrianScale.class
    };

    public static final Class[] chords =
     {
        Major.class, Dom7.class, Major7.class,
        Major6.class, Minor.class, Minor7.class,
        Minor6.class, Minor7b5.class, MinorMajor7.class,
        Dim.class, Dim7.class, Aug.class, Aug7.class,
        Sus4.class, Sus2.class
    };

    private static final Map<Class, String> Notation;
    static
    {
        Notation = new HashMap<Class, String>();

        // Scales
        Notation.put(MajorScale.class, " Major");
        Notation.put(DorianScale.class, " Dorian");
        Notation.put(PhrygianScale.class, " Phrygian");
        Notation.put(LydianScale.class, " Lydian");
        Notation.put(MixolydianScale.class, " Mixolydian");
        Notation.put(NaturalMinorScale.class, " Natural Minor");
        Notation.put(MelodicMinorScale.class, " Melodic Minor");
        Notation.put(HarmonicMinorScale.class, " Harmonic Minor");
        Notation.put(LocrianScale.class, " Locrian");
        Notation.put(ChromaticScale.class, " Chromatic");
        Notation.put(PentatonicScale.class, " Pentatonic");
        Notation.put(WholeToneScale.class, " Whole Tone");
        //Chords
        Notation.put(Major.class, "");
        Notation.put(Dom7.class, Symbols.dominant7);
        Notation.put(Major7.class, Symbols.major7);
        Notation.put(Major6.class, Symbols.sixth);
        Notation.put(Minor.class, Symbols.minor);
        Notation.put(Minor7.class, Symbols.minor7);
        Notation.put(Minor6.class, Symbols.minorsixth);
        Notation.put(Minor7b5.class, Symbols.minor7b5);
        Notation.put(MinorMajor7.class, Symbols.minormajor7);
        Notation.put(Dim.class, Symbols.diminished);
        Notation.put(Dim7.class, Symbols.diminished7);
        Notation.put(Aug.class, Symbols.augmented);
        Notation.put(Aug7.class, Symbols.augmented7);
        Notation.put(Sus4.class, Symbols.sus4);
        Notation.put(Sus2.class, Symbols.sus2);

        Notation.put(NoteCollection.class, " Generic Note Collection");
    }

    public static List<Class> getScaleList(){
        return Arrays.asList(scales);
    }

    public static List<Class> getChordList(){
        return Arrays.asList(chords);
    }

    public static boolean isMinor(Chord c){
     if(Arrays.asList(new Class[]{
       Minor.class, Minor7.class, Minor6.class, Minor7b5.class,
       MinorMajor7.class, Dim.class, Dim7.class
     }).contains(c.getClass())){
      return true;
     }
     return false;
    }

    public static String getSymbol(NoteCollection c){
     return Notation.get(c.getClass());
    }

    private static boolean hasSameStructure(List<NoteI> a, List<NoteI> b){
     if(a.size() != b.size()) return false;
     for(int i = 0; i < a.size()-1; ++i){
      if(a.get(i).semitones(a.get(i+1))
         != b.get(i).semitones(b.get(i+1)))
        return false;
     }
     return true;
    }

    public static Scale makeScale(Class c, NoteI arg){
     Scale scale = null; // FIX this!
     try{
      scale = (Scale) c.getConstructor(NoteI.class).newInstance(arg);
     }catch(Exception e){
        e.printStackTrace();
     }
     return scale;
    }

    public static Chord makeChord(Class c, NoteI arg){
     Chord chord = null; // FIX this!
     try{
      chord = (Chord) c.getConstructor(NoteI.class).newInstance(arg);
     }catch(Exception e){
        e.printStackTrace();
     }
     return chord;
    }

    public static List<Scale> getAllModes(NoteI root){
     List<Scale> modalScales = new ArrayList<>();
     for (Class c: modes) {
        modalScales.add(makeScale(c, root));
     }
     return modalScales;
    }

    public static Chord makeChordFromList(List<NoteI> list){
       for (Class c: chords) {
         Chord chord = makeChord(c, list.get(0));
         if(hasSameStructure(list, chord.getNotes())){
           return chord;
         }
       }
       return new Major(list.get(0)); //dummy
    }
}
