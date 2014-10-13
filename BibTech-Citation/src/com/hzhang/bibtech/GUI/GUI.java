
package com.hzhang.bibtech.GUI;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.hzhang.bibtech.bibtex.Importer;
import com.hzhang.bibtech.bibtex.Library;
import com.hzhang.bibtech.comparators.AuthorComparator;
import com.hzhang.bibtech.comparators.KeyComparator;
import com.hzhang.bibtech.comparators.TitleComparator;
import com.hzhang.bibtech.comparators.TypeKeyComparator;
import com.hzhang.bibtech.comparators.YearComparator;
import com.hzhang.bibtech.entries.*;
import com.hzhang.bibtech.exceptions.FileFormatException;

/**
 * 
 * @author GROUP
 * HW 8 GUI
 */

/**
 * The GUI class
 */
	public class GUI extends JPanel{
		
		public static void main(String[] args) {
			final JFrame frame = new JFrame("BibTech");
			
			final Library lib = new Library();
			final Library searchLib = new Library();
			
			final ArrayList<Color> colors = new ArrayList<Color>(1);
			Color colorBlack = Color.black;
			colors.add(0, colorBlack);
			
			final JList entryList = new JList(lib);
			final JList searchList = new JList(searchLib);
				
			//sets up the main panel
			final JPanel panel = new JPanel();
			panel.setLayout(new BorderLayout());
			panel.setBackground(colors.get(0));
			
			final JFileChooser fileC = new JFileChooser();
			final JScrollPane scroll = new JScrollPane(entryList);
			final JScrollPane sScroll = new JScrollPane(searchList);

			frame.setSize(1000, 750);
			
			ImageIcon addIcon = null;
			ImageIcon removeIcon = null;
			ImageIcon searchIcon = null;
			ImageIcon sortIcon = null;
			ImageIcon saveIcon = null;
			ImageIcon prefIcon = null;
			ImageIcon helpIcon = null;
			ImageIcon editIcon = null;
			
			 addIcon = new ImageIcon(GUI.class.getResource("/Icons/Add.png"));
			 removeIcon = new ImageIcon(GUI.class.getResource("/Icons/Del.png"));
			 searchIcon = new ImageIcon(GUI.class.getResource("/Icons/Search.png"));
			 sortIcon = new ImageIcon(GUI.class.getResource("/Icons/Sort.png"));
			 saveIcon = new ImageIcon(GUI.class.getResource("/Icons/Save.png"));
			 prefIcon = new ImageIcon(GUI.class.getResource("/Icons/Prefs.png"));
			 helpIcon = new ImageIcon(GUI.class.getResource("/Icons/Help.png"));
			 editIcon = new ImageIcon(GUI.class.getResource("/Icons/edit.png"));
				
			// adds the  "add" button
			JButton addE = new JButton("Add a Citation",addIcon); 
			addE.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Object choice = null;
						Object[] possibilities = {"Add From an URL", "Add From a File", "add some", "Manually Input a Citation"};
						// has the user select what to sort by form a list of possibilities
						choice = JOptionPane.showInputDialog(
						                    frame,
						                    "How Would you Like to Add Entries",
						                    "Add to Library",
						                    JOptionPane.PLAIN_MESSAGE,
						                    null,
						                    possibilities,
						                    "Add From a File");
						if(choice != null){
							if(choice.equals("Add From an URL")){
								String URLname = null;
								Importer imprt = new Importer();
								while(URLname == null) {
									URLname = JOptionPane.showInputDialog("What is the URL?");
									if(JOptionPane.showConfirmDialog(null, "is\""+URLname+"\"correct?")
											== JOptionPane.NO_OPTION)
										URLname = null;
									else
										try {
											lib.addEntries(imprt.fromWeb(URLname));
										} catch (MalformedURLException e) {
											JOptionPane.showMessageDialog(frame,
													"Error, this is not a valid URL\n",
												    "Bad URL",
												    JOptionPane.WARNING_MESSAGE);
										} catch (FileFormatException e) {
											JOptionPane.showMessageDialog(frame,
													"Error, this file is not formated properly\n",
												    "File Format Bad",
												    JOptionPane.WARNING_MESSAGE);
										}
								}
							}
							if(choice.equals("Add From a File")){
								File file = null;
								Importer imprt = new Importer();
								int returnVal = fileC.showOpenDialog(null);
	
						        if (returnVal == JFileChooser.APPROVE_OPTION) {
						             file = fileC.getSelectedFile();
						             try {
						            	 lib.addEntries(imprt.fromFile(file.toURI()));
									} catch (FileFormatException e) {
										JOptionPane.showMessageDialog(frame,
												"Error, this file is not formated properly\n",
											    "File Format Bad",
											    JOptionPane.WARNING_MESSAGE);
										
									}
						        } 
							}
						    if(choice.equals("Manually Input a Citation")){
								
								Object manType = null;
								Object[] types = {"Article", "Book", "Inbook", "Incollection", "Inproceedings", "Phdthesis", "Misc", "Online"};
								
								// has the user select what to sort by form a list of possibilities
								 manType = JOptionPane.showInputDialog(
								                    frame,
								                    "Publication type?",
								                    "Manual Input",
								                    JOptionPane.PLAIN_MESSAGE,
								                    null,
								                    types,
								                    "Book");
								 
								if(manType.equals("Article")){
									   JTextField key = new JTextField(15);
								       JTextField author = new JTextField(15);
									   JTextField title = new JTextField(15);
									   JTextField journal = new JTextField(15);
									   JTextField year = new JTextField(15);
									   JTextField month = new JTextField(15);
	
								       JPanel myPanel = new JPanel();
								       myPanel.setLayout(new GridLayout(12,1));
								       myPanel.add(new JLabel("Key:"));
								       myPanel.add(key);
								       myPanel.add(new JLabel("Author:"));
								       myPanel.add(author);
								       myPanel.add(new JLabel("Title:"));
								       myPanel.add(title);
								       myPanel.add(new JLabel("Journal:"));
								       myPanel.add(journal);
								       myPanel.add(new JLabel("Year:"));
								       myPanel.add(year);
								       myPanel.add(new JLabel("OPTIONAL:Month:"));
								       myPanel.add(month);
								      
								      int x = JOptionPane.showConfirmDialog(null, myPanel, 
								               "Please enter the data below", JOptionPane.OK_CANCEL_OPTION);
								      
								      if (x == JOptionPane.OK_OPTION) {
								    	  Article new1 = new Article();
									    	 int invalid = 0;
									    	 if(key.getText().trim().equals(""))
													invalid++;
									    	 else
										    	new1.setValue("key", key.getText());
									    	 if(author.getText().trim().equals(""))
													invalid++;
									    	 else
										    	new1.setValue("author", author.getText());
									    	 if(title.getText().trim().equals(""))	 
										    	 invalid++;
										    else
										    	new1.setValue("title", title.getText());
									    	 if(journal.getText().trim().equals(""))	 
										    	 invalid++;
										    else
										    	new1.setValue("journal", journal.getText());
										    if(year.getText().trim().equals(""))	 
									    		 invalid++;
										    else
										    	new1.setValue("year", year.getText());
										    if(invalid!=0)
										    		 try {
															throw new FileFormatException();
														} catch (FileFormatException e) {
															JOptionPane.showMessageDialog(frame,
																	"Error, not all the required fields were included.\n",
																    "File Format Bad",
																    JOptionPane.WARNING_MESSAGE);
															return;
														}
										    if(month.getText().trim().equals(""))	 
									    		 new1.setValue("month", null);
									    	 else
									    		 new1.setValue("month", month.getText());
										    	 lib.addEntry(new1);
								      }
								}
								
								if(manType.equals("Book")){
									   JTextField key = new JTextField(15);
									   JTextField author = new JTextField(15);
									   JTextField editor = new JTextField(15);
									   JTextField title = new JTextField(15);
									   JTextField publisher = new JTextField(15);
									   JTextField year = new JTextField(15);
									   JTextField month = new JTextField(15);
									   JTextField edition = new JTextField(15);
									   
								      JPanel myPanel = new JPanel();
								      myPanel.setLayout(new GridLayout(17,1));
								      myPanel.add(new JLabel("Key:"));
								      myPanel.add(key);
								      myPanel.add(new JLabel("Must have Author or Editor"));
								      myPanel.add(new JLabel("Author:"));
								      myPanel.add(author);
								      myPanel.add(new JLabel("Editor:"));
								      myPanel.add(editor);
								      myPanel.add(new JLabel("Title:"));
								      myPanel.add(title);
								      myPanel.add(new JLabel("Publisher:"));
								      myPanel.add(publisher);
								      myPanel.add(new JLabel("Year:"));
								      myPanel.add(year);
								      myPanel.add(new JLabel("OPTIONAL: Month:"));
								      myPanel.add(month);
								      myPanel.add(new JLabel("OPTIONAL: Edition:"));
								      myPanel.add(edition);
								      
								      int x = JOptionPane.showConfirmDialog(null, myPanel, 
								               "Please enter the data below", JOptionPane.OK_CANCEL_OPTION);
								      
								      if (x == JOptionPane.OK_OPTION) {
								    	  Book new1 = new Book();
									    	 int invalid = 0;
									    	 if(key.getText().trim().equals(""))
													invalid++;
									    	 else
										    	new1.setValue("key", key.getText());
									    	 if(((author.getText().trim().equals("")) && (editor.getText().trim().equals(""))) ||
									    			 (author.getText().length()>1) && (editor.getText().length()>1)){ 
										    		 invalid++;
									    	 }
									    	 if(author.getText().trim().equals("")){
									    		 new1.setValue("author", null);
										    	 new1.setValue("editor", editor.getText());
									    	 }
										    else
										    	new1.setValue("editor", null);
										    	new1.setValue("author", author.getText());
										    if(title.getText().trim().equals(""))	 
										    	 invalid++;
										    else
										    	new1.setValue("title", title.getText());	 
										    if(publisher.getText().trim().equals(""))	 
									    		 invalid++;
										    else
										    	new1.setValue("publisher", publisher.getText());
										    if(year.getText().trim().equals(""))	 
									    		 invalid++;
										    else
										    	new1.setValue("year", year.getText());
										    if(invalid!=0)
										    		 try {
															throw new FileFormatException();
														} catch (FileFormatException e) {
															JOptionPane.showMessageDialog(frame,
																	"Error, not all the required fields were included.\n",
																    "File Format Bad",
																    JOptionPane.WARNING_MESSAGE);
															return;
														}
										    if(month.getText().trim().equals(""))	 
									    		 new1.setValue("month", null);
									    	 else
									    		 new1.setValue("month", month.getText());
										    if(edition.getText().trim().equals(""))	 
									    		 new1.setValue("edition", null);
									    	 else
									    		 new1.setValue("edition", edition.getText());
										    	 lib.addEntry(new1);
								      }
								}
								if(manType.equals("Inbook")){
									   JTextField key = new JTextField(15);
									   JTextField author = new JTextField(15);
									   JTextField editor = new JTextField(15);
									   JTextField title = new JTextField(15);
									   JTextField chapter = new JTextField(15);
									   JTextField pages = new JTextField(15);
									   JTextField publisher = new JTextField(15);
									   JTextField year = new JTextField(15);
									   JTextField month = new JTextField(15);
									   
								      JPanel myPanel = new JPanel();
								      myPanel.setLayout(new GridLayout(20,1));
								      myPanel.add(new JLabel("Key:"));
								      myPanel.add(key);
								      myPanel.add(new JLabel("Must have Author or Editor"));
								      myPanel.add(new JLabel("Author:"));
								      myPanel.add(author);
								      myPanel.add(new JLabel("Editor:"));
								      myPanel.add(editor);
								      myPanel.add(new JLabel("Title:"));
								      myPanel.add(title);
								      myPanel.add(new JLabel("Must have Chapter and or Pages"));
								      myPanel.add(new JLabel("Chapter:"));
								      myPanel.add(chapter);
								      myPanel.add(new JLabel("Pages:"));
								      myPanel.add(pages);
								      myPanel.add(new JLabel("Publisher:"));
								      myPanel.add(publisher);
								      myPanel.add(new JLabel("Year:"));
								      myPanel.add(year);
								      myPanel.add(new JLabel("OPTIONAL: Month:"));
								      myPanel.add(month);
								      
								      int x = JOptionPane.showConfirmDialog(null, myPanel, 
								               "Please enter the data below", JOptionPane.OK_CANCEL_OPTION);
								      
								      if (x == JOptionPane.OK_OPTION) {
								    	  Inbook new1 = new Inbook();
									    	 int invalid = 0;
									    	 if(key.getText().trim().equals(""))
													invalid++;
									    	 else
										    	new1.setValue("key", key.getText());
									    	 if(((author.getText().trim().equals("")) && (editor.getText().trim().equals(""))) ||
									    			 (author.getText().length()>1) && (editor.getText().length()>1)){ 
										    		 invalid++;
									    	 }
									    	 if(author.getText().trim().equals("")){
									    		 new1.setValue("author", null);
										    	 new1.setValue("editor", editor.getText());
									    	 }
										    else
										    	new1.setValue("editor", null);
										    	new1.setValue("author", author.getText());
										    if(title.getText().trim().equals(""))	 
										    	 invalid++;
										    else
										    	new1.setValue("title", title.getText());	 
										    if((chapter.getText().trim().equals("")) && (pages.getText().trim().equals(""))) 
									    		 invalid++;
									    	if((chapter.getText().length()>1) && (pages.getText().length()>1)){ 
									    		new1.setValue("chapter", chapter.getText());
									    		new1.setValue("pages", pages.getText());
									    	}
									    	else if((chapter.getText().length()>1) && (pages.getText().trim().equals(""))){
									    		new1.setValue("chapter", chapter.getText());
									    	}
									    	else
									    		new1.setValue("pages", pages.getText());
										    if(publisher.getText().trim().equals(""))	 
									    		 invalid++;
										    else
										    	new1.setValue("publisher", publisher.getText());
										    if(year.getText().trim().equals(""))	 
									    		 invalid++;
										    else
										    	new1.setValue("year", year.getText());
										    if(invalid!=0)
										    		 try {
															throw new FileFormatException();
														} catch (FileFormatException e) {
															JOptionPane.showMessageDialog(frame,
																	"Error, not all the required fields were included.\n",
																    "File Format Bad",
																    JOptionPane.WARNING_MESSAGE);
															return;
														}
										    if(month.getText().trim().equals(""))	 
									    		 new1.setValue("month", null);
									    	 else
									    		 new1.setValue("month", month.getText());
								      } 
								}
								if(manType.equals("Incollection")){
									JTextField key = new JTextField(15);
									JTextField author = new JTextField(15);
									JTextField title = new JTextField(15);
									JTextField booktitle = new JTextField(15);
									JTextField year = new JTextField(15);
									JTextField month = new JTextField(15);
									JTextField chapter = new JTextField(15);
									   
									      JPanel myPanel = new JPanel();
									      myPanel.setLayout(new GridLayout(14,1));
									      myPanel.add(new JLabel("Key:"));
									      myPanel.add(key);
									      myPanel.add(new JLabel("Author:"));
									      myPanel.add(author);
									      myPanel.add(new JLabel("Title:"));
									      myPanel.add(title);
									      myPanel.add(new JLabel("Booktitle"));
									      myPanel.add(booktitle);
									      myPanel.add(new JLabel("Year:"));
									      myPanel.add(year);
									      myPanel.add(new JLabel("OPTIONAL:Month:"));
									      myPanel.add(month);
									      myPanel.add(new JLabel("OPTIONAL:Chapter:"));
									      myPanel.add(chapter);
									    
									      int x = JOptionPane.showConfirmDialog(null, myPanel, 
									              "Please enter the data below", JOptionPane.OK_CANCEL_OPTION);
									     
									      if (x == JOptionPane.OK_OPTION) {
									    	  Incollection new1 = new Incollection();
									    	  int invalid = 0;
									    	  if(key.getText().trim().equals(""))
									    		  invalid++;
									    	  else
									    		  new1.setValue("key", key.getText());
									    	  if(author.getText().trim().equals(""))
									    		  invalid++;
									    	  else
									    		  new1.setValue("author", author.getText());
									    	  if(title.getText().trim().equals(""))	 
									    		  invalid++;
									    	  else
									    		  new1.setValue("title", title.getText());
									    	  if(booktitle.getText().trim().equals(""))	 
									    		  invalid++;
									    	  else
									    		  new1.setValue("booktitle", booktitle.getText());
									    	  if(year.getText().trim().equals(""))	 
									    		  invalid++;
									    	  else
									    		  new1.setValue("year", year.getText());
									    	  if(chapter.getText().trim().equals(""))
									    		  invalid++;
									    	  else
									    		  new1.setValue("chapter",chapter.getText());
									    	  if(invalid!=0)
									    		  try {
									    			  throw new FileFormatException();
									    		  } catch (FileFormatException e) {
									    			  JOptionPane.showMessageDialog(frame,
									    					  "Error, not all the required fields were included.\n",
									    					  "File Format Bad",
									    					  JOptionPane.WARNING_MESSAGE);
									    			  return;
									    		  }
									    	  if(month.getText().trim().equals(""))	 
									    		  new1.setValue("month", null);
									    	  else
									    		  new1.setValue("month", month.getText());
									    	  if(chapter.getText().trim().equals(""))
									    		  new1.setValue("chapter", null);
									    	  else
									    		  new1.setValue("chapter",chapter.getText());
									    	  lib.addEntry(new1);
									      }
									}
									if(manType.equals("Inproceedings")){
										  JTextField key = new JTextField(15);
									      JTextField author = new JTextField(15);
									      JTextField title = new JTextField(15);
									      JTextField booktitle = new JTextField(15);
									      JTextField publisher = new JTextField(15);
									      JTextField year = new JTextField(15);
									      JTextField month = new JTextField(15);
									   	  JTextField pages = new JTextField(15);
	
									      JPanel myPanel = new JPanel();
									      myPanel.setLayout(new GridLayout(16,1));
									      myPanel.add(new JLabel("Key:"));
									      myPanel.add(key);
									      myPanel.add(new JLabel("Author:"));
									      myPanel.add(author);
									      myPanel.add(new JLabel("Title:"));
									      myPanel.add(title);
									      myPanel.add(new JLabel("BookTitle:"));
									      myPanel.add(booktitle);
									      myPanel.add(new JLabel("Publisher:"));
									      myPanel.add(publisher);
									      myPanel.add(new JLabel("Year:"));
									      myPanel.add(year);
									      myPanel.add(new JLabel("OPTIONAL:Month:"));
									      myPanel.add(month);
									      myPanel.add(new JLabel("OPTIONAL:Pages:"));
									      myPanel.add(pages);
									     
									      int x = JOptionPane.showConfirmDialog(null, myPanel, 
									              "Please enter the data below", JOptionPane.OK_CANCEL_OPTION);
									     
									      	if (x == JOptionPane.OK_OPTION) {
									      		Inproceedings new1 = new Inproceedings();
									      		int invalid = 0;
									      		if(key.getText().trim().equals(""))
									      			invalid++;
									      		else
									      			new1.setValue("key", key.getText());
									      		if(author.getText().trim().equals(""))
									      			invalid++;
									      		else
									      			new1.setValue("author", author.getText());
									      		if(title.getText().trim().equals(""))	 
									      			invalid++;
									      		else
									      			new1.setValue("title", title.getText());
									      		if(booktitle.getText().trim().equals(""))	 
									      			invalid++;
									      		else
									      			new1.setValue("booktitle", booktitle.getText());
									      		if(publisher.getText().trim().equals(""))
									      			invalid++;
									      		else
									      			new1.setValue("publisher",publisher.getText());
									      		if(year.getText().trim().equals(""))	 
									      			invalid++;
									      		else
									      			new1.setValue("year", year.getText());
									      		if(invalid!=0)
									      			try {
									      				throw new FileFormatException();
									      			} catch (FileFormatException e) {
									      				JOptionPane.showMessageDialog(frame,
									      						"Error, not all the required fields were included.\n",
									      						"File Format Bad",
									      						JOptionPane.WARNING_MESSAGE);
									      				return;
									      			}
									      		if(month.getText().trim().equals(""))	 
									      			new1.setValue("month", null);
									      		else
									      			new1.setValue("month", month.getText());
									      		if(pages.getText().trim().equals(""))
									      			new1.setValue("pages",null);
									      		else
									      			new1.setValue("pages",pages.getText());
									      		lib.addEntry(new1);
									      	}
										}
								if(manType.equals("Phdthesis")){
	
									  JTextField key = new JTextField(15);
								       JTextField author = new JTextField(15);
								       JTextField title = new JTextField(15);
								       JTextField school = new JTextField(15);
									   JTextField year = new JTextField(15);
									   JTextField month = new JTextField(15);
									   
									  JPanel myPanel = new JPanel();
									  myPanel.setLayout(new GridLayout(12,1));
								      myPanel.add(new JLabel("Key:"));
								      myPanel.add(key);
								      myPanel.add(new JLabel("Author:"));
								      myPanel.add(author);
								      myPanel.add(new JLabel("Title:"));
								      myPanel.add(title);
								      myPanel.add(new JLabel("School:"));
								      myPanel.add(school);
								      myPanel.add(new JLabel("Year:"));
								      myPanel.add(year);
								      myPanel.add(new JLabel("OPTIONAL: Month:"));
								      myPanel.add(month);
								      
								      int x = JOptionPane.showConfirmDialog(null, myPanel, 
								               "Please enter the data below", JOptionPane.OK_CANCEL_OPTION);
								      
								      if (x == JOptionPane.OK_OPTION) {
								    	 
								    	 Phdthesis new1 = new Phdthesis();
								    	 int invalid = 0;
								    	 if(key.getText().trim().equals(""))
											invalid++;
										else
								    		 new1.setValue("key", key.getText());
								    	 if(author.getText().trim().equals(""))	 
								    		 invalid++;
								    	 else
								    		 new1.setValue("author", author.getText());
								    	 if(title.getText().trim().equals(""))	 
								    		 invalid++;
								    	 else
								    		 new1.setValue("title", title.getText());
								    	 if(school.getText().trim().equals(""))	 
								    		 invalid++;
								    	 else
								    		 new1.setValue("school", school.getText());
								    	 if(year.getText().trim().equals(""))	 
								    		 invalid++;
								    	 else
								    		 new1.setValue("year", year.getText());
								    	 if(invalid!=0)
								    		 try {
													throw new FileFormatException();
												} catch (FileFormatException e) {
													JOptionPane.showMessageDialog(frame,
															"Error, not all the required fields were included.\n",
														    "File Format Bad",
														    JOptionPane.WARNING_MESSAGE);
													return;
												}
								    	 if(month.getText().trim().equals(""))	 
								    		 new1.setValue("month", null);
								    	 else
								    		 new1.setValue("month", month.getText());
								    	 lib.addEntry(new1);
								      }
								}
								if(manType.equals("Misc")){
									  JTextField key = new JTextField(15);
								       JTextField author = new JTextField(15);
									   JTextField title = new JTextField(15);
									   JTextField year = new JTextField(15);
									   JTextField month = new JTextField(15);
									  JPanel myPanel = new JPanel();
									  myPanel.setLayout(new GridLayout(10,1));
								      myPanel.add(new JLabel("Key:"));
								      myPanel.add(key);
								      myPanel.add(new JLabel("OPTIONAL : Author:"));
								      myPanel.add(author);
								      myPanel.add(new JLabel("OPTIONAL : Title:"));
								      myPanel.add(title);
								      myPanel.add(new JLabel("OPTIONAL : Year:"));
								      myPanel.add(year);
								      myPanel.add(new JLabel("OPTIONAL: Month:"));
								      myPanel.add(month);
								      
								      int x = JOptionPane.showConfirmDialog(null, myPanel, 
								               "Please enter the data below", JOptionPane.OK_CANCEL_OPTION);
								      
								      if (x == JOptionPane.OK_OPTION) {
								    	 
								    	 Misc new1 = new Misc();
								    	 new1.setValue("key", key.getText());
								    	 if(key.getText().trim().equals(""))
											try {
												throw new FileFormatException();
											} catch (FileFormatException e) {
												JOptionPane.showMessageDialog(frame,
														"Error, not all the required fields were included.\n",
													    "File Format Bad",
													    JOptionPane.WARNING_MESSAGE);
												return;
											}
										else
								    		 new1.setValue("author", author.getText());
								    	 if(author.getText().trim().equals(""))	 
								    		 new1.setValue("author", null);
								    	 else
								    		 new1.setValue("author", author.getText());
								    	 if(title.getText().trim().equals(""))	 
								    		 new1.setValue("title", null);
								    	 else
								    		 new1.setValue("title", title.getText());
								    	 
								    	 if(year.getText().trim().equals(""))	 
								    		 new1.setValue("year", null);
								    	 else
								    		 new1.setValue("year", year.getText());
								    	 if(year.getText().trim().equals(""))	 
								    		 new1.setValue("year", null);
								    	 else
								    		 new1.setValue("year", year.getText());
								    	 if(month.getText().trim().equals(""))	 
								    		 new1.setValue("month", null);
								    	 else
								    		 new1.setValue("month", month.getText());
								    	 lib.addEntry(new1);
								      	}
									}
								if(manType.equals("Online")){
									JTextField key = new JTextField(15);
									JTextField author = new JTextField(15);
									JTextField title = new JTextField(15);
									JTextField url = new JTextField(15);
									JTextField protocol = new JTextField(15);
									JTextField host = new JTextField(15);
									JTextField path = new JTextField(15);
									JTextField day = new JTextField(15);
									JTextField month = new JTextField(15);
									JTextField year = new JTextField(15);
									
									JPanel myPanel = new JPanel();
									myPanel.setLayout(new GridLayout(30,1));
									myPanel.add(new JLabel("Key: "));
									myPanel.add(key);
									myPanel.add(new JLabel("Must have Key, Title and Author"));
									myPanel.add(new JLabel("Author: "));
									myPanel.add(author);
									myPanel.add(new JLabel("Title: "));
									myPanel.add(title);
									myPanel.add(new JLabel("Must have either the URL or all Protocol, Host and Path"));
									myPanel.add(new JLabel("URL: "));
									myPanel.add(url);
									myPanel.add(new JLabel("Protocol: "));
									myPanel.add(protocol);
									myPanel.add(new JLabel("Host: "));
									myPanel.add(host);
									myPanel.add(new JLabel("Path: "));
									myPanel.add(path);
									myPanel.add(new JLabel("OPTIONAL: Day:"));
								    myPanel.add(day);
								    myPanel.add(new JLabel("OPTIONAL: Month:"));
								    myPanel.add(month);
								    myPanel.add(new JLabel("OPTIONAL: Year:"));
								    myPanel.add(year);
									
									int x = JOptionPane.showConfirmDialog(null, myPanel, 
								               "Please enter the data below", JOptionPane.OK_CANCEL_OPTION);
									
									if(x == JOptionPane.OK_OPTION){
										Online new1 = new Online();
										int invalid = 0;
										if(key.getText().trim().equals("")){
											invalid++;
										}
										else 
											new1.setValue("key", key.getText());
										if(author.getText().trim().equals("")){
											invalid++;
										}
										else 
											new1.setValue("author", author.getText());
										if(title.getText().trim().equals("")){
											invalid++;
										}
										else 
											new1.setValue("title", title.getText());
										if(url.getText().trim().equals("")){
											if(protocol.getText().trim().equals("")||
												host.getText().trim().equals("")||
												path.getText().trim().equals("")){
												
												invalid++;
											}
											else if(!protocol.getText().trim().equals("") &&
												!host.getText().trim().equals("") &&
												!path.getText().trim().equals("")){
												
												new1.setValue("protocol", protocol.getText());
												new1.setValue("host", host.getText());
												new1.setValue("path", path.getText());
											}
											
										}
										if(!url.equals("")){
											if(!protocol.getText().trim().equals("") ||
												!host.getText().trim().equals("") ||
												!path.getText().trim().equals("")){
											
												invalid++;
											}
											else
												new1.setValue("url", url.getText());
										}
										
										if(day.getText().trim().equals(""))	 
								    		  new1.setValue("day", null);
								    	  else
								    		  new1.setValue("day", day.getText());
								    	  if(month.getText().trim().equals(""))
								    		  new1.setValue("month", null);
								    	  else
								    		  new1.setValue("month",month.getText());
								    	  if(month.getText().trim().equals(""))	 
								    		  new1.setValue("month", null);
								    	  else
								    		  new1.setValue("month", month.getText());
								    	  if(year.getText().trim().equals(""))
								    		  new1.setValue("year", null);
								    	  else
								    		  new1.setValue("year",year.getText());
								    	  lib.addEntry(new1);
									}
								}
							}
						    
						    if(choice.equals("add some")){
								lib.addTestEntry("book1", "key1");
								lib.addTestEntry("book2", "key2");
								lib.addTestEntry("book3", "key3");
								lib.addTestEntry("book4", "key4");
								lib.addTestEntry("book5", "key5");
								lib.addTestEntry("book6", "key6");
								lib.addTestEntry("book7", "key7");
								lib.addTestEntry("book8", "key8");
								lib.addTestEntry("book9", "key9");
								lib.addTestEntry("book10", "key10");
							}	
						}
				    }
				}
			
		);
			// adds the "delete" button-user can choose and entry from the center pane and delete it 
						JButton deleteE = new JButton("Delete",removeIcon);
						deleteE.addActionListener(
								new ActionListener() {
									public void actionPerformed(ActionEvent arg0) {
					                            String key = null;        
					                            while(key == null) {
					                                // gets key from the user
					                                Entry selected = (Entry) entryList.getSelectedValue();
					                                if(selected != null){
					                                	key = selected.getKey();
					                                }
					                                // confirms users choice
					                                int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete \""+key+"\"?");
					                                if(response == JOptionPane.NO_OPTION){
					                                    key = null;
					                                    break;
					                                }
					                                else if(response == JOptionPane.YES_OPTION){
					                                    lib.deleteEntry(selected.getKey());
					                                    JOptionPane.showMessageDialog(null,"Citation with key \""+key+"\" has been deleted.");    
					                                }    
					                            }    
					                        }
									}
					               
						);
			// adds the "search" button
			JButton searchForE = new JButton("Search",searchIcon);
			searchForE.addActionListener(
					new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Object searchFor = null;
						Object[] types = {"Key", "Type", "Author","Title","Tags"};
						
						// has the user select what to sort by form a list of possibilities
						 searchFor = JOptionPane.showInputDialog(
						                    frame,
						                    "What would you like to search for?",
						                    "Search",
						                    JOptionPane.PLAIN_MESSAGE,
						                    null,
						                    types,
						                    "Type");

						 if(searchFor != null){
	                         for(Entry e : searchLib){
	                        	 searchLib.deleteEntry(e.getKey());
	                         	}
							if(searchFor.equals("Key")){
								String search = null;
	                            while(search == null) {
	                                search = JOptionPane.showInputDialog("Please enter the key you wish to search for.");   
	                                for(Entry e : lib){
	                                	if(e.getKey().equals(search)){
	                                		searchLib.addEntry(e);
	                                	}	
	                                }
	                            }
							}
							if(searchFor.equals("Type")){
								String search = null;
	                            while(search == null) {
	                                search = JOptionPane.showInputDialog("Please enter the type you wish to search for.");  
	                                for(Entry e : lib){
	                                	if(e.getType().equals(search)){
	                                		searchLib.addEntry(e);
	                                	}	
	                                }
	                            }
							}
							if(searchFor.equals("Author")){
								String search = null;
	                            while(search == null) {
	                                search = JOptionPane.showInputDialog("Please enter the author you wish to search for."); 
	                                for(Entry e : lib){
	                                	if(e.getAuthor().equals(search)){
	                                		searchLib.addEntry(e);
	                                	}	
	                                }
	                            }
							}
							if(searchFor.equals("Title")){
								String search = null;
	                            while(search == null) {
	                                search = JOptionPane.showInputDialog("Please enter the title you wish to search for."); 
	                                for(Entry e : lib){
	                                	if(e.getTitle().equals(search)){
	                                		searchLib.addEntry(e);
	                                	}	
	                                }
	                            }
	                        }
							if(searchFor.equals("Year")){
								String search = null;
	                            while(search == null) {
	                                search = JOptionPane.showInputDialog("Please enter the year you wish to search for.");
	                                
	                                for(Entry e : lib){
	                                	if(e.getYear() == search){
	                                		searchLib.addEntry(e);
	                                	}	
	                                }
	                            }
							}
							if(searchFor.equals("Tag")){
								String search = null;
	                            while(search == null) {
	                                search = JOptionPane.showInputDialog("Please enter the Tag you wish to search for.");
	                                
	                                for(Entry e : lib){
	                                	if(e.getTags() == search){
	                                		searchLib.addEntry(e);
	                                	}	
	                                }
	                            }
							}
						 }
					}
				}
					
			);
			// adds the "pref" button
			
			JButton prefE = new JButton("Preferences", prefIcon);
			prefE.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							Object[] possibilities = {"Red", "Blue", "Black", "Green"};
							String choice = null;
							Color change = null;
							// has the user select what to sort by form a list of possibilities
							 choice = (String)JOptionPane.showInputDialog(
							                    frame,
							                    "Background color? \n",
							                    "Preferences",
							                    JOptionPane.PLAIN_MESSAGE,
							                    null,
							                    possibilities,
							                    "Black");
							 if(choice != null){
								 if(choice.equals("Black")){
									change = Color.BLACK;
								 }
								 if(choice.equals("Red")){
									change = Color.RED;
								 }
								 if(choice.equals("Blue")){
									change = Color.BLUE;	
								 }
								 if(choice.equals("Green")){
									change = Color.GREEN;
								 }	
								 colors.add(0, change);
								 panel.setBackground(change);
								 
							 }
						 }
						}	
			);
			// adds the "sort" button
			JButton sortE = new JButton("Sort By",sortIcon);
			sortE.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Object[] possibilities = {"Key", "Type", "Author", "Title","Year", "Key (reverse)", "Type (reverse)", "Author (reverse)", "Title (reverse)", "Year (reverse)"};
					String choice = null;
					// has the user select what to sort by form a list of possibilities
					 choice = (String)JOptionPane.showInputDialog(
					                    frame,
					                    "Please the factor you wish \n"
					                    + "sort the list by. ",
					                    "Sort Citations",
					                    JOptionPane.PLAIN_MESSAGE,
					                    null,
					                    possibilities,
					                    "Key");
					 
					 if(choice != null){
						 if(choice.equals("Key")){ 
							lib.setComparator(new KeyComparator());
						 }
						 if(choice.equals("Type")){
							 lib.setComparator(new TypeKeyComparator());
						 }
						 if(choice.equals("Author")){
							 lib.setComparator(new AuthorComparator());
						 }
						 if(choice.equals("Title")){
							lib.setComparator(new TitleComparator()); 
						 }
						 if(choice.equals("Year")){
								lib.setComparator(new YearComparator());
							 }
						 if(choice.equals("Key (reverse)")){
								lib.setComparator(new Comparator<Entry>(){
									@Override
									public int compare(Entry arg0, Entry arg1) {
										KeyComparator comp = new KeyComparator();
										return 0 - comp.compare(arg0, arg1);
									}});
						 }
						if(choice.equals("Type (reverse)")){
									lib.setComparator(new Comparator<Entry>(){
										@Override
										public int compare(Entry arg0, Entry arg1) {
											KeyComparator comp = new KeyComparator();
											return 0 - comp.compare(arg0, arg1);
										}});
						 }
						 if(choice.equals("Author (reverse)")){
								lib.setComparator(new Comparator<Entry>(){
									
									@Override
									public int compare(Entry arg0, Entry arg1) {
										AuthorComparator comp = new AuthorComparator();
										return 0 - comp.compare(arg0, arg1);
									}});
						 }
						 if(choice.equals("Year (reverse)")){
								lib.setComparator(new Comparator<Entry>(){
									
									@Override
									public int compare(Entry arg0, Entry arg1) {
										YearComparator comp = new YearComparator();
										return 0 - comp.compare(arg0, arg1);
									}});
						 }
						 if(choice.equals("Title (reverse)")){
								lib.setComparator(new Comparator<Entry>(){
									
									@Override
									public int compare(Entry arg0, Entry arg1) {
										TitleComparator comp = new TitleComparator();
										return 0 - comp.compare(arg0, arg1);
									}});
						 }
						 if(choice.equals("Year (reverse)")){
								lib.setComparator(new Comparator<Entry>(){
									
									@Override
									public int compare(Entry arg0, Entry arg1) {
										YearComparator comp = new YearComparator();
										return 0 - comp.compare(arg0, arg1);
									}});
						 }
					 }
				}

				}
			);
			
			// adds the "save" button
			JButton saveE = new JButton("Save", saveIcon);
			saveE.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {

							Object choice = null;
							Object[] possibilities = {"Save All", "Save Search Result"};
							// has the user select what to sort by form a list of possibilities
							choice = JOptionPane.showInputDialog(
							                    frame,
							                    "How Would you Like to Add Entries",
							                    "Add to Library",
							                    JOptionPane.PLAIN_MESSAGE,
							                    null,
							                    possibilities,
							                    "Add From a File");
							if(choice != null){
							
									File file = null;
									PrintWriter writer;
									writer = null;
									
								if(choice.equals("Save All")){
									try {
										
										JFileChooser chooser = new JFileChooser();
										int returnVal = chooser.showOpenDialog(frame);
									
									    if(returnVal == JFileChooser.APPROVE_OPTION) {
									       System.out.println("You chose to open this file: " +
									            new PrintWriter(chooser.getSelectedFile()));
									       	
									       writer = new PrintWriter(chooser.getSelectedFile());
									       
										       for(Entry e : lib){
													writer.println(e.toString());	
												}
												
										       writer.close();
										       JOptionPane.showMessageDialog(frame, "All data has been saved!", " ", JOptionPane.INFORMATION_MESSAGE, null);
									    }
									
									} catch (FileNotFoundException e) {
									    JOptionPane.showMessageDialog(frame,
													"Error, this file is not formated properly\n",
												    "File Format Bad",
												    JOptionPane.WARNING_MESSAGE);
									}
								}
								else if(choice.equals("Save Search Result")){
									try {
										JFileChooser chooser = new JFileChooser();
										int returnVal = chooser.showOpenDialog(frame);
									
									    if(returnVal == JFileChooser.APPROVE_OPTION) {
									       System.out.println("You chose to open this file: " +
									            new PrintWriter(chooser.getSelectedFile()));
									       	
									       writer = new PrintWriter(chooser.getSelectedFile());
									       
										       for(Entry e : searchLib){
													writer.println(e.toString());	
												}
												
										       writer.close();
										       JOptionPane.showMessageDialog(frame, "All data has been saved!", " ", JOptionPane.INFORMATION_MESSAGE, null);
									    }
									}catch(FileNotFoundException e) {
									    JOptionPane.showMessageDialog(frame,
												"Error, this file is not formated properly\n",
											    "File Format Bad",
											    JOptionPane.WARNING_MESSAGE);
									}
								}
							}
					}
				}	
			);
			// adds the "help" button that offers explanations of stuff
			JButton helpE = new JButton("Help", helpIcon);
			helpE.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							JOptionPane.showMessageDialog(frame,
									"You need more help that we can offer...");
							JOptionPane.showMessageDialog(frame,
							"..but this may help with problems related to BibTech.\n"
							+ "\n"		
							+ "\"Add\" gives options to add and citations from a file,.\n"
							+ "from a URL, or by manual entry.\n"
							+ "\n"
							+ "\"Remove\" allows your to remove any citation by simply highlighting it and then hitting delete.\n"
							+ "\n"
							+ "\"Search\" allows you to search for a specific entry using several diffrent factors.\n"
							+ "\n"
							+ "\"Sort\" allows you to sort the list of entries by diffrent type of factors.\n"
							+ "\n"
							+ "\"Prefrences\" allows you to customize the look of your window to your spefications .\n"
							+ "\n"
							+ "\"Save\" saves all your files to a location of your choosing.\n"
							+ "\n"
							+ "\"Help\" please see help.\n"
							+ "\n"
						    + "Please enjoy your time using BibTech");
						}
					}	
			);
			
			//sets up the list panel in the center
			JPanel list = new JPanel();
			list.add(new JList());
			
			//Sets up the top icon/button bar
			JPanel topBar = new JPanel();
			topBar.setLayout(new GridLayout(1,7));
			topBar.add(addE);
			topBar.add(deleteE);
			topBar.add(searchForE);
			topBar.add(sortE);
			topBar.add(saveE);
			topBar.add(helpE);
			topBar.add(prefE);
			topBar.setOpaque(false);
			
			//Sets up and adds right preview panel
			JPanel searchP = new JPanel();
			searchP.setLayout(new BorderLayout());
			searchP.add(new JLabel("       Search Results      "+"\n \n \n"), BorderLayout.NORTH);
			searchP.add(sScroll, BorderLayout.CENTER);
		
			//Sets up and adds left "make bib" panel
			JPanel editPane = new JPanel();
				//12 main field types
			   final JTextField author = new JTextField(15);
			   final JTextField editor = new JTextField(15);
			   final JTextField title = new JTextField(15);
			   final JTextField chapter = new JTextField(15);
			   final JTextField pages = new JTextField(15);
			   final JTextField publisher = new JTextField(15);
			   final JTextField journal = new JTextField(15);
			   final JTextField booktitle = new JTextField(15);
			   final JTextField edition = new JTextField(15);
			   final JTextField school = new JTextField(15);
			   final JTextField year = new JTextField(15);
			   final JTextField month = new JTextField(15);
			   final JTextField note = new JTextField(15);
			   final JTextField tag = new JTextField(15);
			   final JTextField type = new JTextField(15);
			   final JTextField key = new JTextField(15);
			   final JTextField url = new JTextField(15);
			   final JTextField protocol = new JTextField(15);
			   final JTextField host = new JTextField(15);
			   final JTextField path = new JTextField(15);
			   final JTextField day = new JTextField(15);
			   
		      JPanel editingPane = new JPanel();
		      editingPane.setLayout(new GridLayout(45,1));
		      editingPane.add(new JLabel("Type:"));
		      editingPane.add(type);
		      type.setEditable(false);
		      editingPane.add(new JLabel("Key:"));
		      editingPane.add(key);
		      key.setEditable(false);
		      type.setEditable(false);
		      editingPane.add(new JLabel("-------Must have Author or Editor-------"));
		      editingPane.add(new JLabel("Author:"));
		      editingPane.add(author);
		      editingPane.add(new JLabel("Editor:"));
		      editingPane.add(editor);
		      editingPane.add(new JLabel("Title:"));
		      editingPane.add(title);
		      editingPane.add(new JLabel("Journal:"));
		      editingPane.add(journal);
		      editingPane.add(new JLabel("-----Must have Chapter and or Pages-----"));
		      editingPane.add(new JLabel("Chapter:"));
		      editingPane.add(chapter);
		      editingPane.add(new JLabel("Pages:"));
		      editingPane.add(pages);
		      editingPane.add(new JLabel("Publisher:"));
		      editingPane.add(publisher);
		      editingPane.add(new JLabel("Edition:"));
		      editingPane.add(edition);
		      editingPane.add(new JLabel("Booktitle:"));
		      editingPane.add(booktitle);
		      editingPane.add(new JLabel("School:"));
		      editingPane.add(school);
		      editingPane.add(new JLabel("Year:"));
		      editingPane.add(year);
		      editingPane.add(new JLabel("Month:"));
		      editingPane.add(month);
		      editingPane.add(new JLabel("Day:"));
		      editingPane.add(day);
		      editingPane.add(new JLabel("-----Must have URL and or Combo of Protocol, Host and Path-----"));
		      editingPane.add(new JLabel("URL:"));
		      editingPane.add(url);
		      editingPane.add(new JLabel("Protocol:"));
		      editingPane.add(protocol);
		      editingPane.add(new JLabel("Host:"));
		      editingPane.add(host);
		      editingPane.add(new JLabel("Path:"));
		      editingPane.add(path);
		      editingPane.add(new JLabel("---------------Notes---------------"));
		      editingPane.add(note);
		      editingPane.add(new JLabel("---------------Tags---------------"));
		      editingPane.add(tag);
		      
		      author.setEditable(false);   
		      editor.setEditable(false);   
			  title.setEditable(false);   
			  chapter.setEditable(false);   
			  pages.setEditable(false);   
			  publisher.setEditable(false);   
			  journal.setEditable(false);   
			  booktitle.setEditable(false);   
			  edition .setEditable(false);   
			  school.setEditable(false);   
			  year.setEditable(false);   
			  month.setEditable(false);
			  day.setEditable(false);   
			  url.setEditable(false);   
			  protocol.setEditable(false);
			  host.setEditable(false);   
			  path.setEditable(false); 
			
			  
			// adds the "edit" button
				final JButton editE = new JButton("Edit", editIcon);
				final JButton saveEditE = new JButton("Save Edits", saveIcon);
				editE.addActionListener(
						new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {	
								  author.setEditable(false);   
							      editor.setEditable(false);   
								  title.setEditable(false);   
								  chapter.setEditable(false);   
								  pages.setEditable(false);   
								  publisher.setEditable(false);   
								  journal.setEditable(false);   
								  booktitle.setEditable(false);   
								  edition .setEditable(false);   
								  school.setEditable(false);   
								  year.setEditable(false);   
								  month.setEditable(false);
								  day.setEditable(false);   
								  url.setEditable(false);   
								  protocol.setEditable(false);
								  host.setEditable(false);   
								  path.setEditable(false); 

								 saveEditE.setEnabled(true);
								  editE.setEnabled(false);
								
								Entry edit;
								note.setEditable(true);
								edit = (Entry) entryList.getSelectedValue();
								String typeof = edit.getType();
								if(typeof.equalsIgnoreCase("book")){
									  
									  Book editItem = (Book) edit;
									  type.setText("Book");
									  key.setText(editItem.getKey());
									  author.setEditable(true);
									  author.setText(editItem.getAuthor());
								      editor.setEditable(true);
								      editor.setText(editItem.getEditor());
									  title.setEditable(true);  
									  title.setText(edit.getTitle());
									  publisher.setEditable(true);
									  publisher.setText(editItem.getPublisher());
									  year.setEditable(true); 
									  year.setText(editItem.getYear());
									  edition .setEditable(true); 
									  edition.setText(editItem.getEdition());
									  month.setEditable(true);
									  month.setText(editItem.getMonth());
									  note.setText(editItem.getNotes());
									  tag.setText(editItem.getTags());
								}
								else if(typeof.equalsIgnoreCase("article")){
									Article editItem = (Article)  edit; 
									type.setText("Article");
									key.setText(editItem.getKey());
									author.setEditable(true);  
									  author.setText(editItem.getAuthor());
									  title.setEditable(true);  
									  title.setText(edit.getTitle());
									  journal.setEditable(true);
									  journal.setText(editItem.getJournal());
									  year.setEditable(true);
									  year.setText(editItem.getYear());
									  month.setEditable(true);
									  month.setText(editItem.getMonth());
									  note.setText(editItem.getNotes());
									  tag.setText(editItem.getTags());
								}
								else if(typeof.equalsIgnoreCase("PhdThesis")){
									Phdthesis editItem = (Phdthesis)  edit;
									type.setText("Phdthesis");
									key.setText(editItem.getKey());
									author.setEditable(true);
									author.setText(editItem.getAuthor());
									  title.setEditable(true); 
									  title.setText(edit.getTitle());
									  school.setEditable(true);
									  school.setText(editItem.getSchool());
									  year.setEditable(true); 
									  year.setText(editItem.getYear());
									  month.setEditable(true); 
									  month.setText(editItem.getMonth());
									  note.setText(editItem.getNotes());
									  tag.setText(editItem.getTags());
									  
								}
								else if(typeof.equalsIgnoreCase("Inproceedings")){
									Inproceedings editItem = (Inproceedings)  edit; 
									type.setText("Inproceedings");
									key.setText(editItem.getKey());
									author.setEditable(true);
									author.setText(editItem.getAuthor());
									  title.setEditable(true); 
									  title.setText(edit.getTitle());
									  booktitle.setEditable(true); 
									  booktitle.setText(editItem.getBookTitle());
									  publisher.setEditable(true); 
									  publisher.setText(editItem.getPublisher());
									  year.setEditable(true);
									  year.setText(editItem.getYear());
									  month.setEditable(true);
									  month.setText(editItem.getMonth());
									  pages.setEditable(true); 
									  note.setText(editItem.getNotes());
									  tag.setText(editItem.getTags());
								}
								else if(typeof.equalsIgnoreCase("incollection")){
									Incollection editItem = (Incollection)  edit;
									type.setText("Incollections");
									key.setText(editItem.getKey());
									author.setEditable(true); 
									author.setText(editItem.getAuthor());
									  title.setEditable(true);
									  title.setText(edit.getTitle());
									  booktitle.setEditable(true); 
									  booktitle.setText(editItem.getBookTitle());
									  year.setEditable(true); 
									  year.setText(editItem.getYear());
									  month.setEditable(true);
									  month.setText(editItem.getMonth());
									  chapter.setEditable(true);
									  note.setText(editItem.getNotes());
									  tag.setText(editItem.getTags());
								}
					    		else if(typeof.equalsIgnoreCase("inbook")){
					    			Inbook editItem = (Inbook)  edit;
					    			type.setText("Inbook");
					    			key.setText(editItem.getKey());
					    			author.setEditable(true);
					    			author.setText(editItem.getAuthor());
								      editor.setEditable(true);  
								      editor.setText(editItem.getEditor());
									  title.setEditable(true); 
									  title.setText(edit.getTitle());
									  chapter.setEditable(true);
									  chapter.setText(editItem.getChapter());
									  pages.setEditable(true);  
									  pages.setText(editItem.getPages());
									  publisher.setEditable(true); 
									  publisher.setText(editItem.getPublisher());
									  year.setEditable(true); 
									  year.setText(editItem.getYear());
									  month.setEditable(true);
									  month.setText(editItem.getMonth());
									  note.setText(editItem.getNotes());
									  tag.setText(editItem.getTags());
					    		}
					    		else if(typeof.equalsIgnoreCase("Misc")){
					    			Misc editItem = (Misc)  edit;
					    			type.setText("Misc");
					    			key.setText(editItem.getKey());
					    			author.setEditable(true);
					    			author.setText(editItem.getAuthor());
									  title.setEditable(true); 
									  title.setText(edit.getTitle());
									  year.setEditable(true);
									  year.setText(editItem.getYear());
									  month.setEditable(true); 
									  month.setText(editItem.getMonth());
									  note.setText(editItem.getNotes());
									  tag.setText(editItem.getTags());
					    		}
					    		else if(typeof.equalsIgnoreCase("online")){
									  
									  Online editItem = (Online) edit;
									  type.setText("Online");
									  key.setText(editItem.getKey());
									  author.setEditable(true);
									  author.setText(editItem.getAuthor());
									  title.setEditable(true);  
									  title.setText(edit.getTitle());
									  url.setEditable(true);
									  url.setText(editItem.getUrl());
									  protocol.setEditable(true); 
									  protocol.setText(editItem.getProtocol());
									  host.setEditable(true); 
									  host.setText(editItem.getHost());
									  path.setEditable(true);
									  path.setText(editItem.getMonth());
									  day.setEditable(true);
									  day.setText(editItem.getDay());
									  month.setEditable(true);
									  month.setText(editItem.getMonth());
									  year.setEditable(true);
									  year.setText(editItem.getYear());
									  note.setText(editItem.getNotes());
									  tag.setText(editItem.getTags());
					    		}
						       
						}		
					}	
					);
			      
			// add the save button for editing
			  
				saveEditE.addActionListener(
						new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								String savkey = key.getText().trim();
								String savtype = type.getText().trim();
								 author.setEditable(false);   
							      editor.setEditable(false);   
								  title.setEditable(false);   
								  chapter.setEditable(false);   
								  pages.setEditable(false);   
								  publisher.setEditable(false);   
								  journal.setEditable(false);   
								  booktitle.setEditable(false);   
								  edition .setEditable(false);   
								  school.setEditable(false);   
								  year.setEditable(false);   
								  month.setEditable(false);
								  
								  if(savtype != null){
									if(savtype.equals("Article")){
									    	  Article new1 = new Article();
										    	 int invalid = 0;
										    	 if(key.getText().trim().equals(""))
														invalid++;
										    	 else
											    	new1.setValue("key", key.getText().trim());
										    	 if(author.getText().trim().equals(""))
														invalid++;
										    	 else
											    	new1.setValue("author", author.getText());
										    	 if(title.getText().trim().equals(""))	 
											    	 invalid++;
											    else
											    	new1.setValue("title", title.getText());
										    	 if(journal.getText().trim().equals(""))	 
											    	 invalid++;
											    else
											    	new1.setValue("journal", journal.getText());
											    if(year.getText().trim().equals(""))	 
										    		 invalid++;
											    else
											    	new1.setValue("year", year.getText());
											    if(invalid!=0)
											    		 try {
																throw new FileFormatException();
															} catch (FileFormatException e1) {
																JOptionPane.showMessageDialog(frame,
																		"Error, not all the required fields were included.\n",
																	    "File Format Bad",
																	    JOptionPane.WARNING_MESSAGE);
																return;
															}
											    if(month.getText().trim().equals(""))	 
										    		 new1.setValue("month", null);
										    	 else
										    		 new1.setValue("month", month.getText());
											    	 lib.deleteEntry(savkey);
											    	 lib.addEntry(new1);
											    if(note.getText().trim().equals(""))	 
											    	 new1.setValue("note", null);
											    else
											    	new1.setValue("note", note.getText());
											    if(tag.getText().trim().equals(""))	 
											    	 new1.setValue("tag", null);
											    else
											    	new1.setValue("tag", tag.getText());
												    lib.deleteEntry(savkey);
												    lib.addEntry(new1);									    	 
									 } 
									if(savtype.equals("Book")){
										  
									    	  Book new1 = new Book();
										    	 int invalid = 0;
										    	 if(key.getText().trim().equals(""))
														invalid++;
										    	 else
											    	new1.setValue("key", key.getText());
										    	 if(((author.getText().trim().equals("")) && (editor.getText().trim().equals(""))) ||
										    			 (author.getText().length()>1) && (editor.getText().length()>1)){ 
											    		 invalid++;
										    	 }
										    	 if(author.getText().trim().equals("")){
										    		 new1.setValue("author", null);
											    	 new1.setValue("editor", editor.getText());
										    	 }
											    else
											    	new1.setValue("editor", null);
											    	new1.setValue("author", author.getText());
											    if(title.getText().trim().equals(""))	 
											    	 invalid++;
											    else
											    	new1.setValue("title", title.getText());	 
											    if(publisher.getText().trim().equals(""))	 
										    		 invalid++;
											    else
											    	new1.setValue("publisher", publisher.getText());
											    if(year.getText().trim().equals(""))	 
										    		 invalid++;
											    else
											    	new1.setValue("year", year.getText());
											    if(invalid!=0)
											    		 try {
																throw new FileFormatException();
															} catch (FileFormatException e1) {
																JOptionPane.showMessageDialog(frame,
																		"Error, not all the required fields were included.\n",
																	    "File Format Bad",
																	    JOptionPane.WARNING_MESSAGE);
																return;
															}
											    if(month.getText().trim().equals(""))	 
										    		 new1.setValue("month", null);
										    	 else
										    		 new1.setValue("month", month.getText());
											    if(edition.getText().trim().equals(""))	 
										    		 new1.setValue("edition", null);
										    	 else
										    		 new1.setValue("edition", edition.getText());
											    if(note.getText().trim().equals(""))	 
											    	 new1.setValue("note", null);
											    else
											    	new1.setValue("note", note.getText());
											    if(tag.getText().trim().equals(""))	 
											    	 new1.setValue("tag", null);
											    else
											    	new1.setValue("tag", tag.getText());
											    	lib.deleteEntry(savkey);
											    	 lib.addEntry(new1);   
									}
									if(savtype.equals("Inbook")){
										   
									    	  Inbook new1 = new Inbook();
										    	 int invalid = 0;
										    	 if(key.getText().trim().equals(""))
														invalid++;
										    	 else
											    	new1.setValue("key", key.getText());
										    	 if(((author.getText().trim().equals("")) && (editor.getText().trim().equals(""))) ||
										    			 (author.getText().length()>1) && (editor.getText().length()>1)){ 
											    		 invalid++;
										    	 }
										    	 if(author.getText().trim().equals("")){
										    		 new1.setValue("author", null);
											    	 new1.setValue("editor", editor.getText());
										    	 }
											    else
											    	new1.setValue("editor", null);
											    	new1.setValue("author", author.getText());
											    if(title.getText().trim().equals(""))	 
											    	 invalid++;
											    else
											    	new1.setValue("title", title.getText());	 
											    if((chapter.getText().trim().equals("")) && (pages.getText().trim().equals(""))) 
										    		 invalid++;
										    	if((chapter.getText().length()>1) && (pages.getText().length()>1)){ 
										    		new1.setValue("chapter", chapter.getText());
										    		new1.setValue("pages", pages.getText());
										    	}
										    	else if((chapter.getText().length()>1) && (pages.getText().trim().equals(""))){
										    		new1.setValue("chapter", chapter.getText());
										    	}
										    	else
										    		new1.setValue("pages", pages.getText());
											    if(publisher.getText().trim().equals(""))	 
										    		 invalid++;
											    else
											    	new1.setValue("publisher", publisher.getText());
											    if(year.getText().trim().equals(""))	 
										    		 invalid++;
											    else
											    	new1.setValue("year", year.getText());
											    if(invalid!=0)
											    		 try {
																throw new FileFormatException();
															} catch (FileFormatException e1) {
																JOptionPane.showMessageDialog(frame,
																		"Error, not all the required fields were included.\n",
																	    "File Format Bad",
																	    JOptionPane.WARNING_MESSAGE);
																return;
															}
											    if(month.getText().trim().equals(""))	 
										    		 new1.setValue("month", null);
										    	 else
										    		 new1.setValue("month", month.getText());
											    if(note.getText().trim().equals(""))	 
											    	 new1.setValue("note", null);
											    else
											    	new1.setValue("note", note.getText());
											    if(tag.getText().trim().equals(""))	 
											    	 new1.setValue("tag", null);
											    else
											    	new1.setValue("tag", tag.getText());
											    lib.deleteEntry(savkey);
										        lib.addEntry(new1);      
									}
									if(savtype.equals("Incollection")){
										    	  Incollection new1 = new Incollection();
										    	  int invalid = 0;
										    	  if(key.getText().trim().equals(""))
										    		  invalid++;
										    	  else
										    		  new1.setValue("key", key.getText());
										    	  if(author.getText().trim().equals(""))
										    		  invalid++;
										    	  else
										    		  new1.setValue("author", author.getText());
										    	  if(title.getText().trim().equals(""))	 
										    		  invalid++;
										    	  else
										    		  new1.setValue("title", title.getText());
										    	  if(booktitle.getText().trim().equals(""))	 
										    		  invalid++;
										    	  else
										    		  new1.setValue("booktitle", booktitle.getText());
										    	  if(year.getText().trim().equals(""))	 
										    		  invalid++;
										    	  else
										    		  new1.setValue("year", year.getText());
										    	  if(chapter.getText().trim().equals(""))
										    		  invalid++;
										    	  else
										    		  new1.setValue("chapter",chapter.getText());
										    	  if(invalid!=0)
										    		  try {
										    			  throw new FileFormatException();
										    		  } catch (FileFormatException e1) {
										    			  JOptionPane.showMessageDialog(frame,
										    					  "Error, not all the required fields were included.\n",
										    					  "File Format Bad",
										    					  JOptionPane.WARNING_MESSAGE);
										    			  return;
										    		  }
										    	  if(month.getText().trim().equals(""))	 
										    		  new1.setValue("month", null);
										    	  else
										    		  new1.setValue("month", month.getText());
										    	  if(chapter.getText().trim().equals(""))
										    		  new1.setValue("chapter", null);
										    	  else
										    		  new1.setValue("chapter",chapter.getText());
										    	  if(note.getText().trim().equals(""))	 
												    	 new1.setValue("note", null);
												    else
												    	new1.setValue("note", note.getText());
										    	  if(tag.getText().trim().equals(""))	 
												    	 new1.setValue("tag", null);
												    else
												    	new1.setValue("tag", tag.getText());
										    	  lib.deleteEntry(savkey);
											      lib.addEntry(new1);     
										}
										if(savtype.equals("Inproceedings")){
											        Inproceedings new1 = new Inproceedings();
										      		int invalid = 0;
										      		if(key.getText().trim().equals(""))
										      			invalid++;
										      		else
										      			new1.setValue("key", key.getText());
										      		if(author.getText().trim().equals(""))
										      			invalid++;
										      		else
										      			new1.setValue("author", author.getText());
										      		if(title.getText().trim().equals(""))	 
										      			invalid++;
										      		else
										      			new1.setValue("title", title.getText());
										      		if(booktitle.getText().trim().equals(""))	 
										      			invalid++;
										      		else
										      			new1.setValue("booktitle", booktitle.getText());
										      		if(publisher.getText().trim().equals(""))
										      			invalid++;
										      		else
										      			new1.setValue("publisher",publisher.getText());
										      		if(year.getText().trim().equals(""))	 
										      			invalid++;
										      		else
										      			new1.setValue("year", year.getText());
										      		if(invalid!=0)
										      			try {
										      				throw new FileFormatException();
										      			} catch (FileFormatException e1) {
										      				JOptionPane.showMessageDialog(frame,
										      						"Error, not all the required fields were included.\n",
										      						"File Format Bad",
										      						JOptionPane.WARNING_MESSAGE);
										      				return;
										      			}
										      		if(month.getText().trim().equals(""))	 
										      			new1.setValue("month", null);
										      		else
										      			new1.setValue("month", month.getText());
										      		if(pages.getText().trim().equals(""))
										      			new1.setValue("pages",null);
										      		else
										      			new1.setValue("pages",pages.getText());
										      		if(note.getText().trim().equals(""))	 
												    	 new1.setValue("note", null);
												    else
												    	new1.setValue("note", note.getText());
										      		 if(tag.getText().trim().equals(""))	 
												    	 new1.setValue("tag", null);
												    else
												    	new1.setValue("tag", tag.getText());
										      		lib.deleteEntry(savkey);
											    	 lib.addEntry(new1);
										      	}
									if(savtype.equals("Phdthesis")){
										Phdthesis new1 = new Phdthesis();
									    	 int invalid = 0;
									    	 if(key.getText().trim().equals(""))
												invalid++;
											else
									    		 new1.setValue("key", key.getText());
									    	 if(author.getText().trim().equals(""))	 
									    		 invalid++;
									    	 else
									    		 new1.setValue("author", author.getText());
									    	 if(title.getText().trim().equals(""))	 
									    		 invalid++;
									    	 else
									    		 new1.setValue("title", title.getText());
									    	 if(school.getText().trim().equals(""))	 
									    		 invalid++;
									    	 else
									    		 new1.setValue("school", school.getText());
									    	 if(year.getText().trim().equals(""))	 
									    		 invalid++;
									    	 else
									    		 new1.setValue("year", year.getText());
									    	 if(invalid!=0)
									    		 try {
														throw new FileFormatException();
													} catch (FileFormatException e1) {
														JOptionPane.showMessageDialog(frame,
																"Error, not all the required fields were included.\n",
															    "File Format Bad",
															    JOptionPane.WARNING_MESSAGE);
														return;
													}
									    	 if(month.getText().trim().equals(""))	 
									    		 new1.setValue("month", null);
									    	 else
									    		 new1.setValue("month", month.getText());
									    	 if(note.getText().trim().equals(""))	 
										    	 new1.setValue("note", null);
										    else
										    	new1.setValue("note", note.getText());
									    	 if(tag.getText().trim().equals(""))	 
										    	 new1.setValue("tag", null);
										    else
										    	new1.setValue("tag", tag.getText());
									    	 lib.deleteEntry(savkey);
									    	 lib.addEntry(new1);
									}
									if(savtype.equals("Online")){
										  
								    	  Online new1 = new Online();
									    	 int invalid = 0;
									    	 if(key.getText().trim().equals(""))
													invalid++;
									    	 else
										    	new1.setValue("key", key.getText());
									    	 if(!url.getText().equals("")){
									    		 new1.setValue("url", url.getText());
									    	 }
									    	 
										    new1.setValue("protocol", protocol.getText());
										    new1.setValue("host", host.getText());
										    new1.setValue("path", path.getText());
									    	 
									    	if(author.getText().trim().equals(""))
													invalid++;
									    	else
										    	new1.setValue("author", author.getText());
										    if(title.getText().trim().equals(""))	 
										    	 invalid++;
										    else
										    	new1.setValue("title", title.getText());	 
						
										    if(invalid!=0)
										    		 try {
															throw new FileFormatException();
														} catch (FileFormatException e1) {
															JOptionPane.showMessageDialog(frame,
																	"Error, not all the required fields were included.\n",
																    "File Format Bad",
																    JOptionPane.WARNING_MESSAGE);
															return;
														}
										    if(month.getText().trim().equals(""))	 
									    		 new1.setValue("month", null);
									    	 else
									    		 new1.setValue("month", month.getText());
										    if(year.getText().trim().equals(""))	 
									    		 new1.setValue("year", null);
									    	 else
									    		 new1.setValue("year", year.getText());
										    if(day.getText().trim().equals(""))	 
									    		 new1.setValue("day", null);
									    	 else
									    		 new1.setValue("day", day.getText());
										    if(note.getText().trim().equals(""))	 
										    	 new1.setValue("note", null);
										    else
										    	new1.setValue("note", note.getText());
										    if(tag.getText().trim().equals(""))	 
										    	 new1.setValue("tag", null);
										    else
										    	new1.setValue("tag", tag.getText());
										    	lib.deleteEntry(savkey);
										    	 lib.addEntry(new1);   
								}
									if(savtype.equals("Misc")){
									    	 Misc new1 = new Misc();
									    	 new1.setValue("key", key.getText());
									    	 if(key.getText().trim().equals(""))
												try {
													throw new FileFormatException();
												} catch (FileFormatException e1) {
													JOptionPane.showMessageDialog(frame,
															"Error, not all the required fields were included.\n",
														    "File Format Bad",
														    JOptionPane.WARNING_MESSAGE);
													return;
												}
											else
									    		 new1.setValue("author", author.getText());
									    	 if(author.getText().trim().equals(""))	 
									    		 new1.setValue("author", null);
									    	 else
									    		 new1.setValue("author", author.getText());
									    	 if(title.getText().trim().equals(""))	 
									    		 new1.setValue("title", null);
									    	 else
									    		 new1.setValue("title", title.getText());
									    	 
									    	 if(year.getText().trim().equals(""))	 
									    		 new1.setValue("year", null);
									    	 else
									    		 new1.setValue("year", year.getText());
									    	 if(year.getText().trim().equals(""))	 
									    		 new1.setValue("year", null);
									    	 else
									    		 new1.setValue("year", year.getText());
									    	 if(month.getText().trim().equals(""))	 
									    		 new1.setValue("month", null);
									    	 else
									    		 new1.setValue("month", month.getText());
									    	 if(note.getText().trim().equals(""))	 
										    	 new1.setValue("note", null);
										    else
										    	new1.setValue("note", note.getText());
									    	 if(tag.getText().trim().equals(""))	 
										    	 new1.setValue("tag", null);
										    else
										    	new1.setValue("tag", tag.getText());
									    	 lib.deleteEntry(savkey);
									    	 lib.addEntry(new1); 
									}
									
									//clears out all the text fields
										author.setText(""); editor.setText(""); title.setText("");
									   chapter.setText(""); pages.setText(""); publisher.setText("");
									   journal.setText(""); booktitle.setText(""); edition.setText("");
									   school.setText(""); year.setText(""); month.setText("");
									   note.setText("");  type.setText(""); key.setText(""); note.setText(""); tag.setText("");
									   url.setText(""); protocol.setText(""); host.setText(""); path.setText(""); day.setText("");
									   saveEditE.setEnabled(false); 
											editE.setEnabled(true);
								}
							}
						}	
					);
			
			editPane.setLayout(new BorderLayout());
			editPane.add(editingPane,BorderLayout.CENTER);
			
			JPanel editButtons = new JPanel();
			editButtons.setLayout(new GridLayout(1,2));
			editButtons.add(editE);
			editButtons.add(saveEditE);
			editButtons.setOpaque(false);

			editPane.add(editButtons,BorderLayout.NORTH);
			editPane.setOpaque(false);
			
			//Sets up and adds the bottom label 
			JPanel bottomLabel = new JPanel();
			JLabel words = new JLabel("Brought To You By GROUP");
			bottomLabel.add(words);
			words.setForeground(Color.red);
			bottomLabel.setOpaque(false);
			
			// adds all of the smaller panels to the main window in the appropriate location
			panel.add(topBar, BorderLayout.NORTH);
			panel.add(scroll, BorderLayout.CENTER);
			panel.add(bottomLabel, BorderLayout.SOUTH);
			panel.add(editPane, BorderLayout.WEST);
			panel.add(searchP, BorderLayout.EAST);
			
			
			frame.setContentPane(panel);
			//frame.setIconImage(mainIcon);
			
			frame.setVisible(true);
			/*
			JOptionPane.showMessageDialog(frame,
					"Are you sure you want to close BibTech \n"
						    + "all unsaved data will be lost.\n"
						    + "Select \"save\" to save data. ",
				    "Closing Warning",
				    JOptionPane.WARNING_MESSAGE);
		*/
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		}
	}

