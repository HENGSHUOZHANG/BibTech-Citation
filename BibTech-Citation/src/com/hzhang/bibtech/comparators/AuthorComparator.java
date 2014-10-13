package com.hzhang.bibtech.comparators;

import java.io.IOException;
import java.util.Comparator;

import com.hzhang.bibtech.entries.Entry;

/**
 * This class compares two entries by their author
 * 
 * @author GROUP
 */
public class AuthorComparator implements Comparator<Entry> {
    
	@Override
	public int compare(Entry e1, Entry e2){
		return e1.getAuthor().compareTo(e2.getAuthor());
	}
}