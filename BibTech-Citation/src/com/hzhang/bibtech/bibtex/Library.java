package com.hzhang.bibtech.bibtex;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import com.hzhang.bibtech.comparators.KeyComparator;
import com.hzhang.bibtech.entries.Article;
import com.hzhang.bibtech.entries.Book;
import com.hzhang.bibtech.entries.Entry;
import com.hzhang.bibtech.entries.Inbook;
import com.hzhang.bibtech.entries.Incollection;
import com.hzhang.bibtech.entries.Inproceedings;
import com.hzhang.bibtech.entries.Misc;
import com.hzhang.bibtech.entries.Online;
import com.hzhang.bibtech.entries.Phdthesis;
import com.hzhang.bibtech.exceptions.FileFormatException;


/**
* CSC232 - Spring 2013
*
* This class will store a collection of Entry class objects in an
* ArrayList. The class will also offer various method for functional
and testing purposes.
*
* @author (GROUP)
* @version (HW10)
*/

public class Library implements ListModel, Iterable<Entry> {
	private ArrayList<ListDataListener> listeners = new ArrayList<ListDataListener>();
	private HashMap<String, Entry> entries;
	private Comparator<Entry> sortOrder = new KeyComparator();
   
	/**
    * Constructor for objects of class Library
    */
   public Library(){
	   entries = new HashMap<String, Entry>();
   }

   /**
    * this instance method creates a new entry.
    * @param author
    * @param publisher
    * @param year
    * @param month
    * @param edition
    * @param Booktitle
    */
   public void addTestEntry(String type, String key){

		   Entry e1 = new Book();
		   e1.setValue("key",key);
		   entries.put(e1.getKey(), e1);
		   notifyListeners();
	   }
   
   /**
    * This method uses a for loop to add multiple entries to an arrayList list
    * @param list
    */
   public void addEntries(ArrayList<Entry> list){
	   for(Entry e : list){
		   createEntry(e, e.getType(),e.getKey());   
	   }  
   }
   public void addEntry(Entry newe){
	   entries.put(newe.getKey(), newe);
	   notifyListeners();
   }
   
   /**
    * This method creates a new Entry object based on type
    * @param e
    * @param type
    * @param key
    */
   public void createEntry(Entry e, String type, String key)
   {

	if(type.equalsIgnoreCase("Article")){
		   Article ea = new Article();
		   ea = (Article) e;
		   ea.setValue("key",key);
		   entries.put(ea.getKey(), ea);
	   }
	   
	   else if(type.equalsIgnoreCase("Book")){ 
		   Book eb = new Book();
		   eb = (Book) e;
		   eb.setValue("key",key);
		   entries.put(eb.getKey(), eb);
	   }
	   
	   else if(type.equalsIgnoreCase("Inbook")){
		   Inbook e1 = new Inbook();
		   e1 = (Inbook) e;
		   e1.setValue("key",key);
		   entries.put(e1.getKey(), e1);
	   }
	   
	   else if(type.equalsIgnoreCase("Incollection")){
		   Incollection e1 = new Incollection();
		   e1 = (Incollection) e;
		   e1.setValue("key",key);
		   entries.put(e1.getKey(), e1);
	   }
	   
	   else if(type.equalsIgnoreCase("Inproceedings")){
		   Inproceedings e1 = new Inproceedings();
		   e1 = (Inproceedings) e;
		   e1.setValue("key",key);
		   entries.put(e1.getKey(), e1);
	   }
	   
	   else if(type.equalsIgnoreCase("Misc")){
		  Misc e1 = new Misc();
		   e1 = (Misc) e;
		   e1.setValue("key",key);
		   entries.put(e1.getKey(), e1);
	   }
	   
	   else if(type.equalsIgnoreCase("Phdthesis")){
		  Phdthesis e1 = new Phdthesis();
		   e1 = (Phdthesis) e;
		   e1.setValue("key",key);
		   entries.put(e1.getKey(), e1);
	   }
	   else if(type.equalsIgnoreCase("Online")){
			  Online e1 = new Online();
			   e1 = (Online) e;
			   e1.setValue("key",key);
			   entries.put(e1.getKey(), e1);
		   }
	   notifyListeners();
   }


   /**
    * this instance method removes the entry object with parameter key
      from the Entry class
    *
    * @param key of the object that is to be deleted.
    */
   public void deleteEntry(String key){
   	entries.remove(key);
   	notifyListeners();
	}

   /**
    * this instance method looks for the entry objects with parameter type
      from the Entry class
    * @param type type of the entry object that one is searching for.
    * @return an ArrayList named found that includes all applicable entries
    */
   public ArrayList<Entry> findType(String type){
       ArrayList<Entry> found = new ArrayList<Entry>();
       for(Entry ent : entries.values()) {
    	   if(ent.getType().equalsIgnoreCase(type)){
    		   found.add(ent);
    	   }
       }
       return found;
   }
   
   /**
    * 	This method takes and integer representing index and
    * retries the entry item at that index.
    * @param index index of the cell thats contents is to be returned
    * @return Entry object
    */
   public Entry get(String key){
	   return entries.get(key);
   }
   
   /**
    * this method is currently used in debugging code and for testing
    * it will print out all of the entries in the library
    */
   public void printAll(){
   		for(Entry ent : entries.values()) {
   			System.out.println(ent);
   		}
   }
   
   /**
    * This method test if an entry with specific key is in the collection
    * @param key
    * @return true if this key is contained in the list
    * otherwise, return false
    */
   public boolean contains(String key){
	   if(entries.containsKey(key) == true)
		   return true;
	   else
		   return false;		
   }
   
   /**
    * this method defines an action listener for whenever the contents are removed or
    * added in the list, the GUI will be notified
    */
   private void notifyListeners() {
		for(ListDataListener listener : listeners) {
			listener.contentsChanged(
				new ListDataEvent(this, 
						          ListDataEvent.CONTENTS_CHANGED,
						          0, entries.size()));
		}
   }
   
   /**
    * this method adds a ListDataListener to listeners
    */
	@Override
	public void addListDataListener(ListDataListener listener) {
		listeners.add(listener);
	}
	
	/**
	 * this method retrieves the entry object in an arraylist at place i
	 * @return an entry object
	 */
	@Override
	public Object getElementAt(int i) {
		 ArrayList<Entry> ents = new ArrayList<Entry>(entries.values());
		 Collections.sort(ents, sortOrder);
		return ents.get(i);
	}
	
	public void setComparator(Comparator<Entry> sortOrder){
		this.sortOrder = sortOrder;
		notifyListeners();
	}
	
	/**
	 * this method gets the size of an arrayList
	 * @return an integer
	 */
	@Override
	public int getSize() {
		return entries.size();
	}
	
	/**
	 * this method removes a ListdataListener from listeners
	 */
	@Override
	public void removeListDataListener(ListDataListener listener) {
		listeners.remove(listener);
	}
	
	/**
	 * this method enables the entry class to be iterable
	 */
	@Override
	public Iterator<Entry> iterator() {
		return entries.values().iterator();
	}

}
