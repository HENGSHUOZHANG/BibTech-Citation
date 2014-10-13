package com.hzhang.bibtech.entries;

import java.util.HashMap;

/**
* CSC232 - Spring 2013
* This class creates a hashmap for each type 
* by calling corresponding factory method
* @author GROUP
* @version HW7
*/
public class HashEnt {
	
	public static HashMap<String, EntryFactory>
   
	generateFactories() {
	HashMap<String, EntryFactory> factories = 
		new HashMap<String, EntryFactory>();
	factories.put("book", Book.FACTORY);
	factories.put("phdthesis", Phdthesis.FACTORY);
	factories.put("article", Article.FACTORY);
	factories.put("misc", Misc.FACTORY);
	factories.put("inbook", Inbook.FACTORY);
	factories.put("incollection", Incollection.FACTORY);
	factories.put("online", Online.FACTORY);
	return factories;
	}
}
