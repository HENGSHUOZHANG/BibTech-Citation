package com.hzhang.bibtech.testing;
import java.net.MalformedURLException;

import static org.junit.Assert.*;

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
public class ImporterTest {
	
	/**
	 * @throws FileFormatException 
	 * 
	 */
	@Test
	public void test() throws FileFormatException{
		ArrayList<Entry> from = new ArrayList<Entry>();
		ArrayList<Entry> badFrom = new ArrayList<Entry>();
		
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
		TestLibControl.addTestEntry("book","weber1993");
		
		//debugger code
		//TestLibControl.printAll();
		Importer test1 = new Importer();
		try {		  
			from = test1.fromWeb("http://www.csc.depauw.edu/~mwebe15/csc232/bibtextTest/Bibtex.tex");
			badFrom = test1.fromWeb("http://www.csc.depauw.edu/~mwebe15/csc232/bibtextTest/BadBibtex.tex");
		} 
		catch (MalformedURLException e) {
			System.out.println("The test URL is not working");
		} catch (FileFormatException e) {
			System.out.println("file format exception");
			e.printStackTrace();
		}
		for(int i = 0; i < from.size(); i++){
			TestLibInput.addTestEntry((from.get(i).getType()),(from.get(i).getKey()));
			
		}
		
		System.out.println("from size = "+from.size());
		System.out.println("from 0 type ="+ from.get(0).getType());
		System.out.println("from 0 key ="+ from.get(0).getKey());
		System.out.println("from 1 type ="+ from.get(1).getType());
		System.out.println("from 1 key ="+ from.get(1).getKey());
		
		/**
		 * check if the first type loaded from the web is "book".
		 */
		
		assertEquals( "Book", from.get(0).getType());
		
		/**
		 * check if the first key loaded from the web is "weber1993".
		 */
		assertEquals(from.get(0).getKey(), "weber1993");
		
		/**
		 * check if the second type loaded from the web is "article". 
		 */
		assertEquals(from.get(1).getType(), "Article");
		
		/**
		 * check if the second key loaded from the web is "Zhang1993".
		 */
		assertEquals(from.get(1).getKey(), "Zhang1993");
		
		/**
		 * check if the first type loaded from the web is "book".
		 */
		//assertNotSame( "book", badFrom.get(0).getType());
		
		/**
		 * check if the first key loaded from the web is "weber1993".
		 */
		//assertNotSame(badFrom.get(0).getKey(), "weber1993");
		
		/**
		 * check if the second type loaded from the web is "article". 
		 */
		//assertNotSame(badFrom.get(1).getType(), "article");
		
		/**
		 * check if the second key loaded from the web is "Zhang1993".
		 */
		//assertNotSame(badFrom.get(1).getKey(), "Zhang1993");
		}
		//debugger code
		//System.out.println("The Control Library contains:\n");
		//TestLibControl.printAll();
		//System.out.println("The Input Library contains:\n");
		//TestLibInput.printAll();
	}	 	