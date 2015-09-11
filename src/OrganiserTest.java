import static org.junit.Assert.*;

import org.junit.Test;

// Author: John Welsby, ANU ID: u5132271, March 2015
public class OrganiserTest {
	
	Organiser organiser = new Organiser();
	
	void setup() {
		
		organiser.clear();
		
		organiser.addContact(new Contact("Toby,Ziegler,0433666777"));
		organiser.addContact(new Contact("John,Welsby,0421834961"));
		organiser.addContact(new Contact("Toby,Welsby,044111222"));
		organiser.addContact(new Contact("James,Welsby,0421333444"));
		organiser.addContact(new Contact("Kate,Donaldson,0432777888"));
		organiser.addContact(new Contact("Margo,Donaldson,0422555666"));

	}

	//Simple lookup
	@Test
	public void test1() {
		
		setup();
		
		Contact contact = new Contact("Toby,Ziegler,0433666777");
		
		assertTrue(contact.equals(organiser.getContact(0)));
	}
	
	//Simple delete
	@Test
	public void test2() {
		
		setup();
		
		Contact contact = new Contact("John,Welsby,0421834961");
		
		organiser.deleteContact(1); 
		
		assertFalse(contact.equals(organiser.getContact(1)));
	}
	
	//Size
	@Test
	public void test3() {
		setup();
		
		int size1 = organiser.size();
		
		organiser.addContact(new Contact());
		
		assertFalse(size1 == organiser.size());
		
	}
	
	//Sorting
	@Test
	public void test4() {
		setup();
		
		organiser.addContact(new Contact("Aaron,Aardvark,0234777555"));
		
		organiser.sortContacts();
		
		for (int i = 1; i < organiser.size(); i++){
			Contact contact1 = organiser.getContact(i-1);
			Contact contact2 = organiser.getContact(i); 
			
			if(contact1.compareTo(contact2) > 0)
				fail("Contacts not sorted");
					
		}
		/*
		String oldString = organiser.toString();
		
		organiser.sortContacts();
		
		String newString = organiser.toString();
		
		assertTrue(oldString != newString);
		*/
	}

}
