package com.allfre2.musicruler.songs;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Implementation of {@link DataSource} that extracts the tokens
 * from a File object.
 * @see DataSource
 */
public class FileDataSource extends DataSource{

 /**
  * Class Constructor.
  * @param path The path tothe file to be read.
  */
    public FileDataSource (String path){
    tokenize(readFile(new File(path)));
    }

 /**
  * Reads all lines from a file into a single string.
  * @param file File object.
  * @return All the lines in the file as a single String.
  */
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
