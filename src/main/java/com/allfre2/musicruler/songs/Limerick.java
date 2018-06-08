package com.allfre2.musicruler.songs;

public class Limerick extends Poem{
    
    protected static final String limerickCFG =
      "limerick = " +
      " verse-1-0-0 nl"+
      " verse-2-1-1 nl"+
      " verse-3-0-0 nl"+
      " verse-4-3-3 nl"+
      " verse-5-4-1;"; 

	public Limerick (DataSource data){
	 setDataSource(data);
	 setCfg(limerickCFG);
	 setCfgStart("limerick");
	 setLineWidth(8);
	 setMaxRhymeDistance(4);
	 init();
	}
}