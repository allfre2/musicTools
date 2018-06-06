package com.allfre2.musicruler.tools;

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