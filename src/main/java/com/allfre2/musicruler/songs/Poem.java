package com.allfre2.musicruler.songs;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.Random;

//
import com.allfre2.musicruler.tools.io;
//

public abstract class Poem implements Lyrics{

    public void init(){
     try{
        poem = new CFG(cfg);
     }catch(InvalidCFGException e){
        e.printStackTrace();
     }
     generator = new StringMarkov(data);
     generator.countTokens();
     buildRhymeTable();
     verses = new HashMap<>();
    }

    protected static final Random rnd = new Random();

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

    public void setCfgStart(String start){
        this.start = start;
    }

    protected int lineWidth;

    public int getLineWidth(){
        return lineWidth;
    }
    
    public void setLineWidth(int lineWidth){
        this.lineWidth = lineWidth;
    }
    
	protected DataSource data;

	public void setDataSource(DataSource dataSource){
		data = dataSource;
	}

	public DataSource getDataSource(){
		return data;
	}

    protected int maxRhymeDistance;

    public int getMaxRhymeDistance(){
    	return maxRhymeDistance;
    }
 
    public void setMaxRhymeDistance(int distance){
    	this.maxRhymeDistance = distance;
    }

    protected HashMap< Token<String>, List<Token<String>> > rhymeTable;
 
	public void buildRhymeTable(){

List<Token<String>> tokens = generator.tokens();
// System.out.println("building rhyme table");
// System.out.println(tokens);
// System.out.println("\n\n " + tokens.size()*tokens.size() + " Comparisons need to be performed.");
// io.input();

	 rhymeTable = new HashMap<>();

     for(Token<String> token: tokens){

      if(!rhymeTable.containsKey(token))
        rhymeTable.put(new Token<String>(token), new ArrayList<Token<String>>());

      for(Token<String> adjToken: tokens){

      	if(Soundex.distance(token.get(), adjToken.get()) < maxRhymeDistance){
// System.out.println("Comparing with soundex " + token.get() + " and " + adjToken.get());
// System.out.println("result = " + Soundex.distance(token.get(), adjToken.get()));
// io.input();
          List<Token<String>> rhymes = rhymeTable.get(token);
          rhymes.add(new Token<String>(adjToken));

      	}
      }
     }

// System.out.println("Final Rhyme Table ...\n\n");
//      for(Token<String> t: rhymeTable.keySet()){
//         System.out.println("key: " + t + " " + rhymeTable.get(t) );
//         io.input();
//      }
	}

    public String generate(){

     String poemStr = "";

     for(String verseStr: poem.genRandom(start, 1).split(" ")){
        // System.out.println("Generating: verStr = " + verseStr);
        // io.input();
        if(verseStr.equals(newLine))
            poemStr += "\n";
        else
            poemStr += genVerse(verseStr);
        // System.out.println("poemStr = " + poemStr);
     }

     return poemStr;
    }

    protected StringMarkov generator;

    HashMap<Integer, List<Token<String>>> verses;

    public String genVerse(String verseStr){

// System.out.println(" = " + verseStr);
     if(!verseStr.matches(verse+separator
                        +number+separator
                        +number+separator
                        +number+separator))
        // System.out.println("Error: verse is not in the correct format");
        ;

     List<String> fields = Arrays.asList(verseStr.split(separator));
     // System.out.println("fields: " + fields);
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

     // TODO fields.get(2) make sense with ...

     int rhymeIndex = Integer.parseInt(fields.get(3));

     List<Token<String>> rhymeTokens = verses.get(rhymeIndex);

     List<Token<String>> newVerse =
      generator.generateEndingWith(rhymeTokens,
                         null, // When fields.get(2) is sorted out here will be some related words
                         rnd.nextInt(lineWidth/2)+(lineWidth/2));

     int key = Integer.parseInt(fields.get(1));

     if(!verses.containsKey(key))
        verses.put(key, rhymeTokens);

     return newVerse.stream()
                    .map(token -> token.get())
                    .reduce("", (s1, s2) -> s1 + " " + s2);
    }
}