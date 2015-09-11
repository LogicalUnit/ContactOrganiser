import javax.swing.table.AbstractTableModel;

/*
 * Author: John Welsby, ANU ID: u5132271, March 2015
 * OrganiserTableModel is the logical interface between a JTable and an Organiser object
 * It overrides the methods in AbstractTableModel
 */
public class OrganiserTableModel extends AbstractTableModel {
	
	public static final int FIRSTNAME_INDEX = 0;
	public static final int LASTNAME_INDEX = 1;
	public static final int NUMBER_INDEX = 2;
	
	private static final String[] columnNames = {"First Name", "Last Name", "Number" };
	
	private Organiser organiser = new Organiser();
	
	
	public OrganiserTableModel(){
		//organiser.addContact(new Contact("John,Welsby,0421834961"));
		//organiser.addContact(new Contact("James,Welsby,0411222333"));
		//organiser.addContact(new Contact("Margo,Donaldsom,0423777888"));
	}
	
	//All cells can be edited
	public boolean isCellEditable(int row, int col){
		return true;
	}
	
	public String getColumnName(int col){
		return columnNames[col];
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return organiser.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
	
		Contact contact = organiser.getContact(row);
		
		switch(col){
			case FIRSTNAME_INDEX:
				return contact.getFirstName();
			case LASTNAME_INDEX:
				return contact.getLastName();
			case NUMBER_INDEX:
				return contact.getNumber();								
		}
		
		return new Object();
	}
	
	public void setValueAt(Object value, int row, int col){
		
		Contact contact = organiser.getContact(row); 
		String text = (String)value;
		
		switch(col){
			case FIRSTNAME_INDEX:
				contact.setFirstName(text);
				break;
			case LASTNAME_INDEX:
				contact.setLastName(text);
				break;
			case NUMBER_INDEX:
				contact.setNumber(text);
				break;
		}
		
		fireTableCellUpdated(row, col);
	}
	
	public void sortContacts(){
		organiser.sortContacts();
		fireTableDataChanged();
	}
	
	public void addEmptyContact(){
		organiser.addContact(new Contact());
		fireTableDataChanged();
	}
	
	public void deleteRow(int row){
		organiser.deleteContact(row);
		fireTableDataChanged();
	}

	public String getSaveString(){
		return organiser.toString();
	}
	
	public void loadFromString(String data){
		organiser.fromString(data);
		fireTableDataChanged();
	}
	
	public String getXmlSaveString() {
		return organiser.toXmlString();
	}
	
	public void loadFromXmlString(String xml) {
		organiser.fromXmlString(xml);
		fireTableDataChanged();
	}
	
	/*
	public void setOrganiser(Organiser organiser){
		this.organiser = organiser;
		fireTableDataChanged();
	}
	
	public Organiser getOrganiser() {
		return organiser;
	}
	*/

}
