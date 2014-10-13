package com.hzhang.bibtech.testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.event.ListDataListener;

import org.junit.Test;

import com.hzhang.bibtech.bibtex.Library;
import com.hzhang.bibtech.comparators.KeyComparator;
import com.hzhang.bibtech.entries.Book;
import com.hzhang.bibtech.entries.Entry;

/**
* CSC232 - Spring 2013
* A class for testing the Library class.
* @author GROUP
* @version HW4
*/
public class LibraryTest {

	@Test
	public void test() {
		
		Library testLib = new Library();
		testLib.addTestEntry("book1", "key1");
		testLib.addTestEntry("book2", "key2");
		
		Entry e1 = new Book();
		e1.setValue("book", "book1");
		e1.setValue("key", "key1");
		Entry e2 = new Book();
		e1.setValue("book", "book2");
		e2.setValue("key", "key2");
		testLib.addEntry(e1);
		testLib.addEntry(e2);
		
		assertEquals(e1, testLib.getElementAt(0));
		assertEquals(e2, testLib.getElementAt(1));
		
		testLib.deleteEntry("key1"); 
		assertEquals(e2, testLib.getElementAt(0)); //test delete function
		assertEquals(1, testLib.getSize()); //test the getSize method and check the size after deleting
		
		testLib.addTestEntry("book1", "key1");
		
		assertTrue(testLib.contains("key1")); //test method contains
		
		assertEquals(e2, testLib.get("key2")); 
		
		testLib.findType("book");
		HashSet<Entry> found = new HashSet<Entry>();
		found.add(e1);
		found.add(e2);
		assertEquals(found, new HashSet<Entry>(testLib.findType("book")));
		
	}

}
