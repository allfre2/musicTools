package com.allfre2.musicruler.songs;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileDataSource extends DataSource{
    
	public FileDataSource (String path){
    tokenize(readFile(new File(path)));
	}

	protected String readFile(File file){
     String lines = "";
     try{

      lines = readLines(
       new BufferedReader(
       new FileReader(file)));

     }catch(IOException e){
     	e.printStackTrace();
     }
     return lines;
	}
}