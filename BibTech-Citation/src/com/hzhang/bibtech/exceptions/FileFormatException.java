package com.hzhang.bibtech.exceptions;

/**
 * This class throws runtime exception
 * if the file is not formatted properly
 * @author GROUP
 *
 */
public class FileFormatException extends Exception{
	
	/**
	 * constructor for FileFormatException
	 */
	 public FileFormatException(){
		 super();
	 }
	   	
	 /**
	  * contructor for FileFormatEception with parameter
	  * @param str
	  */
	  public FileFormatException(String str){
	   	 super(str);
	  }
	   	
	  /**
	   * this method returns a message to tell the user 
	   * the file is not formatted properly
	   */
	  public String toString() {
		  return "Runtime Exception: The file is not formatted properly";
	  }
}
