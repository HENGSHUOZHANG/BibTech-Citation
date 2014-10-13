package com.hzhang.bibtech.comparators;

import java.io.IOException;
import java.util.Comparator;

import com.hzhang.bibtech.entries.Entry;


/**
 * This class compares two entries by their year
 * 
 * @author GROUP
 */
public class YearComparator implements Comparator<Entry> {

	@Override
	public int compare(Entry e1, Entry e2){
		return e1.getYear().compareTo(e2.getYear());
	}
}
