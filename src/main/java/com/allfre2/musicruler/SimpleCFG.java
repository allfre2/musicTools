package com.allfre2.musicruler;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SimpleCFG implements CFG<Chord>{

   protected Scale key;
   protected HashMap<Object, ProductionRule> grammar;
   protected String initialSymbol = "Bridge"; // Song Structure

   public SimpleCFG(Scale scale){

    key = scale;
    grammar = new HashMap<>();

    grammar.put("Brigde", () ->
    	Arrays.asList(new String[]{
     	 "intro",
     	 "verse","chorus",
     	 "verse","chorus",
     	 "middle",
     	 "chorus","chorus",
     	 "outro"
        }));

    Random rnd = new Random();
    grammar.put("verse", () ->
    	Arrays.asList(new String[][]{
    	 {"A", "B", "A", "B"},
    	 {"A", "A", "B", "B"}
    	}[rnd.nextInt(3)]));

    grammar.put("A", () -> {
     return new ArrayList<>();
    });

    grammar.put("B", () -> {
     return new ArrayList<>();
    });

    grammar.put("chorus", () -> {
     return new ArrayList<>();
    });

    grammar.put("middle", () -> {
     return new ArrayList<>();
    });

    grammar.put("outro", () -> {
     return new ArrayList<>();
    });

    // Chords
    grammar.put("T", () -> { // Tonic
     return new ArrayList<>();
    });

    grammar.put("T+", () -> {
     return new ArrayList<>();
    });

    grammar.put("T*", () -> {
     return new ArrayList<>();
    });

    grammar.put("SD", () -> { // SubDominant
     return new ArrayList<>();
    });

    grammar.put("SD+", () -> {
     return new ArrayList<>();
    });

    grammar.put("SD*", () -> {
     return new ArrayList<>();
    });

    grammar.put("D", () -> { // Dominant
     return new ArrayList<>();
    });

    grammar.put("D+", () -> {
     return new ArrayList<>();
    });

    grammar.put("D*", () -> {
     return new ArrayList<>();
    });
   }

   public List<Chord> produce(){
   	ProductionRule production = grammar.get(initialSymbol);
   	List<Chord> result = new ArrayList<>();
   	while((production = grammar.get(production)) != null){
     for(Object p: production.expand()){
     }
    }
    return null;
   }

   public List<Chord> produce(Object symbol){
    return null;
   }

   public List<Chord> parse(List<Object> text){
   	
   	return null;
   }
}