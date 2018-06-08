package com.allfre2.musicruler.songs;

public interface Lyrics{

 public void setDataSource(DataSource dataSource);
 public DataSource getDataSource();

 public String generate();
}