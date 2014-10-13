package com.hzhang.bibtech.comparators;

import java.io.IOException;
import java.util.Comparator;

import com.hzhang.bibtech.entries.Entry;

/**
 * This class compares two entries by their key
 * 
 * @author GROUP
 *
 */
public class TitleComparator implements Comparator<Entry> {

	@Override
	public int compare(Entry e1, Entry e2){
		return e1.getTitle().compareTo(e2.getTitle());
	}
}
