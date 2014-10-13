package com.hzhang.bibtech.bibtex;
import java.lang.String;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.ArrayList;

import com.hzhang.bibtech.entries.*;
import com.hzhang.bibtech.exceptions.FileFormatException;

/** 
 * CSC-232 Spring
 * This will function as the main class in which entries 
 * are made from outside files, websites, and GUI manual entries.
 * @author GROUP
 *
 */
public class Importer{
    
    /**
     * Connects to a file and brings in strings and parses out Bibtex lite references.
     * @return ArrayList<Entry> an ArrayList of entry objects
     * @param String fileName
     * @throws FileFormatException 
     * @throws IOException
     */
    public ArrayList<Entry> fromFile(URI uri) throws FileFormatException{
    	
    	 ArrayList<Entry> list = new ArrayList<Entry>(0);
    	 Scanner in;
    	 try {
			in = new Scanner(new File(uri));
			
		 } catch (IOException e) {
			System.out.println("***BAD FILE ERROR***");
			return list;
		 }
    	 Entry new1= null;
         while (in.hasNextLine()){
             String line = in.nextLine(); 
             line = line.trim();
             // part 1 header lines
             if((line.isEmpty() == false)&&(line.charAt(0) == '@')){ //read entry header           	
                 //validate incoming line
                 if(validate1(line) == -1){
                 	throw new FileFormatException();
                 }
                
                 String type = makeType(line);
                 System.out.println(type);
                 new1 = setEntryType(type);
                 System.out.println(line);
            	new1.setValue("key", makeKey(line));
            	// Part 2 fields and content
             } else if(line.contains("=")){  //read in a field definition for current entry            	
             	if(validate2(line) == -1){
             		throw new FileFormatException();
             	}
                 String fieldName = getFieldName(line);
                 String fieldContents = getFieldContents(line);
                 defineFieldContents(fieldName,fieldContents, new1);
             //Part 4 is a blank line    
             } else if(line.equals("")) {             	
              	System.out.println("4: " + line);  //DEBUG -- REMOVE ME
             }
             // Part 3 end of file
             else if((line.charAt(0) == '}')){   //end of entry definition   	
             	if(validate3(line) == -1){
             		throw new FileFormatException();
             	}
             	 //System.out.println(" } found...adding an entry to the list");
             	 list.add(new1);  
             //Part 4 unexpected line error
             }
             else { 
                  throw new FileFormatException();
             }
          }
         
          if(list.size() == 0){
             System.out.println("list has not entries");
             return null;
          }
          else{
     	    return list;
          } 
     }
   
    /**
     * Connects to a URL and brings in strings and parses out Bibtex lite references.
     * @return ArrayList<Entry> an ArrayList of entry objects
     * @param String webpage
     * @throws MalformedURLException
     * @throws FileFormatException 
     */
    public ArrayList<Entry> fromWeb(String webpage) throws MalformedURLException, FileFormatException{
        URLConnection conn;
        Scanner in;
        ArrayList<Entry> list = new ArrayList<Entry>();
        
        
        URL url = new URL(webpage);
        try{
            conn = url.openConnection();
            in = new Scanner(conn.getInputStream());
            }
        catch(IOException e){
            System.out.println("Fatal error, unable to import from website.");
            throw new MalformedURLException();
        }
        Entry new1= null;
        while (in.hasNextLine()){
            String line = in.nextLine(); 
            line = line.trim();
            // part 1 header lines
            if((line.isEmpty() == false)&&(line.charAt(0) == '@')){ //read entry header           	
                //validate incoming line
                if(validate1(line) == -1){
                	throw new FileFormatException();
                }
                String type = makeType(line);
                new1 = setEntryType(type);
           		new1.setValue("key", makeKey(line));
           	// Part 2 fields and content
            } else if(line.contains("=")){  //read in a field definition for current entry            	
            	if(validate2(line) == -1){
            		throw new FileFormatException();
            	}
                String fieldName = getFieldName(line);
                String fieldContents = getFieldContents(line);
                defineFieldContents(fieldName,fieldContents, new1);
            //Part 4 is a blank line    
            } else if(line.equals("")) {  
            	
            }
            // Part 3 end of file
            else if((line.charAt(0) == '}')){   //end of entry definition   	
            	if(validate3(line) == -1){
            		throw new FileFormatException();
            	}
            	 //System.out.println(" } found...adding an entry to the list");
            	 list.add(new1);  
            //Part 4 unexpected line error
            }
            else { 
                 throw new FileFormatException();
            }
         }
        
         if(list.size() == 0){
            System.out.println("list has not entries");
            return null;
         }
         else{
    	    return list;
         } 
    }
  
   

    /**
     * this method takes a string and breaks of and returns the field name.
     * @param string line
     * @return string fieldName
     */
    public String getFieldName(String line){
    	line = line.trim();
    	String fieldContents  = line.substring(0, line.indexOf('='));
        fieldContents = fieldContents.trim();
        return fieldContents;
    }
    
    /**
     * this method takes a string and breaks of and returns the field name.
     * @param string line
     * @return string fieldContents
     */
    public String getFieldContents(String line ){
    	line = line.trim();
        String fieldName  = line.substring(line.indexOf('=')+1, line.indexOf(','));
        if((fieldName.charAt(0)=='{') && (fieldName.charAt(fieldName.length())=='}')) {
        	fieldName = fieldName.substring(1, fieldName.length());
        }
        fieldName = fieldName.trim();
        return fieldName;
    }
    
