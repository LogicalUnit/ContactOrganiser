import static org.junit.Assert.*;

import org.junit.Test;

// Author: John Welsby, ANU ID: u5132271, March 2015
public class ContactTest {

	//Basic fromString test
	@Test
	public void test1() {
		
		String constructString = "John,Welsby,0421834961";
		Contact contact = new Contact(constructString);
		
		assertEquals(contact.getFirstName(), "John");
		assertEquals(contact.getLastName(), "Welsby");
		assertEquals(contact.getNumber(), "0421834961");
	}
	
	//Basic fromString and toString
	@Test	
	public void test2() {
		
		String constructString = "James,Welsby,04123765";
		Contact contact = new Contact(constructString);
		
		assertEquals(constructString, contact.toString());
	}
	
	//fromString and toString object comparison
	@Test
	public void test3() {
		String constructString = "Margo,Donaldson,0442444555";
		
		Contact contact1 = new Contact(constructString);		
		Contact contact2 = new Contact(contact1.toString());
		
		assertTrue(contact1.equals(contact2));
	}

}
