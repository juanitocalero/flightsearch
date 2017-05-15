package jcalero.flightsearch.csv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

import org.junit.Test;

import jcalero.flightsearch.csv.CsvReader;
import jcalero.flightsearch.csv.InvalidCsvFormatException;

public class CsvReaderTest {
	
	@Test
	public void readMinimalCsvTest() throws IOException, InvalidCsvFormatException {
		String minimalCsv = "A,B,C\n1,2,3\n4,5,6";
		try (CsvReader reader = CsvReader.open(new StringReader(minimalCsv), ",")) {
		
			assertEquals("A", reader.header()[0]);
			assertEquals("B", reader.header()[1]);
			assertEquals("C", reader.header()[2]);
			
			reader.nextLine();
			
			assertEquals("1", reader.currentLine()[0]);
			assertEquals("2", reader.currentLine()[1]);
			assertEquals("3", reader.currentLine()[2]);
			
			reader.nextLine();
			
			assertEquals("4", reader.currentLine()[0]);
			assertEquals("5", reader.currentLine()[1]);
			assertEquals("6", reader.currentLine()[2]);
			
			assertFalse(reader.nextLine());
		}
		
	}
	
	@Test
	public void readExampleFileCsv() throws IOException, InvalidCsvFormatException {
		
		try (CsvReader reader = CsvReader.open(
				new InputStreamReader(this.getClass().getResourceAsStream("/airlines.csv")), ",")) {
			
			assertEquals("iataCode", reader.header()[0]);
			assertEquals("name", reader.header()[1]);
			assertEquals("infantPrice", reader.header()[2]);
			
			reader.nextLine();
			
			assertEquals("IB", reader.currentLine()[0]);
			assertEquals("Iberia", reader.currentLine()[1]);
			assertEquals("10", reader.currentLine()[2]);
			
			reader.nextLine();
			
			assertEquals("BA", reader.currentLine()[0]);
			assertEquals("British Airways", reader.currentLine()[1]);
			assertEquals("15", reader.currentLine()[2]);
			
		}
		
	}

}
