import javax.swing.DefaultListCellRenderer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.*;
//import java.util.Vector;


/*
 * Author: John Welsby, ANU ID: u5132271, March 2015
 * This class is no longer in use for the assignment
 * It contains experimentation and lab work
 * It is left here for reference
 */

public class OrganiserGUI implements Runnable, ActionListener, ListSelectionListener {
	
	private static final String SORTCOMMAND = "sortcommand";
	private static final String ADDCOMMAND = "addcommand";
	//private static final String DETAILSCOMMAND = "detailscommand";
	private static final String EDITCOMMAND = "editcommand";
	private static final String DELETECOMMAND = "deletecommand";
	
	
	JFrame jframe;
	String[][] fields;
	Organiser organiser = new Organiser();
	int contactIndex = 0;
	//JTextField textField;
	//Vector<Vector<String>> fields;
	//String[][] fields;
	//GridLayout lm;
	private JTextField firstnamefield = new JTextField(10);
	private JTextField lastnamefield = new JTextField(10);
	private JTextField numberfield = new JTextField(10);
	
	private JList contactList = new JList();
	
	public void setFields(String[][] fields){
		this.fields = fields;		
	}
	
	public OrganiserGUI(){
		SwingUtilities.invokeLater(this);
		
		organiser.addContact(new Contact("Toby,Ziegler,0433666777"));
		organiser.addContact(new Contact("John,Welsby,0421834961"));
		organiser.addContact(new Contact("Toby,Welsby,044111222"));
		organiser.addContact(new Contact("James,Welsby,0421333444"));
		organiser.addContact(new Contact("Kate,Donaldson,0432777888"));
		organiser.addContact(new Contact("Margo,Donaldson,0422555666"));
		
		//Vector<String> temp = new Vector<String>();
		
		//fields = new Vector<Vector<String>>();
	}
	
	public static void main(String[] args){
		OrganiserGUI gui = new OrganiserGUI();
		
		/*
		String[] s1 = {"This", "is", "an", "array", "of", "strings"};
		String[] s2 = {"So", "is", "this", "an", "array", "also"};
		String[] s3 = {"More", "strings", "to", "add", "to", "matrix"};
		String[] s4 = {"John", "is", "awesome", "at", "writing", "Java"};
		
		String[][] data = {s1, s2, s3, s4};
		
		gui.setFields(data);
		*/
	}
	
	public JPanel contactsPanel(){
		JPanel panel = new JPanel();		
		LayoutManager lm = new GridLayout(organiser.size(), 2);
			 	
		//panel.setLayout(lm);
		
		String elements[] = new String[organiser.size()];
		
		for(int i = 0; i < organiser.size(); i++){
			
			Contact contact = organiser.getContact(i);
			
			elements[i] = contact.getFirstName() + " " + contact.getLastName();
			
			//panel.add(new JLabel(contact.getFirstName()));
			//panel.add(new JLabel(contact.getLastName()));
			//panel.add(new JLabel(contact.getNumber()));
		}
		
		contactList = new JList(elements);
		
		contactList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		contactList.setSelectedIndex(contactIndex);
		
				
		//list.setCellRenderer(new DefaultListCellRenderer());
		
		contactList.addListSelectionListener(this);
		
		JScrollPane pane = new JScrollPane(contactList);
		
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		panel.add(pane);
		
		
		
		//panel.add(new JScrollPane(list));
		/*JButton sortbutton = new JButton();
		
		sortbutton.addActionListener(this);
		sortbutton.setActionCommand(SORTCOMMAND);
		
		panel.add(sortbutton);*/
		
		return panel;
	}
	
	public JPanel detailsPanel(Contact contact){
		JPanel panel = new JPanel();
		
		LayoutManager lm = new GridLayout(2,3);
		
		panel.setLayout(lm);
		
		
		//JTextField firstnamefield = new JTextField(10);
		firstnamefield.setText(contact.getFirstName());
		
		//JTextField lastnamefield = new JTextField(10);
		lastnamefield.setText(contact.getLastName());
		
		//JTextField numberfield = new JTextField(10);
		numberfield.setText(contact.getNumber());
		
		panel.add(new JLabel("First Name"));
		panel.add(new JLabel("Last Name"));
		panel.add(new JLabel("Phone No"));
		
		panel.add(firstnamefield);
		panel.add(lastnamefield);
		panel.add(numberfield);
		
		return panel;
		
	}
	
