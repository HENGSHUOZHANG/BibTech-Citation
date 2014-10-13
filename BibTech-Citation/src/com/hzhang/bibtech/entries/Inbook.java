package com.hzhang.bibtech.entries;

import java.io.IOException;

/**
* CSC232 - Spring 2013
* A class for producing an entry of Inbook type's basic citation information 
* into a bibtex like system
* @author GROUP
* @version HW7
*/
public class Inbook extends Entry{

	private String publisher;
	private String chapter;
	private String pages;
	private String month;
	private String editor;

	/**
	 * This a public static field holding an anonymous EntryFactory class that
	 * generates instances of that Entry class
	 */
	public static final EntryFactory FACTORY = new EntryFactory(){
		
		/**
		 * this method creates an Inbook object with specific parameters
		 * @return an Inbook entry object
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
			booktitle = null;
			school = null;
			journal = null;
			url = null;
			protocol = null;
			host = null;
			path = null;
			day = null;
			
			return new Inbook(key,  author, editor, title, year, publisher, 
						      chapter, pages, month, tag);
		}
	};
	
	/**
	 *this method returns a properly formated string 
	 *@returns a String containing formatting and information from all the fields of an entry object.
	*/	
	public String toString(){
		String out;
		out = "@" + getType() + "{" + getKey() + ",";
		
		if(getAuthor() != null){
			out = out + "\n" + "   " + "AUTHOR = "+ getAuthor() + ",";
		}
		else if(getEditor() != null){
			out = out + "\n" + "   " + "Editor = "+ getEditor() + ",";
		}
		
		out = out + "\n" + "   " + "TITLE = " + "{" + getTitle() + "}" + ",";
		
		if(getChapter() != null){
			out = out + "\n" + "   " + "Chapter = "+ getChapter() + ",";
		}
		else if(getPages() != null){
			out = out + "\n" + "   " + "Pages = "+ getPages() + ",";
		}
		
		out = out + "\n" + "   " + "Publisher = " + "{" + getPublisher() + "}" + ",";
		out = out + "\n" + "   " + "YEAR = " + "{" + getYear() + "}" + ",";
		
		if(month != null){
			out = out + "\n" + "   " + "Month = " + "{" + getMonth() + "}" + ",";
		}
		out = out + "\n" + "   " + "TAG = " + "{" + getTags() + "}" + ",";
		out = out + "\n" + "}";
		
		return out;
	}
	
	/**
	 * Inbook Constructor
	 */
	public Inbook(){
		String key = null; String author = null; String editor = null;
		String chapter = null; String title = null; String year = null; 
		String publisher = null; String edition = null;
		String pages = null; String month = null; String tag = null;
	}
	
	/**
	 * Inbook Constructor with parameters
	 * @param key
	 * @param author
	 * @param editor
	 * @param title
	 * @param year
	 * @param publisher
	 * @param chapter
	 * @param pages
	 * @param month
	 */
	public Inbook(String key, String author, String editor, String title,
			String year, String publisher, String chapter, String pages,
			String month, String tag) {
		super(key, author, title, year, tag);
		this.editor = editor;
		this.chapter = chapter;
		this.pages = pages;
		this.month = month;
	}

	/**
	 * get editor method
	 * @return String editor
	 */
	public String getEditor(){
		return editor;
	}
	
	/**
	 * get chapter method
	 * @return String chapter
	 */
	public String getChapter(){
		return chapter;
	}
	
	/**
	 * get pagas method
	 * @return String pages
	 */
	public String getPages(){
		return pages;
	}
	
	/**
	 * get publisher method
	 * @return String publisher
	 */
	public String getPublisher(){
		return publisher;
	}
	
	/**
	 * get month method
	 * @return String month
	 */
	public String getMonth(){
		return month;
	}
	
	/**
	 * this method sets value for fieldName and fieldContents
	 */
	public void setValue(String fieldName, String fieldContents){
    	if(fieldName.equalsIgnoreCase("month")){
    		this.month = fieldContents;
    	}
    	else if(fieldName.equalsIgnoreCase("editor")){
    		this.editor = fieldContents;
    	}
    	else if(fieldName.equalsIgnoreCase("pages")){
    		this.pages = fieldContents;
    	}
    	else if(fieldName.equalsIgnoreCase("publisher")){
    		this.publisher = fieldContents;
    	}

    	else if(fieldName.equalsIgnoreCase("chapter")){
    		this.chapter = fieldContents;
    	} 
    	
        else if(fieldName.equalsIgnoreCase("pages")){
        	this.pages = fieldContents;
        }
    	else {
    		super.setValue(fieldName, fieldContents);    
    	}
	}
	
	/**
	 * get type method
	 * @return "Inbook"
	 */
	@Override
	public String getType() {
		return "Inbook";
	}
}
