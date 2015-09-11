import static org.junit.Assert.*;

import org.junit.Test;

// Author: John Welsby, ANU ID: u5132271, March 2015
public class OrganiserXmlBuilderTest {

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
	
	@Test
	public void test() {
		setup();
		
		String xml = OrganiserXmlBuilder.organiserToXml(organiser);
		
		Database.writeString("Organiser.xml", xml);
	}
	
	@Test
	public void test2() {
		
		setup();
		
		String xml = Database.readString("Organiser.xml");
		
		OrganiserXmlBuilder.loadOrganiserFromXml(organiser, xml);
		
		System.out.println(organiser.toString());
	}

}
