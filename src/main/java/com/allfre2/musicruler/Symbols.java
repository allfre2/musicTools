package com.allfre2.musicruler;

import java.util.HashMap;

public class Symbols{
    public static final String natural = "♮";
    public static final String sharp = "♯";
    public static final String flat = "♭";
    public static final String doubleSharp = "𝄪";
    public static final String doubleFlat = "𝄫";
    public static final String sixth = "6";
    public static final String minorsixth = "-6";
    public static final String augmented = "+";
    public static final String augmented7 = "+7";
    public static final String diminished = "o";
    public static final String diminished7 = "o7";
    public static final String dominant7 = "7";
    public static final String major = "maj";
    public static final String major7 = "maj7";
    public static final String minor = "-";
    public static final String minor7 = "-7";
    public static final String minor7b5 = "-7b5";
    public static final String minormajor7 = "-maj7";
    public static final String sus4 = "sus4";
    public static final String sus2 = "sus2";

    public static final String[][] modifiers = {
        {"bb", doubleFlat},   // double flat , -2, (i - 2)
        {"b",  flat},    // flat        , -1
        {"=",  natural},    // natural     ,  0
        {"#",  sharp},    // sharp       ,  1
        {"x", doubleSharp},   // double sharp,  2
     {"?","?"}     // Error, need triple sharp?! or triple flat?!
    };

    public static final String[] romanNumerals = {"I", "II", "III", "IV", "V", "VI", "VII"};

    public static final String[] figures = {"𝅝", "𝅗𝅥", "𝅘𝅥", "𝅘𝅥𝅮", "𝅘𝅥𝅯", "𝅘𝅥𝅰", "𝅘𝅥𝅱"};
    public static final String[] figureNames = {
        "whole note",
        "half note",
        "quarter note",
        "eighth note",
        "sixteenth note",
        "thirty-second note",
        "sixty-fourth note"
    };
    public static final HashMap<Integer, String> figureTable;
    static{
        figureTable = new HashMap<>();
        figureTable.put(1, figures[0]);
        figureTable.put(2, figures[1]);
        figureTable.put(4, figures[2]);
        figureTable.put(8, figures[3]);
        figureTable.put(16, figures[4]);
        figureTable.put(32, figures[5]);
        figureTable.put(64, figures[6]);
    }
}
