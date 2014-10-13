package com.hzhang.bibtech.testing;

import static org.junit.Assert.*;

import org.junit.Test;

import com.hzhang.bibtech.entries.Online;
/**
* CSC232 - Spring 2013
* A class for testing the Online class.
* @author Hengshuo Zhang
*/
public class OnlineTestTest {

	@Test
	public void test(){
		/**
		 * make a new Online object and set each field a value
		 */
		Online testOnline = new Online();
		testOnline.setValue("key", "hzhan15");
		testOnline.setValue("author", "Hengshuo Zhang");
		testOnline.setValue("title", "awesome project");
		
		testOnline.setValue("url","http://www.apple.com"); // url is not null and it is valid, so even if 
														   // protocol, host and path are not null, it should not pass
														   // the value
		testOnline.setValue("protocol", "ftp");
		testOnline.setValue("host", "www.yahoo.com");
		testOnline.setValue("path", "goodjob");
		testOnline.setValue("day", "15");
		testOnline.setValue("month", "May");
		testOnline.setValue("year", "2013");
		testOnline.setValue("tag", "I Like It");
		
		/**
		 * Make another Online object by another constructor with parameters
		 * url is valid and even if it have protocol, host and path, those three field should not pass values.
		 */
		Online compareOnline = new Online("hzhan15", "awesome project", "Hengshuo Zhang",
				"http://www.apple.com", "ftp", "www.yahoo.com", "goodjob", "15", "May", "2013", "I Like It");
		
		assertEquals("hzhan15", compareOnline.getKey());
		assertEquals("Hengshuo Zhang", compareOnline.getAuthor());
		assertEquals("awesome project", compareOnline.getTitle());
		assertEquals("http://www.apple.com", compareOnline.getUrl());
		assertEquals(null, compareOnline.getProtocol());
		assertEquals(null, compareOnline.getHost());
		assertEquals(null, compareOnline.getPath());
		assertEquals("15", compareOnline.getDay());
		assertEquals("May", compareOnline.getMonth());
		assertEquals("I Like It", compareOnline.getTags());
		
		assertEquals("http://www.apple.com", testOnline.getUrl());
		assertEquals(null, testOnline.getProtocol());
		assertEquals(null, testOnline.getHost());
		assertEquals(null, testOnline.getPath());
		
		assertEquals(testOnline, compareOnline);
		
	}
	

}
