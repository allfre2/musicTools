package com.allfre2.musicruler.songs;

/**
 * A class to use the {@link Markov} class with the
 * generic T parameter as a String specifically.
 * @see Markov
 */
public class StringMarkov extends Markov<String>{
    
    protected int minOrder = 5;

	public StringMarkov (DataSource data){
	 order = minOrder;
	 buildTable(data.getTokens());
	}

	public void add(DataSource data){
     buildTable(data.getTokens());
	}
}