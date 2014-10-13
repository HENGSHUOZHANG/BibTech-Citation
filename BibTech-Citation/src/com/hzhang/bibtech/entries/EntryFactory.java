package com.hzhang.bibtech.entries;

/**
 * This interface includes a factory method for generating an Entry 
 * @author GROUP
 */
public interface EntryFactory {
	/** will create a new item 
	 * from the EntryHolder item it is given
	 */
	public Entry generateEntry(String key, String author, 
			                   String title, String year, String month,
			                   String editor, String publisher, String edition,
			                   String chapter, String pages, String booktitle,
			                   String school, String journal, String tag, String url,
			                   String protocol, String host, String path, String day);
}
