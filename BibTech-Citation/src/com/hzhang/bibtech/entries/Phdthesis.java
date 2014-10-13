package com.hzhang.bibtech.entries;

import java.io.IOException;

/**
* CSC232 - Spring 2013
* A class for producing an entry of Phdthesis type's basic citation information 
* into a bibtex like system
* @author GROUP
* @version HW7
*/
public class Phdthesis extends Entry {

	private String school;
	private String month;
	
	/**
	 * This a public static field holding an anonymous EntryFactory class that
	 * generates instances of that Entry class
	 */
	public static final EntryFactory FACTORY = new EntryFactory(){
		
		/**
		 * this method creates a Phdthesis object with specific parameters
		 * @return a Phdthesis entry object
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
			journal = null;
			url = null;
			protocol = null;
			host = null;
			path = null;
			day = null;
			
			return new Phdthesis(key, author, title, year,
								 school, month, tag);
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
		out = out + "\n" + "   " + "SCHOOL = " + "{" + getSchool() + "}" + ",";
		out = out + "\n" + "   " + "YEAR = " + "{" + getYear() + "}" + ",";
		
		if(month != null){
			out = out + "\n" + "   " + "Month = " + "{" + getMonth() + "}" + ",";
		}
		
		out = out + "\n" + "   " + "TAG = " + "{" + getTags() + "}" + ",";
		out = out + "\n" + "}";
		
		return out;
	}
	
	/**
	 * Phdthesis constructor
	 */
	public Phdthesis(){
		String key = null; String author = null; String title = null;
		String year = null; String school = null; String month = null;
		String tag = null;
	}
	
	/**
	 * Phdthesis constructor with parameters
	 * @param key
	 * @param author
	 * @param title
	 * @param year
	 * @param school
	 * @param month
	 */
	public Phdthesis(String key, String author, String title,
			String year, String school, String month, String tag) {
		super(key, author, title, year, tag);
		this.school = school;
		this.month = month;
	}
	
	/**
	 * this method sets the value for fieldName and fieldContents
	 */
	@Override
	public void setValue(String fieldName, String fieldContents){
    	if(fieldName.equalsIgnoreCase("month")){
    		this.month = fieldContents;
    	}
    	else if(fieldName.equalsIgnoreCase("school")){
    		this.school = fieldContents;
    	}  
    	else {
    		super.setValue(fieldName, fieldContents);    
    	}
	}
	
	/**
	 * get school method
	 * @return String school
	 */
	public String getSchool(){
		return school;
	}
	
	/**
	 * get month method
	 * @return String month
	 */
	public String getMonth(){
		return month;
	}
	
	/**
	 * get type method
	 * @return "Phdthesis"
	 */
	@Override
	public String getType() {
		return "Phdthesis";
	}
}
