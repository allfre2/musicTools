package com.allfre2.musicruler.songs;

/**
 * Interface to be implemented by any class that uses
 * a Markov object to generate a specific kind of text/lyrics.
 */
public interface Lyrics{

 public void setDataSource(DataSource dataSource);
 public DataSource getDataSource();

 public String generate();
}