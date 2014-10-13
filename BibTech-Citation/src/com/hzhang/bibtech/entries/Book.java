package com.hzhang.bibtech.entries;

import java.io.IOException;

/**
* CSC232 - Spring 2013
* A class for producing an entry of Book type's basic citation information 
* into a bibtex like system
* @author GROUP
* @version HW4
*/
public class Book extends Entry {
	
	private String publisher;
	private String editor;
	private String edition;
	private String month;
	
	/**
	 * This a public static field holding an anonymous EntryFactory class that
	 * generates instances of that Entry class
	 */
	public static final EntryFactory FACTORY = new EntryFactory(){
		
		/**
		 * this method creates a Book object with specific parameters
		 * @return a Book entry object
		 */
		@Override
		public Entry generateEntry(String key, String author, 
                String title, String year, String month,
                String editor, String publisher, String edition,
                String chapter, String pages, String booktitle,
                String school, String journal, String tag, String url,
                String protocol, String host, String path, String day){
			
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
			
			/**
			 * call the Book constructor
			 */
			return new Book(key, author,editor, title, year, 
					        publisher, edition, month, tag);
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
		out = out + "\n" + "   " + "PUBLISHER = " + "{" + getPublisher() + "}" + ",";
		out = out + "\n" + "   " + "YEAR = " + "{" + getYear() + "}" + ",";
		
		if(month != null){
			out = out + "\n" + "   " + "MONTH = " + "{" + getMonth() + "}" + ",";
		}
		
		if(edition != null){
			out = out + "\n" + "   " + "Edition = " + "{" + getEdition() + "}" + ",";
		}
		out = out + "\n" + "   " + "TAG = " + "{" + getTags() + "}" + ",";
		out = out + "\n" + "}";
		
		return out;
	}
	
	/**
	 * Book constructor
	 */
	public Book(){
		String key = null; String author = null; String editor = null;
		String title = null; String year = null; String publisher = null; String edition = null;
		String month = null; String tag = null; 
	}
	
	/**
	 * Book constructor with parameters
	 * @param key
	 * @param author
	 * @param editor
	 * @param title
	 * @param year
	 * @param publisher
	 * @param edition
	 * @param month
	 */
	public Book(String key, String author,
			String editor, String title, String year, 
			String publisher, String edition, String month, String tag){
		super(key, author, title, year, tag);
		this.publisher = publisher;
		this.editor = editor;
		this.edition = edition;
		this.month = month;

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
	 * get edition method
	 * @return String edition
	 */
	public String getEdition(){
			return edition;
	}
	
	/**
	 * get editor method
	 * @return String editor
	 */
	public String getEditor(){
		if(editor != null)
			return editor;
		else 
			return null;
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

    	else if(fieldName.equalsIgnoreCase("edition")){
    		this.edition = fieldContents;
    	}
    	
    	else if(fieldName.equalsIgnoreCase("publisher")){
    		this.publisher = fieldContents;
    	}
  
    	else {
    		super.setValue(fieldName, fieldContents);    
    	}
	}
	
	/**
	 * get type method
	 * @return "Book"
	 */
	@Override
	public String getType() {
		return "Book";
	}
}
