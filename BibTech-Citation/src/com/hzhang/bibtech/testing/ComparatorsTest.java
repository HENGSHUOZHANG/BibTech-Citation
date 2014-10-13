package com.hzhang.bibtech.testing;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

import com.hzhang.bibtech.comparators.AuthorComparator;
import com.hzhang.bibtech.comparators.KeyComparator;
import com.hzhang.bibtech.comparators.TitleComparator;
import com.hzhang.bibtech.comparators.TypeComparator;
import com.hzhang.bibtech.comparators.TypeKeyComparator;
import com.hzhang.bibtech.comparators.YearComparator;
import com.hzhang.bibtech.entries.Article;
import com.hzhang.bibtech.entries.Book;
import com.hzhang.bibtech.entries.Entry;

import static org.junit.Assert.assertEquals;

/** 
 * CSC-232 Spring
 * This will be our tester for the Comparators class for HW6
 * It will test the three sorting methods
 * @author GROUP
 */

public class ComparatorsTest {
	
	private Collections Comparator;
	
	/**
	 * This is testing method for all Comparators
	 */
	@Test
	public void test(){
		
		ArrayList<Entry> ls = new ArrayList<Entry>();
		
		/**
		 * Declare three entries for testing
		 */
		Entry testEntry1 = new Book("Weber1993", "Michael Weber", "Forrest Gump", "DePauw", "2013", "August", "2nd", "13", "favorite");
		Entry testEntry2 = new Article("Zhang1993", "Hengshuo Zhang", "The Story of My Life", "2015", "Great Journal","May", "Super Awesome");
		Entry testEntry3 = new Article("Li1992", "Tianqi LI", "Computers?", "2020", "MIT Journal","September", "Trash Bin");
		
		/**
		 * add the the three new entries to an arrayList ls
		 */
		ls.add(testEntry1);
		ls.add(testEntry2);
		ls.add(testEntry3);
		
		/**
		 * declare a new arrayList testKeyEntry with the entries 
		   in the correct order that we expect
		 */
		ArrayList<Entry> testKeyEntry = new ArrayList<Entry>();
		testKeyEntry.add(testEntry1);
		testKeyEntry.add(testEntry2);
		testKeyEntry.add(testEntry3);
		
		/**
		 * Sort the arrayList ls by using TypeComparator()
		 */
		Collections.sort(ls, new TypeComparator());
		testKeyEntry.set(0, testEntry2);
		testKeyEntry.set(1, testEntry3);
		testKeyEntry.set(2, testEntry1);
		assertEquals(testKeyEntry, ls);
		
		/**
		 * Sort the arrayList ls by using KeyComparator()
		 */
		Collections.sort(ls, new KeyComparator());
		testKeyEntry.set(0, testEntry3);
		testKeyEntry.set(1, testEntry1);
		testKeyEntry.set(2, testEntry2);
		assertEquals(testKeyEntry, ls);
		
		/**
		 * Sort the arrayList ls by using TypeKeyComparator()
		 */
		Collections.sort(ls, new TypeKeyComparator());
		testKeyEntry.set(0, testEntry3);
		testKeyEntry.set(1, testEntry2);
		testKeyEntry.set(2, testEntry1);
		assertEquals(testKeyEntry, ls);
		
		/**
		 * Sort the arrayList ls by using AuthorComparator()
		 */
		Collections.sort(ls, new AuthorComparator());
		testKeyEntry.set(0, testEntry2);
		testKeyEntry.set(1, testEntry1);
		testKeyEntry.set(2, testEntry3);
		assertEquals(testKeyEntry, ls);
		
		/**
		 * Sort the arrayList ls by using TitleComparator()
		 */
		Collections.sort(ls, new TitleComparator());
		testKeyEntry.set(0, testEntry3);
		testKeyEntry.set(1, testEntry1);
		testKeyEntry.set(2, testEntry2);
		assertEquals(testKeyEntry, ls);
		
		/**
		 * Sort the arrayList ls by using YearComparator()
		 */
		Collections.sort(ls, new YearComparator());
		testKeyEntry.set(0, testEntry1);
		testKeyEntry.set(1, testEntry2);
		testKeyEntry.set(2, testEntry3);
		assertEquals(testKeyEntry, ls);
	}
	
}
