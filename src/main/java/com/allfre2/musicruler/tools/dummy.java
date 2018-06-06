package com.allfre2.musicruler.tools;

import java.util.HashMap;
import java.util.TreeSet;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

import java.util.LinkedList;

public class dummy{
    
 public static void main(String[] args){
   // URLDataSource.testing("https://en.wikipedia.org/wiki/Fur");

// StringMarkov M = new StringMarkov(new URLDataSource("https://www.ntu.edu.sg/home/ehchua/programming/java/JavaServerPages.html"));
// StringMarkov M = new StringMarkov(new URLDataSource("https://en.wikipedia.org/wiki/Fur"));
   StringMarkov m =
    new StringMarkov(new URLDataSource("https://www.gnu.org/licenses/gpl-3.0.txt"));
   // // m.add(new URLDataSource("https://www.gnu.org/licenses/fdl-1.3.txt"));   StringMarkov m =
   //  // new StringMarkov(new FileDataSource("Sample-text-file-10kb.txt"));
   //  // m.add(new URLDataSource("https://www.sample-videos.com/text/Sample-text-file-10kb.txt"));
    System.out
     .println(m
      .generate(59, 4)
      .stream()
      .map(t -> t.get() + " ")
      .reduce("", (a, b) -> a + b));
 }
}