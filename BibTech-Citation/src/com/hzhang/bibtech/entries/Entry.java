package com.hzhang.bibtech.entries;

import java.io.IOException;
//import java.util.HashMap;


import com.hzhang.bibtech.exceptions.FileFormatException;

/**
* CSC232 - Spring 2013
* A class for producing an entry of basic citation information into a bibtex
* like system
* We choose these fields because we thought that our four defined fields title, 
* author, year, and type provided a good base for entries.
* @author GROUP
* @version HW4
*/
public abstract class Entry implements Comparable<Entry>{
	private String key = null;
	private String author = null;
	private String title = null;
	private String year = null;
	private String notes = null;
	private String tag = null;
	

	
	/**
	 * Constructs an Entry object that holds information on an article. 
	 */
    public Entry(String key, String author,
		String title, String year, String tag) {
		this.key = key;
		this.author = author;
		this.title = title;
		this.year = year;
		this.tag = tag;
	}
   
	/**
	 * default constructor for Entry
	 */
	public Entry() {
		key = "Key";
		author = "Author";
		title = "Title";
		year = "Year";
		tag = "";
	}
	
	/**
	 * 	the following are all setters and getters for the variables
	 * @return Strings
	 * @throws IOException 
	 */
	public abstract String getType();
	
	/**
	 * This method gets the key
	 * @return String key
	 * @throws IllegalStateException
	 */
	public String getKey() throws IllegalStateException{
		if(key == null){
			throw new IllegalStateException("key not set");
		}
		else{
			return key;
		}
	}
	
	/**
	 * get author method
	 * @return String author
	 */
	public String getAuthor(){
		return author;
	}
	
	/**
	 * get title method
	 * @return String title
	 */
	public String getTitle(){
		return title;
	}
	
	/**
	 * get year method
	 * @return String year
	 */
	public String getYear(){
		return year;
	}
	/**
	 * get tags method
	 * @return String year
	 */
	public String getTags(){
		return tag;
	}
	
	/**
	 * get notes method
	 * @return String notes
	 */
	public String getNotes(){
		return notes;
	}
	
	/**
	 * This method compares two entries by their key
	 * @param entry object ent
	 * @return -1, 0 or 1
	 */
	@Override
	public int compareTo(Entry ent) {
		return key.compareTo(ent.key);
	}
	
	/**
	 * This method set values for key, author, title, year and notes
	 * @param fieldName
	 * @param fieldContents
	 */
	public void setValue(String fieldName, String fieldContents) {
		if(fieldName.equalsIgnoreCase("key")){
			this.key = fieldContents;
		}
		else if(fieldName.equalsIgnoreCase("author")){
	    	this.author = fieldContents;
	    }
	    else if(fieldName.equalsIgnoreCase("title")){
	    	this.title = fieldContents;
	    }
	    else if(fieldName.equalsIgnoreCase("year")){
	    	this.year = fieldContents;
	    }  
	    else if(fieldName.equalsIgnoreCase("note")){
	    	this.notes = fieldContents;
	    }
	    else if(fieldName.equalsIgnoreCase("tag")){
	    	this.tag = fieldContents;
	    }
	}

	@Override
	public boolean equals(Object ent){
		if(ent instanceof Entry){
			return ((Entry) ent).getKey().equals(key);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return key.hashCode();
	}
}



