package com.allfre2.musicruler.songs;

import java.net.URL;
import java.net.MalformedURLException;
import java.io.Reader;
import java.io.StringReader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

//
import javax.swing.text.AttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.Parser;
import javax.swing.text.html.parser.ParserDelegator;
//

/**
 * A DataSource implementation that fetches the text form a URL.
 * @see DataSource
 */
public class URLDataSource extends DataSource{

   /**
    * Class Constructor.
    * Initializes the tokens field by fetching the data from the URL
    * and passing it to the {@link URLDataSource#getData} method.
    * @param url The String of the url to be downloaded.
    */
    public URLDataSource (String url){
     tokenize(getData(url));
    }

    // TODO parse HTML, Json and others
    // For now just plain text
   /**
    * Downloads the url by passing it to the URL classes constructor
    * and calling the openStream method.
    * @param url The String of the url to be downloaded.
    * @return The String containing all the data downloaded from the url.
    */
    protected String getData(String url){

     String data = "";
     try{

       data = readLines(
         new BufferedReader(
         new InputStreamReader(
         new URL(url).openStream())));

     }catch(MalformedURLException e){
        e.printStackTrace();
     }catch(IOException e){
        e.printStackTrace();
     }

     //Check to se if it is html
     // if it is get the p tags
     return data;
    }

  // Pending
  private String getPtags(String data) throws IOException{
    Reader reader = new StringReader(data);
    HTMLEditorKit htmlKit = new HTMLEditorKit();
    HTMLDocument htmlDoc = (HTMLDocument) htmlKit.createDefaultDocument();
    HTMLEditorKit.Parser parser = new ParserDelegator();
    parser.parse(reader, htmlDoc.getReader(0), true);

    HTMLDocument.Iterator itr = htmlDoc.getIterator(HTML.Tag.P);
      if(itr == null){
        System.out.println("P tags iterator is null!!");
        return data;
      }

    for(; itr.isValid(); itr.next())
    {
     AttributeSet attrSet = itr.getAttributes();
     System.out.println("attrSet: " + attrSet);
    }
    return null;
  }
}