	public void draw(){
		//fields = strings;
		Container pane = jframe.getContentPane();
		
		
		pane.removeAll();
		
		pane.setLayout(new FlowLayout());
		
		pane.add(contactsPanel());
		
		Contact displayContact = null;
		
		if (organiser.size() > 0) {
			displayContact = organiser.getContact(contactIndex);
		} else {
			displayContact = new Contact();
		}
		
		pane.add(detailsPanel(displayContact));
				
		
		JButton sortbutton = new JButton("Sort");
		
		sortbutton.addActionListener(this);
		sortbutton.setActionCommand(SORTCOMMAND);
		//sortbutton.setActionCommand(ADDCOMMAND);
		
		JButton addbutton = new JButton("Add");
		addbutton.addActionListener(this);
		addbutton.setActionCommand(ADDCOMMAND);
		
		JButton detailsbutton = new JButton("Edit");
		detailsbutton.addActionListener(this);
		detailsbutton.setActionCommand(EDITCOMMAND);
		
		JButton deletebutton = new JButton("Delete");
		deletebutton.addActionListener(this);
		deletebutton.setActionCommand(DELETECOMMAND);
		
		pane.add(sortbutton);
		pane.add(addbutton);
		pane.add(detailsbutton);
		pane.add(deletebutton);
		
		/*GridLayout lm = new GridLayout(fields.length, fields[0].length);		
		
		pane.setLayout(lm);*/
		
		/*for (Vector<String> row : fields){
			for (String cellText : row){
				pane.add(new JLabel(cellText));
			}
		}*/
		
		/*
		for (int row = 0; row < fields.length; row++){
			for (int col = 0; col < fields[0].length; col++){
				//pane.add(new JLabel(strings[row][col]));
				pane.add(new JLabel("<html><font size=\"30\">" + fields[row][col] + "</font></html>"));
			}
		}
		*/
		
		jframe.setVisible(true);
		jframe.pack();
		
		//FlowLayout lm = new FlowLayout();		
		//GridLayout lm = new GridLayout(4,1);
		
	}
	
	public Contact buildContact(){
		Contact newContact = new Contact();
		
		newContact.setFirstName(firstnamefield.getText());
		newContact.setLastName(lastnamefield.getText());
		newContact.setNumber(numberfield.getText());
		
		return newContact;
	}
	
	public void run(){
		jframe = new JFrame("Contact Organiser");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		draw();
		
		/*JLabel label = new JLabel("<html><font size=\"20\">OrganiserGUI</font></html>");
		label.setVerticalTextPosition(JLabel.CENTER);
		label.setHorizontalAlignment(JLabel.CENTER);*/
		
		/*textField = new JTextField(50);
		
		JButton button = new JButton("Click Me");
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				//System.out.println("Button Clicked");
				System.out.println(textField.getText());
			}
		});*/
		
		
			
		
			
		/*
		jframe.getContentPane().add(label);
		jframe.getContentPane().add(button);
		jframe.getContentPane().add(textField);
		jframe.getContentPane().add(new JLabel("Filler"));
		jframe.setVisible(true);
		jframe.pack();
		*/
	}
	
	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().equals(SORTCOMMAND)) {
			System.out.println(SORTCOMMAND);
			organiser.sortContacts();
			draw();
		} else if (ae.getActionCommand().equals(ADDCOMMAND)){
			System.out.println(ADDCOMMAND);			
			Contact newContact = buildContact();			
			organiser.addContact(newContact);
			draw();
		} else if (ae.getActionCommand().equals(EDITCOMMAND)){
			System.out.println(EDITCOMMAND);
			Contact editContact = buildContact();			
			organiser.replaceContact(contactIndex, editContact);			
			draw();
		} else if (ae.getActionCommand().equals(DELETECOMMAND)){
			organiser.deleteContact(contactIndex);
			
			if (contactIndex >= organiser.size())
					contactIndex = organiser.size() - 1;
			
			if (contactIndex < 0)
					contactIndex = 0;
				
			draw();
		}
		//System.out.println("actionPerformed");
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		//if(!e.getValueIsAdjusting()){
			contactIndex = contactList.getSelectedIndex();
			System.out.println("contactIndex: " + contactIndex);
		//}
		draw();		
	}

}
