import java.io.*;

/*
 * Author: John Welsby, ANU ID: u5132271, March 2015
 * Database is a facade for reading and writing String objects to file
 */
public class Database {
	
	//private String filename;
	
	/*
	public Database(String filename){
		this.filename = filename;
	}
	 */
	
	//Writes the content of output to filename. Overwrites filename if it exists; creates it if it doesn't
	public static void writeString(String filename, String output){	
		
		try {
			File file = new File(filename);
			
			if(!file.exists())
				file.createNewFile();
						
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			
			writer.write(output);		
			writer.close();		
			
		} catch (IOException e) {
		
			System.out.println("Exception: " + e.toString() + " in Database.writeString(\"" + output + "\")");
			//e.printStackTrace();
		}		
				
	}
	
	//Reads contents of filename. Appends trailing newline if there wasn't already one
	public static String readString(String filename){
		
		String output = "";
	
		try{
			BufferedReader reader = new BufferedReader(new FileReader(filename));
						
			String line = reader.readLine();		
			
			while(line != null){
				output += line + "\n";
				line = reader.readLine();
			}				
			
			reader.close();
			
		}
		catch(IOException e){
			System.out.println("Exception: " + e.toString() + " in Database.readString()");
			//return null;
		}
		
		
		return output;
	}
	
	/*
	public static void main(String[] args) {
		String writeData = "This is a string\nOver\nEven\nMore\nLines than before\n";
		String readData = "";
			
		
		Database.writeString("output.txt", writeData);
		readData = Database.readString("output.txt");
		
		//System.out.println("Data written");
		System.out.println(readData);
		
	}
	*/
}
