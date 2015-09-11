//import java.util.Collections;
//import java.util.Vector;
//import java.util.Iterator;

/*
 * Author: John Welsby, ANU ID: u5132271, March 2015
 * The Contact class represents a single record in the organiser system.
 * It contains firstname, lastname, and number fields
 * It includes getters, setters, compareTo(), equals(), toString(), and fromString() functions.
 */

public class Contact implements Comparable<Contact> {
	
	private String firstname;
	private String lastname;
	private String number;
	
	public void setFirstName(String firstname){
		this.firstname = sanitiseInput(firstname);
	}
	
	public void setLastName(String lastname){
		this.lastname = sanitiseInput(lastname);
	}
	
	public void setNumber(String number){
		this.number = sanitiseInput(number);
	}
	
	public String getFirstName() {
		return firstname;
	}
	
	public String getLastName(){
		return lastname;
	}
	
	public String getNumber() {
		return number;
	}
	
	//Obtain a textual representation of the object
	public String toString(){
		return firstname + ',' + lastname + ',' + number;		
	}
	
	//Restore an object from its textual representation
	public void fromString(String contactString){		
		clear();
		
		try	{
			String[] tokens = contactString.split(",");
		
			firstname = tokens[0];
			lastname = tokens[1];
			number = tokens[2];
		}
		catch(Exception e){
			System.out.println("Exception: " + e.toString() + " in Contact.fromString(\"" + contactString + "\")");
		}
	}
	
	public void clear(){
		firstname = "";
		lastname = "";
		number = "";
	}
	
	public Contact(String contactString){
		fromString(contactString);
	}
		
	
	/*public Contact(Contact rhs){
		name = rhs.name;
		number = rhs.number;
	}*/
	
	public Contact(){
		clear();
	}

	//Compare two Contacts by last name. If last names a equal, use first name
	@Override
	public int compareTo(Contact rhs) {
	
		int result = this.lastname.compareTo(rhs.lastname);
		
		if (result != 0){
			return result;
		}
		
		result = this.firstname.compareTo(rhs.firstname);
			
		return result;
	}
	
	public boolean equals(Contact rhs) {				
		
		return (this.firstname.equals(rhs.firstname) &&
				this.lastname.equals(rhs.lastname) &&
				this.number.equals(rhs.number));
					
	}
	
	private String sanitiseInput(String raw) {
		raw = raw.replace(",", " "); //Commas break CSV, so remove them
		raw = raw.replace("<", " "); //Angle brackets break XML, so remove them
		raw = raw.replace(">", " ");
		return raw;
	}

	/*
	public static void main(String[] args) {
		
	//	Vector<Contact> vec = new Vector<Contact>();
		Organiser organiser = new Organiser();
		
		
		
		System.out.println("Before Sorting: \n" + organiser.toString());
		
		organiser.sortContacts();
		
		System.out.println("After Sorting: \n" + organiser.toString());
		
		System.out.println("Executed");
		
	}
	*/
	
}
