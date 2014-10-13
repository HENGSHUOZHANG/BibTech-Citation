package com.hzhang.bibtech.testing;

import static org.junit.Assert.*;

import org.junit.Test;

import com.hzhang.bibtech.entries.Entry;
import com.hzhang.bibtech.entries.Misc;

/**
* CSC232 - Spring 2013
* A class for testing the Entry class.
* @author GROUP
* @version HW4
*/
public class EntryTest {

	@Test
	public void test() {
		
		Entry testEntry = new Misc();
		
		testEntry.setValue("Key", "HZ:GP");
		testEntry.setValue("Author", "Hengshuo Zhang");
		testEntry.setValue("Title", "Good Paper");
		testEntry.setValue("Year", "2013");
		testEntry.setValue("Month", "May");
		testEntry.setValue("Tag", "favorite");
		
		Entry rightEntry = new Misc("HZ:GP","Hengshuo Zhang",
				"Good Paper", "2013", "May", "favorite"); 
		/**
		 * check if rightEntry has the same content with testEntry
		 */
		assertEquals(rightEntry.toString(), testEntry.toString());
		assertEquals(rightEntry.getType(), "Misc");
		assertEquals(rightEntry.getKey(), "HZ:GP");
		assertEquals(rightEntry.getAuthor(), "Hengshuo Zhang");
		assertEquals(rightEntry.getYear(), "2013");
		assertEquals(((Misc) rightEntry).getMonth(), "May");
		assertEquals(rightEntry.getTags(), "favorite");
		
		rightEntry.setValue("author", "Michael Weber");
		
		assertEquals("Michael Weber", rightEntry.getAuthor());
		
		rightEntry.setValue("month", "April");
		
		assertEquals("April", ((Misc) rightEntry).getMonth());
	}
}
