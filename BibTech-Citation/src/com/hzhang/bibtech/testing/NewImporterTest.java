package com.hzhang.bibtech.testing;
import java.net.MalformedURLException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import com.hzhang.bibtech.bibtex.Importer;
import com.hzhang.bibtech.bibtex.Library;
import com.hzhang.bibtech.entries.Entry;
import com.hzhang.bibtech.exceptions.FileFormatException;

/** 
 *  CSC-232 Spring
 *  
 * This will be our tester for the importer class.
 * @author GROUP
 *
 */
public class NewImporterTest {
	
	/**
	 * @throws FileFormatException 
	 */
	@Test
	public void test() throws FileFormatException{
		ArrayList<Entry> from = new ArrayList<Entry>();
		//below code is for view during coding/testing purposes
		/**String testerBibtex1 =
			"@book{weber1993,\n" +
			"author = Michael Weber,\n"+
			"title = {Forrest Gump},\n" +
			"address = {Effingham, IL},\n"+
			"year = 2013,\n" +
			"url = www.google.com,\n" +
			"publisher = DePauw,\n" +
			"}\n" ;
		
		String testerBibtext2 =
			"@article{Zhang1993," +
			"journal = {The best one ever},\n" +
			"author = Hengshuo Zhang,\n" +
			"title = {The Story of My Life},\n" +
			"address = {New York NY USA},\n" +
			"pages = {1-100},\n" +
			"volume = 12,\n" +
			"number = 3,\n" +
			"year = {2015},\n" +
			"url = {http://www.depauw.edu},\n" +
			"publisher = DePauw\n" +
			"}\n" ;
			*/
		Library TestLibControl = new Library();
		Library TestLibInput = new Library();
		TestLibControl.addTestEntry("article", "Zhang1993");
		TestLibControl.addTestEntry("book", "weber1993");
		//TestLibInput.addEntry((from.get(i).getType()),(from.get(i).getKey()));  
		
		//debugger code
		//TestLibControl.printAll();
		Importer newTest = new Importer();
		try {		  
			from = newTest.fromWeb("http://www.csc.depauw.edu/~mwebe15/csc232/bibtextTest/BadBibtex.tex");
		} 
		catch (MalformedURLException e) {
			System.out.println("The test URL is not working");
		}
		for(int i = 0; i < from.size(); i++){ 
		}
		/**
		 * check if the first type loaded from the web is "book".
		 */
		assertEquals(from.get(0).getType(), "book");
		
		/**
		 * check if the first key loaded from the web is "weber1993".
		 */
		assertFalse(from.get(0).getKey().equalsIgnoreCase("weber1993"));
		
		/**
		 * check if the second type loaded from the web is "article". 
		 */
		assertEquals(from.get(1).getType(), "article");
		
		/**
		 * check if the second key loaded from the web is "Zhang1993".
		 */
		assertFalse(from.get(0).getKey().equalsIgnoreCase("Zhang1993"));
		
		}
		//debugger code
		//System.out.println("The Control Library contains:\n");
		//TestLibControl.printAll();
		//System.out.println("The Input Library contains:\n");
		//TestLibInput.printAll();
	}	 	