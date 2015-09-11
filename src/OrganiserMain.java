import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

/*
 * Author: John Welsby, ANU ID: u5132271, March 2015
 * OrganiserMain is the interface to the finished assignment
 * It connects the OrganiserGUI to the various commands,
 * and initialises Swing
 */
public class OrganiserMain implements ActionListener, Runnable {
	
	private static final String ADDCOMMAND = "addcommand";
	private static final String SORTCOMMAND = "sortcommand";
	private static final String DELETECOMMAND = "deletecommand";
	private static final String SAVECOMMAND = "savecommand";
	private static final String LOADCOMMAND = "loadcommand";
	private static final String SAVEXMLCOMMAND = "savexmlcommand";
	private static final String LOADXMLCOMMAND = "loadxmlcommand";
	
	private static final String DEFAULT_SAVE = "Organiser.csv";
	
	private JFrame mainFrame;
	private OrganiserGUI2 gui;
	private JFileChooser fileChooser;
	
	//Simple interface for creating command buttons
	private JButton commandButton(String text, String command){
		JButton button = new JButton(text);
		button.addActionListener(this);
		button.setActionCommand(command);
		return button;
	}
	
	//Simple interface for creating menu items
	private JMenuItem commandMenuItem(String text, String command){
		JMenuItem item = new JMenuItem(text);
		item.addActionListener(this);
		item.setActionCommand(command);
		return item;		
	}
	
	public OrganiserMain() {
		
		SwingUtilities.invokeLater(this);
				
	}

	public static void main(String[] args) {

		OrganiserMain main = new OrganiserMain();

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getActionCommand().equals(ADDCOMMAND)){
			gui.addCommand();	
		} else if (ae.getActionCommand().equals(SORTCOMMAND)){
			gui.sortCommand();
		} else if (ae.getActionCommand().equals(DELETECOMMAND)){
			gui.deleteCommand();			
		} else if (ae.getActionCommand().equals(SAVECOMMAND)){
			saveCommand();
		} else if (ae.getActionCommand().equals(LOADCOMMAND)){
			loadCommand();
		} else if (ae.getActionCommand().equals(SAVEXMLCOMMAND)) {
			saveXmlCommand();
		} else if (ae.getActionCommand().equals(LOADXMLCOMMAND)) {
			loadXmlCommand();
		}
		
	}
	
	private void setup(){
		mainFrame = new JFrame("Contacts Organiser");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		gui = new OrganiserGUI2();
		
		fileChooser = new JFileChooser();
		
		mainFrame.setLayout(new BorderLayout());
		mainFrame.add(gui, BorderLayout.CENTER);
		
		/*Organiser organiser = new Organiser();
		
		
		organiser.addContact(new Contact("John,Welsby,0421834961"));
		organiser.addContact(new Contact("James,Welsby,0411222333"));
		organiser.addContact(new Contact("Margo,Donaldsom,0423777888"));
		
		
		gui.setOrganiser(organiser);*/
	
		
		//Set up the command buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(commandButton("Add", ADDCOMMAND));
		buttonPanel.add(commandButton("Sort", SORTCOMMAND));
		buttonPanel.add(commandButton("Delete", DELETECOMMAND));
		
		mainFrame.add(buttonPanel, BorderLayout.PAGE_END);
		
		//Set up the File menu
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		
		fileMenu.add(commandMenuItem("Save CSV", SAVECOMMAND));
		fileMenu.add(commandMenuItem("Load CSV", LOADCOMMAND));
		fileMenu.add(commandMenuItem("Save XML", SAVEXMLCOMMAND));
		fileMenu.add(commandMenuItem("Load XML", LOADXMLCOMMAND));
		
		menuBar.add(fileMenu);		
		mainFrame.setJMenuBar(menuBar);
		
	
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

	//Display modal file chooser for saving to CSV
	public void saveCommand(){
		int result = fileChooser.showSaveDialog(mainFrame);
		
		if (result == JFileChooser.APPROVE_OPTION){
			String fileName = fileChooser.getSelectedFile().getName();
			save(fileName);
		}
	}
	
	//Display modal file chooser for saving to XML
	public void saveXmlCommand() {
		int result = fileChooser.showSaveDialog(mainFrame);
		
		if (result == JFileChooser.APPROVE_OPTION) {
			String fileName = fileChooser.getSelectedFile().getName();
			saveXml(fileName);
		}
	}
	
	//Save CSV representation to fileName
	private void save(String fileName){		
		String saveData = gui.getSaveString();
		Database.writeString(fileName, saveData);		
	}
	
	//Save XML representation to fileName
	private void saveXml(String fileName) {
		String saveData = gui.getXmlSaveString();
		Database.writeString(fileName, saveData);
	}
	
	//Display modal file chooser for loading CSV
	public void loadCommand(){
		int result = fileChooser.showOpenDialog(mainFrame);
		
		if (result == JFileChooser.APPROVE_OPTION){
			String fileName = fileChooser.getSelectedFile().getName();
			load(fileName);
		}
			
	}
	
	//Display modal file chooser for loading XML
	public void loadXmlCommand() {
		int result = fileChooser.showOpenDialog(mainFrame);
		
		if (result == JFileChooser.APPROVE_OPTION) {
			String fileName = fileChooser.getSelectedFile().getName();
			loadXml(fileName);
		}
	}
	
	//Load CSV representation from fileName
	private void load(String fileName){		
		String loadData = Database.readString(fileName);
		gui.loadFromString(loadData);
	}
	
	//Load XML representation from fileName
	private void loadXml(String fileName) {
		String loadData = Database.readString(fileName);
		gui.loadFromXmlString(loadData);
	}

	@Override
	public void run() {
		setup();
		load(DEFAULT_SAVE); //Attempt to load default save file when application is run
	}
	

}
