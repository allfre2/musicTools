package com.allfre2.musicruler;

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
        {"##", doubleSharp},   // double sharp,  2
     {"?","?"}     // Error, need triple sharp?! or triple flat?!
    };

    public static final String[] romanNumerals = {"I", "II", "III", "IV", "V", "VI", "VII"};
}
