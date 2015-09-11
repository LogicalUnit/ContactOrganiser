import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/*
 * Author: John Welsby, ANU ID: u5132271, March 2015
 * OrganiserXmlBuilder is a facade for translation between an Organiser object and an XML string
 */
public class OrganiserXmlBuilder {
	
	private static final String CONTACTLISTFIELD = "contactlist";
	private static final String CONTACTFIELD = "contact";
	private static final String FIRSTNAMEFIELD = "firstname";
	private static final String LASTNAMEFIELD = "lastname";
	private static final String NUMBERFIELD = "number";
	
	//Obtain a textual XML representation of the Organiser object
	public static String organiserToXml(Organiser organiser){
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		
		try {
			db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();
			
			//Start with a contact list element
			Element contactList = doc.createElement(CONTACTLISTFIELD);
					
			for(int i = 0; i < organiser.size(); i++) {				
				
				Contact currentObject = organiser.getContact(i);
				
				//For every Contact in the organiser, create a contact node
				Element contact = doc.createElement(CONTACTFIELD);
			
				//Add firstname to contact node
				Element firstname = doc.createElement(FIRSTNAMEFIELD);
				firstname.appendChild(doc.createTextNode(currentObject.getFirstName()));
				contact.appendChild(firstname);
				
				//Add lastname to contact node
				Element lastname = doc.createElement(LASTNAMEFIELD);
				lastname.appendChild(doc.createTextNode(currentObject.getLastName()));
				contact.appendChild(lastname);
				
				//Add number to contact node
				Element number = doc.createElement(NUMBERFIELD);
				number.appendChild(doc.createTextNode(currentObject.getNumber()));
				contact.appendChild(number);
				
				//Add the contact to the contact list
				contactList.appendChild(contact);				
			}
			
			//Add the contact list to the root of the XML
			doc.appendChild(contactList);
			
			//Do some trickery to convert from XML document to a String
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			DOMSource source = new DOMSource(doc);
			StringWriter outWriter = new StringWriter();
			StreamResult result = new StreamResult(outWriter);
			transformer.transform(source, result);
			
			StringBuffer sb = outWriter.getBuffer();
			return sb.toString();
			
			
		} catch (Exception e) {
			System.out.println("Exception: " + e.toString() + " in OrganiserXmlBuilder.organiserToXml");
		}
		
		
		return "";
	}
	
	//Restore an organiser from a textual XML string
	public static void loadOrganiserFromXml(Organiser organiser, String xml) {
	
		//Start by emptying the organiser
		organiser.clear();
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		
		try {
			db = dbf.newDocumentBuilder();
			InputSource input = new InputSource(new StringReader(xml));			
			Document doc = db.parse(input);
			
			//Obtain a list of contact nodes
			NodeList contacts = doc.getElementsByTagName(CONTACTFIELD);
			
			for(int i = 0; i < contacts.getLength(); i++){
				
				//For each node in the contact list, create a new object
				Contact newObject = new Contact();
				Node currentContact = contacts.item(i);
				NodeList attributes = currentContact.getChildNodes();
				
				for(int j = 0; j < attributes.getLength(); j++) {
					
					//Iterate over the attributes of each node, setting them as we go
					Node n = attributes.item(j);
					
					if(n.getNodeName().equals(FIRSTNAMEFIELD)) {
						newObject.setFirstName(n.getTextContent());
					} else if (n.getNodeName().equals(LASTNAMEFIELD)) {
						newObject.setLastName(n.getTextContent());
					} else if (n.getNodeName().equals(NUMBERFIELD)) {
						newObject.setNumber(n.getTextContent());
					}
				}
				
				//Add each new Contact object to the organiser
				organiser.addContact(newObject);								
			}
			
		} catch (Exception e) {			
			System.out.println("Exception :" + e.toString() + " in OrganiserXmlBuilder.loadOrganiserFromXml");
		}
		
	}
	
}