import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/*
 * Author: John Welsby, ANU ID: u5132271, March 2015
 * OrganiserGUI2 is the physical interface to the Organiser model
 * It provides the commands Add, Sort, and Delete
 * Includes a scroll bar
 */
public class OrganiserGUI2 extends JPanel { //implements ActionListener {

	//private static final String SORTCOMMAND = "sortcommand";
	//private static final String ADDCOMMAND = "addcommand";
	//private static final String DELETECOMMAND = "deletecommand";
	
	//private JFrame jframe;
	private JTable table;
	private JScrollPane scroller;
	private OrganiserTableModel tableModel;
	
	/*
	public static void main(String[] args) {
		
		JFrame jframe = new JFrame("Organiser GUI 2");			
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jframe.getContentPane().add(new OrganiserGUI2());
		jframe.pack();
		jframe.setVisible(true);

	}
	*/
	
	public OrganiserGUI2(){
		initComponents();
	}
	
	public void initComponents(){
		tableModel = new OrganiserTableModel();
		//tableModel.addTableModelListener(new OrganiserGUI2.OrganiserTableModelListener());
		table = new JTable();
		table.setModel(tableModel);
		
		scroller = new JScrollPane(table);
		
		setLayout(new BorderLayout());
		add(scroller, BorderLayout.CENTER);
		
		/*
		JButton sortbutton = new JButton("Sort");
		sortbutton.addActionListener(this);
		sortbutton.setActionCommand(SORTCOMMAND);
		
		JButton addcontact = new JButton("Add");
		addcontact.addActionListener(this);
		addcontact.setActionCommand(ADDCOMMAND);
		
		JButton deletecontact = new JButton("Delete");
		deletecontact.addActionListener(this);
		deletecontact.setActionCommand(DELETECOMMAND);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		
				
		buttonPanel.add(sortbutton);
		buttonPanel.add(addcontact);
		buttonPanel.add(deletecontact);
		
		add(buttonPanel, BorderLayout.PAGE_END);
		*/
	}
	
	public void sortCommand(){
		tableModel.sortContacts();
	}

	public void addCommand(){
		tableModel.addEmptyContact();
	}
	
	public void deleteCommand(){
		int selectedRow = table.getSelectedRow();
		
		if(selectedRow != -1){
			//If a row is selected, delete it
			tableModel.deleteRow(selectedRow);
			
			if (selectedRow < tableModel.getRowCount()){
				//Afterward, select the row that replaced it
				table.setRowSelectionInterval(selectedRow,  selectedRow);
			} else if (tableModel.getRowCount() > 0){
				//If we deleted the last row, select the previous row -- but only if the table isn't empty 
				table.setRowSelectionInterval(selectedRow - 1, selectedRow -1);
			}						
		}				
	}
	
	public String getSaveString(){
		return tableModel.getSaveString();
	}
	
	public void loadFromString(String data){
		tableModel.loadFromString(data);
	}
	
	public String getXmlSaveString() {
		return tableModel.getXmlSaveString();
	}
	
	public void loadFromXmlString(String xml) {
		tableModel.loadFromXmlString(xml);
	}

	/*
	public void setOrganiser(Organiser organiser){
		tableModel.setOrganiser(organiser);
	}
	
	public Organiser getOrganiser(){
		return  tableModel.getOrganiser();
	}
	*/

	/*
	public class OrganiserTableModelListener implements TableModelListener {

		@Override
		public void tableChanged(TableModelEvent evt) {
			//System.out.println("Blorg");
			if (evt.getType() == TableModelEvent.UPDATE){
				int row = evt.getFirstRow();
				int col = evt.getColumn();
				
				System.out.println("row: " + row + " col: " + col);
				table.setRowSelectionInterval(row,row);
				table.setColumnSelectionInterval(col + 1, col + 1);
			}
			
		}
		
	}
	*/

	/*
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getActionCommand().equals(SORTCOMMAND)){
			tableModel.sortContacts();
			System.out.println(SORTCOMMAND);		
		} else if (ae.getActionCommand().equals(ADDCOMMAND)){			
			tableModel.addEmptyContact();
			System.out.println(ADDCOMMAND);
		} else if (ae.getActionCommand().equals(DELETECOMMAND)){
			tableModel.deleteRow(table.getSelectedRow());
		}
		
	}
	*/
}
