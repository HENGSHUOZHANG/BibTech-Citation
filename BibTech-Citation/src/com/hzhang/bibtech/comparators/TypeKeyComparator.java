package com.hzhang.bibtech.comparators;

import java.util.Comparator;

import com.hzhang.bibtech.entries.Entry;

/**
 * This class compares two entries by their type 
 * and if types are the same, it will compare their key
 * 
 * @author GROUP
 */
public class TypeKeyComparator implements Comparator<Entry> {

	@Override
	public int compare(Entry e1, Entry e2) {
		if(e1.getType().equals(e2.getType()))
			return e1.getKey().compareTo(e2.getKey());
		else
			return e1.getType().compareTo(e2.getType());
	}
}
