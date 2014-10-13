package com.hzhang.bibtech.entries;

import java.io.IOException;

/**
* CSC232 - Spring 2013
* A class for producing an entry of Inproceedings type's basic citation information 
* into a bibtex like system
* @author GROUP
* @version HW7
*/
public class Inproceedings extends Entry {

	private String booktitle;
	private String publisher;
	private String month;
	private String year;
	private String pages;
	
	/**
	 * This a public static field holding an anonymous EntryFactory class that
	 * generates instances of that Entry class
	 */
	public static final EntryFactory FACTORY = new EntryFactory(){

		/**
		 * this method creates an Inproceedings object with specific parameters
		 * @return an Inproceedings entry object
		 */
		@Override
		public Entry generateEntry(String key, String author, 
                String title, String year, String month,
                String editor, String publisher, String edition,
                String chapter, String pages, String booktitle,
                String school, String journal, String tag, String url,
                String protocol, String host, String path, String day){
			
			editor = null;
			edition = null;
			chapter = null;
			school = null;
			journal = null;
			url = null;
			protocol = null;
			host = null;
			path = null;
			day = null;
			
			return new Inproceedings(key, author,title, year, publisher, pages,
									month, booktitle, tag);
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
		out = out + "\n" + "   " + "BOOKTITLE = " + "{" + getBookTitle() + "}" + ",";
		out = out + "\n" + "   " + "PUBLISHER = " + "{" + getPublisher() + "}" + ",";
		out = out + "\n" + "   " + "YEAR = " + "{" + getYear() + "}" + ",";
		
		if(month != null){
			out = out + "\n" + "   " + "Month = " + "{" + getMonth() + "}" + ",";
		}
		
		if(pages != null){
			out = out + "\n" + "   " + "PAGES = " + "{" + getPages() + "}" + ",";
		}
		out = out + "\n" + "   " + "TAG = " + "{" + getTags() + "}" + ",";
		out = out + "\n" + "}";
		
		return out;
	}
	
	/**
	 * Inproceedings constructor
	 */
	public Inproceedings(){
		String key = null; String author = null; String title = null;
		String year = null; String publisher = null; String pages = null;
		String month = null; String booktitle = null; String tag = null;
	}
	
	/**
	 * Inproceedings constructor with parameters
	 * @param key
	 * @param author
	 * @param title
	 * @param year
	 * @param publisher
	 * @param pages
	 * @param month
	 * @param booktitle
	 */
	public Inproceedings(String key, String author, String title,
			String year, String publisher, String pages,
			String month, String booktitle, String tag) {
		super(key, author, title, year, tag);
		
		this.publisher = publisher;
		this.booktitle = booktitle;
		this.month = month;
		this.pages = pages;
	}
	
	/**
	 * this method sets value for fieldName and fieldContents
	 */
	public void setValue(String fieldName, String fieldContents){
    	if(fieldName.equalsIgnoreCase("month")){
    		this.month = fieldContents;
    	}
    	else if(fieldName.equalsIgnoreCase("booktitle")){
    		this.booktitle = fieldContents;
    	}
    	else if(fieldName.equalsIgnoreCase("pages")){
    		this.pages = fieldContents;
    	}
    	else if(fieldName.equalsIgnoreCase("publisher")){
    		this.publisher = fieldContents;
    	}

    	else if(fieldName.equalsIgnoreCase("year")){
    		this.year = fieldContents;
    	}  
    	else {
    		super.setValue(fieldName, fieldContents);    
    	}
	}
	
	/**
	 * get publisher method
	 * @return String publisher
	 */
	public String getPublisher(){
		return publisher;
	}
	
	/**
	 * get bookTitle method
	 * @return String booktitle
	 */
	public String getBookTitle(){
		return booktitle;
	}
	
	/**
	 * get month method
	 * @return String month
	 */
	public String getMonth(){
		return month;
	}
	
	/**
	 * get pages method
	 * @return String pages
	 */
	public String getPages(){
		return pages;
	}
	
	/**
	 * get type method
	 * @return "Inproceedings"
	 */
	@Override
	public String getType() {
		return "Inproceedings";
	}
}
