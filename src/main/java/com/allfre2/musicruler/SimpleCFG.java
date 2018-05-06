package com.allfre2.musicruler;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class SimpleCFG implements CFG<Chord>{

   protected Scale key;
   protected HashMap<Object, ProductionRule> grammar;
   protected String root = "Bridge"; // Song Structure

   public SimpleCFG(Scale scale){

    key = scale;
    grammar = new HashMap<>();

    grammar.put("Brigde", () -> Arrays.asList(new String[]{"intro",""}));
   }

   public List<Chord> produce(){
   	ProductionRule production = grammar.get(root);
   	while((production = grammar.get(production)) != null){
     for(Object p: production.expand()){

     }
    }
    return null;
   }

   public List<Chord> parse(List<Object> text){
   	
   	return null;
   }
}