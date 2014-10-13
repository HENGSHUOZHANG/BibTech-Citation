package com.hzhang.bibtech.entries;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Online extends Entry{
	
	private String url;
	private String protocol;
	private String host;
	private String path;
	private String day;
	private String month;
	private String year;
	
	/**
	 * This a public static field holding an anonymous EntryFactory class that
	 * generates instances of that Entry class
	 */
	public static final EntryFactory FACTORY = new EntryFactory(){
		
		/**
		 * this method creates a Book object with specific parameters
		 * @return a Online entry object
		 */
		
		@Override
		public Entry generateEntry(String key, String author, String title,
				String year, String month, String editor, String publisher,
				String edition, String chapter, String pages, String booktitle,
				String school, String journal, String tag, String url,
				String protocol, String host, String path, String day) {
			
			editor = null;
			publisher = null;
			edition = null;
			chapter = null;
			pages = null;
			booktitle = null;
			school = null;
			journal = null;
			tag = null;
			
			return new Online(key, author, title, url, protocol, host, 
					          path, day, month, year, tag);
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
		if(url != null && (protocol == null && host == null && path == null)){
			out = out + "\n" + "   " + "URL = " + "{" + getUrl() + "}" + ",";
		}
		if(url == null && (protocol != null && host != null && path != null)){
				out = out + "\n" + "   " + "URL = " + "{" + getProtocol() +
						"/" + getHost() +  "/" + getPath() + "}" + ",";
		}
		if(day != null){
			out = out + "\n" + "   " + "DAY = " + "{" + getDay() + "}" + ",";
		}
		if(month != null){
			out = out + "\n" + "   " + "MONTH = " + "{" + getMonth() + "}" + ",";
		}
		if(year != null){
			out = out + "\n" + "   " + "YEAR = " + "{" + getYear() + "}" + ",";
		}
		
		return out;
	}
	/**
	 * Online constructor
	 */
	public Online(){
		String key = null; String author = null; String title = null; 
		String url = null; String protocol = null; String host = null;
		String path = null; String day = null; String month = null; 
		String year = null; String tag = null;
	}
	/**
	 * Book constructor with parameters
	 * @param key
	 * @param title
	 * @param author
	 * @param url
	 * @param protocol
	 * @param host
	 * @param path
	 * @param day
	 * @param month
	 * @param year
	 * @param tag
	 */
	public Online(String key, String title, String author, String url, String protocol, 
			String host, String path, String day, String month, String year, String tag){
		
		super(key, author, title, year, tag);
		
		this.url = url;
		
		if(url == null){
			this.protocol = protocol;
			this.host = host;
			this.path = path;
		}
		
		this.day = day;
		this.month = month;
	}
	/**
	 * get url method
	 * @return String url
	 */	
	public String getUrl(){
			return url;
	}
	
	/**
	 * get protocol method
	 * @return String protocol
	 */
	public String getProtocol(){
		return protocol;
	}
	/**
	 * get host method
	 * @return String host
	 */
	public String getHost(){
		return host;
	}
	/**
	 * get path method
	 * @return String path
	 */
	public String getPath(){
		return path;
	}
	
	/**
	 * get day method
	 * @return String day
	 */
	public String getDay(){
		return day;
	}
	/**
	 * get month method
	 * @return String month
	 */
	public String getMonth(){
		return month;
	}
	/**
	 * get year method
	 * @return String year
	 */
	public String getYear(){
		return year;
	}
	/**
	 * this method sets value for fieldName and fieldContents
	 */
	public void setValue(String fieldName, String fieldContents){
    	
		if(fieldName.equalsIgnoreCase("url")){
			if(protocol != null || host != null || path != null){
				return; //if we have all other three fields, return
			}
			else {
				if(fieldContents.charAt(0)== '"' ||fieldContents.charAt(0)== '{'){ //check if the first char in the url is the quotation make
						fieldContents = fieldContents.substring(1,fieldContents.length()-1);
					}
				 if(fieldContents.charAt(fieldContents.length()-1)== '"'){
					 fieldContents = fieldContents.substring(0,fieldContents.length()-1);
				 }
				 if(fieldContents.length() < 10){//check url's length
					 System.out.println("URL too short");
					 return;
				 }
				 else if(fieldContents.substring(0, 7).equals("http://")){
					this.url = fieldContents;
				 }
				 else{
					return;
				 }
			}
    	}
		else if(fieldName.equalsIgnoreCase("protocol")){ //put value if url == null;
			if(url != null){
				return;
			}
			else
				this.protocol = fieldContents;
		}
		else if(fieldName.equalsIgnoreCase("host")){//put value if url == null;
			if(url != null){
				return;
			}
			else
				this.host = fieldContents;
		}
	    else if(fieldName.equalsIgnoreCase("path")){//put value if url == null;
	    	if(url != null){
				return;
			}
			else
				this.path = fieldContents;
	    }
		
    	else if(fieldName.equalsIgnoreCase("day")){
    		this.day = fieldContents;
    	}
    	else if(fieldName.equalsIgnoreCase("month")){
    		this.month = fieldContents;
    	}
    	else if(fieldName.equalsIgnoreCase("year")){
    		this.year = fieldContents;
    	}
    	else {
    		super.setValue(fieldName, fieldContents);    
    	}
	}
	/**
	 * get type method
	 * @return "Online"
	 */
	@Override
	public String getType() {
		return "Online";
	}
	
}
