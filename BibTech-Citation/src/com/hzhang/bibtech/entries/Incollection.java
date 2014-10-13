package com.hzhang.bibtech.entries;

import java.io.IOException;

/**
* CSC232 - Spring 2013
* A class for producing an entry of Incollection type's basic citation information 
* into a bibtex like system
* @author GROUP
* @version HW7
*/
public class Incollection extends Entry{

	private String booktitle;
	private String chapter;
	private String month;
	
	/**
	 * This a public static field holding an anonymous EntryFactory class that
	 * generates instances of that Entry class
	 */
	public static final EntryFactory FACTORY = new EntryFactory(){
		
		/**
		 * this method creates an Incollection object with specific parameters
		 * @return an Incollection entry object
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
			school = null;
			journal = null;
			booktitle = null;
			url = null;
			protocol = null;
			host = null;
			path = null;
			day = null;
			
			return new Incollection(key, author, title, booktitle, year, 
					publisher, chapter, pages, month, tag);
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
				out = out + "\n" + "   " + "YEAR = " + "{" + getYear() + "}" + ",";
				
				if(month != null){
					out = out + "\n" + "   " + "MONTH = " + "{" + getMonth() + "}" + ",";
				}
				
				if(chapter != null){
					out = out + "\n" + "   " + "CHAPTER = " + "{" + getChapter() + "}" + ",";
				}
				out = out + "\n" + "   " + "TAG = " + "{" + getTags() + "}" + ",";
				out = out + "\n" + "}";
				
				return out;
			}
		
		/**
		 * Incollection constructor 
		 */
		public Incollection(){
			String key = null; String author = null; String chapter = null;
			String title = null; String booktitle = null; String year = null; String publisher = null;
			String pages = null; String month = null; String tag = null;
		}
		
		/**
		 * Incollection constructor with parameters
		 * @param key
		 * @param author
		 * @param title
		 * @param booktitle
		 * @param year
		 * @param publisher
		 * @param chapter
		 * @param pages
		 * @param month
		 */
		public Incollection(String key, String author, String title,
				String booktitle, String year, String publisher, String chapter,
				String pages, String month, String tag) {
			super(key, author, title, year, tag);
			this.booktitle = booktitle;
			this.month = month;
			this.chapter = chapter;
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
	    	else if(fieldName.equalsIgnoreCase("chapter")){
	    		this.chapter = fieldContents;
	    	}

	    	else {
	    		super.setValue(fieldName, fieldContents);    
	    	}
		}
		
		/**
		 * get bookTitle method
		 * @return String bookTitle
		 */
		public String getBookTitle(){
			return booktitle;
		}
		
		/**
		 * set bookTitle method
		 * @param newBookTitle
		 */
		public void setBookTitle(String newBookTitle){
			booktitle = newBookTitle;
		}
		
		/**
		 * get month method
		 * @return String month
		 */
		public String getMonth(){
			return month;
		}
		
		/**
		 * set month method
		 * @param newMonth
		 */
		public void setMonth(String newMonth){
			month = newMonth;
		}
		
		/**
		 * get chapter method
		 * @return String chapter
		 */
		public String getChapter(){
			return chapter;
		}
		
		/**
		 * set chapter method
		 * @param newChapter
		 */
		public void setChapter(String newChapter){
			chapter = newChapter;
		}
		
		/**
		 * get type method
		 * @return "Article"
		 */
		@Override
		public String getType() {
			return "Incollection";
		}
	
}

