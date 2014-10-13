package com.hzhang.bibtech.entries;

import java.io.IOException;
//import java.util.HashMap;

/**
* CSC232 - Spring 2013
* A class for producing an entry of basic citation information into a bibtex
* like system
* We choose these fields because we thought that our four defined fields title, 
* author, year, and type provided a good base for entries.
* @author GROUP
* @version HW4
*/
public class Misc extends Entry{
	
	private String month;

   /** The main method which makes an new instance of the 
    *  already filled Entry constructor and then calls toString()	
    *  method on this object.
    *
    *  Constructs an Entry object that holds information on an article. 
    */
	public static final EntryFactory FACTORY = new EntryFactory(){
		
		/**
		 * this method creates a Misc object with specific parameters
		 * @return a Misc entry object
		 */
		@Override
		public Entry generateEntry(String key, String author, 
                String title, String year, String month,
                String editor, String publisher, String edition,
                String chapter, String pages, String booktitle,
                String school, String journal, String tag, String url,
                String protocol, String host, String path, String day){
			
			editor = null;
			publisher = null;
			edition = null;
			chapter = null;
			pages = null;
			booktitle = null;
			school = null;
			journal = null;
			url = null;
			protocol = null;
			host = null;
			path = null;
			day = null;
			
			return new Misc(key, author, title, year, month, tag);
		}
	};
	
	    /**
	     *this method returns a properly formated string 
	     *@returns a String containing formatting and information from all the fields of an entry object.
	     */
		public String toString(){
			String out;
			out = "@" + getType() + "{" + getKey() + ",";
			out = out + "\n" + "   " + "AUTHOR = "+ getAuthor() + ",";
			out = out + "\n" + "   " + "TITLE = " + "{" + getTitle() + "}" + ",";
			out = out + "\n" + "   " + "YEAR = " + "{" + getYear() + "}" + ",";
			out = out + "\n" + "   " + "Month = " + "{" + getMonth() + "}" + ",";
			out = out + "\n" + "   " + "TAG = " + "{" + getTags() + "}" + ",";
			out = out + "\n" + "}";
			
			return out;
		}
		
	/**
	 * Misc constructor
	 */
	public Misc(){
		String key = null; String author = null; String tag = null;
		String title = null; String year = null; String month = null;
	}
	
	/**
	 * Misc constructor with parameters
	 * @param key
	 * @param author
	 * @param title
	 * @param year
	 * @param month
	 */
	public Misc(String key, String author, String title, 
			String year, String month, String tag) {
		
		super(key, author, title, year, tag);
		this.month = month;
	}
	
	/**
	 * get type method
	 * @return "Misc"
	 */
	@Override
	public String getType() {
		return "Misc";
	}
	
	/**
	 * get month method
	 * @return String month
	 */
	public String getMonth(){
			return month;
	}
	
	/**
	 * this method sets the value for fieldName and fieldContents
	 */
	@Override
	public void setValue(String fieldName, String fieldContents){
		if(fieldName.equalsIgnoreCase("month")){
			this.month = fieldContents;
		}
		else{
			super.setValue(fieldName, fieldContents);
		}
	}
}



