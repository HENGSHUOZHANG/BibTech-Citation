package com.hzhang.bibtech.entries;
import java.io.IOException;

/**
* CSC232 - Spring 2013
* A class for producing an entry of Article type's basic citation information 
* into a bibtex like system
* @author GROUP
* @version HW7
*/
public class Article extends Entry {

	private String month = null;
	private String journal = null;
	
	/**
	 * This a public static field holding an anonymous EntryFactory class that
	 * generates instances of that Entry class
	 */
	public static final EntryFactory FACTORY = new EntryFactory(){

		/**
		 * this method creates a Article object with specific parameters
		 * @return a Article entry object
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
			
			
			return new Article(key, author, title, year, journal, month, tag);
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
		out = out + "\n" + "   " + "Journal = " + "{" + getJournal() + "}" + ",";
		out = out + "\n" + "   " + "YEAR = " + "{" + getYear() + "}" + ",";
		
		if(month != null){
			out = out + "\n" + "   " + "Month = " + "{" + getMonth() + "}" + ",";
		}
		
		out = out + "\n" + "   " + "TAG = " + "{" + getTags() + "}" + ",";
		out = out + "\n" + "}";
		
		return out;
	}
		
	/**
	 * Article constructor
	 */
	public Article(){
		String key = null; String author = null; String tag = null;
		String title = null; String year = null; String journal = null; String month = null;
	}
		
	/**
	* Article constructor with parameters
	* @param key
	* @param author
	* @param title
	* @param year
	* @param journal
	* @param month
	*/
	public Article(String key, String author,
			String title, String year, String journal, String month, String tag){
		super(key, author, title, year, tag);
		this.journal = journal;
		this.month = month;
	}
		
	/**
	 * get journal method
	 * @return String journal
	 */
	public String getJournal(){
		return journal;
	}
		
	/**
	 * get month method
	 * @return String method
	 */
	public String getMonth(){
		return month;
	}
		
	/**
	 * This method sets value for fieldName and fieldContents
	 */
	public void setValue(String fieldName, String fieldContents){
		if(fieldName.equalsIgnoreCase("month")){
		    this.month = fieldContents;
		}
		else if(fieldName.equalsIgnoreCase("journal")){
		    this.journal  = fieldContents;
		}
		else {
		    super.setValue(fieldName, fieldContents);    
		}
	}
		
	/**
	 * get type method
	 * @return "Article"
	 */
	@Override
	public String getType() {
		return "Article";
	}
}
