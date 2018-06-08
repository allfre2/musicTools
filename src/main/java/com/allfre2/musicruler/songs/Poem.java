package com.allfre2.musicruler.songs;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Poem implements Lyrics{

	protected DataSource data;
	protected int maxRhymeDistance = 4;
	protected StringMarkov generator;
	protected HashMap< Token<String>, List<Token<String>> > rhymeTable;

	public void setDataSource(DataSource dataSource){
		data = dataSource;
	}

	public DataSource getDataSource(){
		return data;
	}

    public int getMaxRhymeDistance(){
    	return maxRhymeDistance;
    }
    
    public void setMaxRhymeDistance(int distance){
    	this.maxRhymeDistance = distance;
    }
    
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
}