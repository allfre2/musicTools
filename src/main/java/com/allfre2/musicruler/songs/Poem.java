package com.allfre2.musicruler.songs;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;

public abstract class Poem implements Lyrics{

    public void init(){
     try{
        poem = new CFG(cfg);
     }catch(InvalidCFGException e){
        e.printStackTrace();
     }
     generator = new StringMarkov(data);
    }

    protected static final String verse = "verse";
    protected static final String separator = "-";
    protected static final String newLine = "nl";
    protected static final String number = "\\d+";

    protected String cfg;
    protected String start;
    protected CFG poem;

    public String getCfg(){
        return cfg;
    }
    
    public void setCfg(String cfg){
        this.cfg = cfg;
    }
 
	protected DataSource data;

	public void setDataSource(DataSource dataSource){
		data = dataSource;
	}

	public DataSource getDataSource(){
		return data;
	}

    protected int maxRhymeDistance = 4;

    public int getMaxRhymeDistance(){
    	return maxRhymeDistance;
    }
 
    public void setMaxRhymeDistance(int distance){
    	this.maxRhymeDistance = distance;
    }

    protected HashMap< Token<String>, List<Token<String>> > rhymeTable;
 
	public void buildRhymeTable(){

	 rhymeTable = new HashMap<>();

     for(Token<String> token: data.getTokens()){

      if(!rhymeTable.containsKey(token))
        rhymeTable.put(new Token<String>(token), new ArrayList<Token<String>>());

      for(Token<String> adjToken: data.getTokens()){

      	if(Soundex.distance(token.get(), adjToken.get()) < maxRhymeDistance){

          List<Token<String>> rhymes = rhymeTable.get(token);
          rhymes.add(new Token<String>(adjToken));

      	}
      }
     }
	}

    public String generate(){

     String poemStr = "";

     for(String verseStr: poem.genRandom(start, 1).split(" "))
        if(verseStr.equals(newLine))
            poemStr += "\n";
        else
            poemStr += genVerse(verseStr);

     return poemStr;
    }

    protected StringMarkov generator;

    public String genVerse(String verseStr){

     if(!verseStr.matches(verse+separator
                        +number+separator
                        +number+separator
                        +number+separator))
        System.out.println("Error: verse is in incorrect format");

     List<String> fields = Arrays.asList(verseStr.split(separator));
     // Store the numbers and genrated string/sentence/verse
     // in some sort of data structure.

     // Retrieve the integer of the verse
     // Use first to index the verse in the data structure
     // Use second to see with which is has to be related (have common words make sense)
     //  use ... something to pass a list of keyWords to generator's generate method
     // Use third to see with which other verse it has to rhyme (0 means with none)
     // Then ... call generator's generate method with keyWords related to corresponding
     //   verse and generateEndingWith with words that rhyme with the corresponding verse
     // Use the computed rhymeTable to finds these words.

     return null;
    }
}