    /**
     * This method takes a field name and test it against a set of approved names
     * then take the field contents and load it into that field.
     * @return Entry
     * @param String fieldName and String FieldContents 
     */  
    public Entry defineFieldContents(String fieldName, String fieldContents, Entry e){	
    	if((e != null) && validFieldName(fieldName)){
    		e.setValue(fieldName, fieldContents);
    	}
    	else {
    		//throw new FileFormatException();    
    	}
    	return e;
   	}
    
    /** The method counts the occurrences of an certain char
     * @return int
     * @param char, String
     */    
    public int countOccurances(char x, String check){
        int a = 0;
        for(int i = 0; i < check.length(); i++){
            if(check.charAt(i) == x )
                a++;
        }
        return a;
    }
    
    /** The method sets the type of an entry
     * @param type
     * @return Entry
     */
    public Entry setEntryType(String type){
    	    Entry e1 = null;
    	if(type.equalsIgnoreCase("book")){
        	    e1 = new Book();
        }
    	else if(type.equalsIgnoreCase("Article")){
    	    e1 = new Article();
    	}
    	else if(type.equalsIgnoreCase("PhdThesis")){
    	    e1 = new Phdthesis();
    	}
    	else if(type.equalsIgnoreCase("Inproceedings")){
    	    e1 = new Inproceedings();
    	}
    	else if(type.equalsIgnoreCase("Incollection")){
    	    e1 = new Incollection();
    	}
    	else if(type.equalsIgnoreCase("Inbook")){
    	    e1 = new Inbook();
    	}
    	else if(type.equalsIgnoreCase("Misc")){
    	    e1 = new Misc();
    	}
    	else if(type.equalsIgnoreCase("Online")){
    		e1 = new Online();
    	}

		return e1;
    }
    
    /**This method is used to validate the data in the header line of a bibtex lite reference.
     * It return negative one (-1) of the line is invalid or positive one (1) if the line is valid.
     * @param String line
     * @return int -1 or 1
     */
    public int validate1(String line){
    	//System.out.println("validating 1");
    	if(countOccurances('@', line) != 1) {
            System.out.println("returning null more @");
            return -1;  //abort parsing, illegal input
        }
        else if(countOccurances('{', line) != 1) {
             System.out.println("returning null more than one {");
             return -1;  //abort parsing, illegal input
        }
        else if(countOccurances(',', line) != 1) {
             System.out.println("returning null more than one ,");
             return -1;  //abort parsing, illegal input    
        }
        else{
             return 1;
        }
       // already tested if empty and char@0=='@' 
       // other possibilities... 1. if type not valid, 2. if all not alphanumeric and or +_:-/ , 3. 

    }
   
    /**This method is used to validate the data in the middle part of the file of a bibtex lite reference.
     * It return negative one (-1) of the line is invalid or positive one (1) if the line is valid.
     * @param String line
     * @return int -1 or 1
     */
    public int validate2(String line){
    	//System.out.println("validating 2");
        if(countOccurances('=', line) != 1) {
            System.out.println(line +"returning null more =");
            return -1;  //abort parsing, illegal input
        }
        else if(countOccurances(',', line) != 1) {
             System.out.println("returning null more than one ,");
             return -1;  //abort parsing, illegal input    
        }
        else{
             return 1;
        }
    }

    /**This method is used to validate the data in the last line of a bibtex lite reference.
     * It return negative one (-1) of the line is invalid or positive one (1) if the line is valid.
     * @param String line
     * @return int -1 or 1
     */
    public int validate3(String line){

        if(countOccurances('}', line) != 1) {
            //System.out.println("returning null more @");
            return -1;  //abort parsing, illegal input
        }
        else{
             return 1;
        }
    }
    
    /** this method gets the type from a string that is validated as a bibtex header line
     * @return String type
     * @param String line
     */
    public String makeType(String line){
        String first = line;
        first = first.trim();
        String type = first.substring(line.indexOf('@')+1,line.indexOf('{'));
        type = type.trim();
        return type; 
     }
    
    /** this method gets the key from a string that is validated as a bibtex header line
     * @return String key
     * @param String line
     */
    public String makeKey(String line){
        String first = line;
        first = first.trim();
        System.out.println(first);
        String key = first.substring(line.indexOf('{')+1,line.indexOf(','));
        System.out.println("key = "+key);
        key = key.trim();
        return key; 
     }
    
    /**
     *  This method returns true if the field name is valid
     * @param fieldName
     * @return boolean
     */
    public boolean validFieldName(String fieldName){
    	if((fieldName.equalsIgnoreCase("year"))||(fieldName.equalsIgnoreCase("volumn"))||(fieldName.equalsIgnoreCase("title"))||
    			(fieldName.equalsIgnoreCase("school"))||(fieldName.equalsIgnoreCase("publisher"))||(fieldName.equalsIgnoreCase("pages"))||
    					(fieldName.equalsIgnoreCase("month"))||(fieldName.equalsIgnoreCase("journal"))||(fieldName.equalsIgnoreCase("edition"))||
    							(fieldName.equalsIgnoreCase("author"))||(fieldName.equalsIgnoreCase("chapter"))||(fieldName.equalsIgnoreCase("booktitle"))||
    							(fieldName.equalsIgnoreCase("tag"))||(fieldName.equalsIgnoreCase("notes"))||(fieldName.equalsIgnoreCase("year"))||
    							(fieldName.equalsIgnoreCase("address"))||(fieldName.equalsIgnoreCase("url"))||(fieldName.equalsIgnoreCase("protocol"))||
    							(fieldName.equalsIgnoreCase("host"))||(fieldName.equalsIgnoreCase("path"))||(fieldName.equalsIgnoreCase("day"))){
    		return true;
    	}
    	else {
    		return false;   

    	}
    }
}