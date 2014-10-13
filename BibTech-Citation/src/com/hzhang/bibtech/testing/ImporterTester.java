package com.hzhang.bibtech.testing;
import java.net.MalformedURLException;
import java.util.ArrayList;

import com.hzhang.bibtech.bibtex.Importer;
import com.hzhang.bibtech.bibtex.Library;
import com.hzhang.bibtech.entries.Entry;
import com.hzhang.bibtech.exceptions.FileFormatException;

/** 
 * CSC-232 Spring
 * This will be our tester for the importer class for HW4
 * the below code is meant to provide test for the importer
 * and offers confirmation of completion at various times in the code.
 * @author GROUP
 *
 */
public class ImporterTester {
	
	/**
	 * a method for debugging
	 * @param args
	 */
	public static void main(String[] args){
		ArrayList<Entry> test1 = new ArrayList<Entry>();
		ArrayList<Entry> test2 = new ArrayList<Entry>();
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
		//debugger code
		//TestLibControl.printAll();
		Importer T1 = new Importer();
		try {	
			System.out.println("size is of test1 is " + (test1.size()));
			test1 = T1.fromWeb("file:///home/jmorwick/public_html/csc232/ai.bib");
//			test1 = T1.fromWeb("http://www.csc.depauw.edu/~mwebe15/csc232/bibtextTest/Bibtex.tex");
			System.out.println("test one has been initiated");
			//System.out.println("size is of test1 is " + (test1.size()));
			//test2 = T1.fromWeb("http://www.csc.depauw.edu/~mwebe15/csc232/bibtextTest/BadBibtex.tex");
		} 
		catch (MalformedURLException e) {
			System.out.println("The test URL is not working");
			e.printStackTrace();
		} catch (FileFormatException e) {
			System.out.println("FileFormatException thrown");
			e.printStackTrace();
		}
		System.out.println("into the for loop");
		if (test1.isEmpty()== true){
			System.out.println("test1 is empty");
		}
		System.out.println("size is of test1 is " + (test1));
		for(int i = 0; i < test1.size(); i++){
			System.out.println("for loop running"+ i);
			System.out.println(test1.get(i));
			TestLibInput.addTestEntry((test1.get(i).getType()),(test1.get(i).getKey()));
		}
		//debugger code
		System.out.println("The Control Library contains:\n"); 
		TestLibControl.printAll();
		System.out.println("The Input Library contains:\n");
		TestLibInput.printAll();
	}	 	 
}