import static org.junit.Assert.*;

import org.junit.Test;

// Author: John Welsby, ANU ID: u5132271, March 2015
public class DatabaseTest {

	//Simple read-write test
	@Test
	public void test() {
		String writeData = "The quick brown fox jumps over the lazy dog\n";
		String file = "Test.txt";
		
		Database.writeString(file, writeData);
		
		String readData = Database.readString(file);
		
		assertTrue(writeData.equals(readData));
	}

}