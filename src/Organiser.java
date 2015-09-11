import java.util.Collections;
import java.util.Vector;
import java.util.Iterator;

/*
 * Author: John Welsby, ANU ID: u5132271, March 2015
 * Organiser represents a list of Contacts.
 * It provides safe functions for adding, replacing, deleting, getting, and sorting
 * Also provides size(), clear(), toString() and fromString()
 */
public class Organiser {

	private Vector<Contact> contacts = new Vector<Contact>();
	
	public void addContact(Contact contact) {
		contacts.add(contact);
	}
	
	public void replaceContact(int index, Contact contact){
		try {
			contacts.set(index, contact);
		} catch (Exception e) {
			System.out.println("Exception : " + e.toString() + " in Organiser.replaceContact");
		}
	}
	
	public void deleteContact(int index){
		try {
			contacts.remove(index);
		} catch (Exception e) {
			System.out.println("Exception: " + e.toString() + " in Organiser.deleteContact");
		}
	}
	
	public Contact getContact(int index){
		try {
			return contacts.get(index);
		} catch (Exception e) {
			System.out.println("Exception: " + e.toString() + " in Organiser.getContact");
		}
		
		return null;
	}
	
	void sortContacts(){
		Collections.sort(contacts);
	}
	
	void clear() {
		contacts.clear();
	}
	
	int size() {
		return contacts.size();
	}
	
	//Obtain a CSV textual representation of the list
	public String toString() {
		String output = "";
	
		Iterator<Contact> it = contacts.iterator();
		
		while(it.hasNext()){
			output += it.next().toString() + "\n";
		}
		
		return output;
	}
	
	//Restore a list from its CSV textual representation
	public void fromString(String organiserString){
		clear();
		
		if(organiserString == null || organiserString == "")
			return;

		try{
			String[] tokens = organiserString.split("\n");
			
			for (String contactString : tokens){
				//if (contactString != "")
					addContact(new Contact(contactString));
			}
		}
		catch(Exception e){
			System.out.println("Exception: " + e.toString() + " in Organiser.fromString(\"" + organiserString + "\")");
		}
	}
	
	//Obtain an XML textual representation of the list
	public String toXmlString() {
		String xml = OrganiserXmlBuilder.organiserToXml(this);
		return xml;
	}
	
	//Restore a list from its XML textual representation
	public void fromXmlString(String xml) {
		OrganiserXmlBuilder.loadOrganiserFromXml(this, xml);
	}
	
	public Organiser(String organiserString){
		fromString(organiserString);
	}
	
	public Organiser(){
		
	}
	
	/*
	public static void main(String[] args) {
		System.out.println("Organiser Main");
		
		Organiser organiser = new Organiser();
		
		//organiser.addContact(new Contact("Helen Welsby,0433123456"));
		//organiser.addContact(new Contact());
		
				
		//organiser.addContact(contact1);
		
		//contact1.setName("John");
		//System.out.println(contact1.getName());
		
		
		Contact contact1 = new Contact("John,Welsby,0421834961");
		Contact contact2 = new Contact("Barry,Welsby,0122222233");
		Contact contact3 = new Contact("James,Welsby,0433336666");
		
		organiser.addContact(contact1);
		organiser.addContact(contact2);
		organiser.addContact(contact3);
		
		String firstOutput = organiser.toString();
		System.out.println("Unsorted Output: \n" + firstOutput + "\n");
		
		//organiser.fromString(firstOutput);
		organiser.sortContacts();
		
		
		String secondOutput = organiser.toString();
		System.out.println("Sorted Output: \n" + secondOutput + "\n");
		
		System.out.println("Size: " + organiser.size());
	}
	*/
}
