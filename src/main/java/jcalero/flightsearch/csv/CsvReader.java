package jcalero.flightsearch.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Naive Csv reader implementation.
 * A production-ready implementation should take into account many more cases in split
 * (see http://stackoverflow.com/a/24950812)
 * @author j.calero
 *
 */
public class CsvReader implements AutoCloseable {
	
    private String splitBy = ",";
    private String[] header;
    BufferedReader reader;
    
    private String[] currentLine;
    
    public static CsvReader open(String path, String splitBy) throws IOException, InvalidCsvFormatException {

        BufferedReader reader = new BufferedReader(new FileReader(path));
        return new CsvReader(reader, splitBy);
    }
    
    public static CsvReader open(Reader origReader, String splitBy) throws IOException, InvalidCsvFormatException {

        BufferedReader reader = new BufferedReader(origReader);
        return new CsvReader(reader, splitBy);
    }
    
    
    protected CsvReader(BufferedReader reader, String splitBy) throws IOException, InvalidCsvFormatException {
    	this.reader = reader;
    	this.splitBy = splitBy;
    	
        String headerLine = reader.readLine();
        if (headerLine == null) {
        	throw new InvalidCsvFormatException("Missing header line");
        }
        header = headerLine.split(splitBy);
    }
    
    public boolean nextLine() throws IOException {
    	String line = reader.readLine();
        if (line == null) {
        	currentLine = null;
        	return false;
        }
        currentLine = line.split(splitBy);
        return true;
    	
    }
    
    public String[] header() {
    	return header;
    }
    
    public String[] currentLine() {
    	return currentLine;
    }
    
    public String currentValue(int fieldPosition) {
    	if (fieldPosition > header.length)
    		throw new IllegalArgumentException("Illegal field position");
    	return currentLine[fieldPosition];
    }
	

	@Override
	public void close()  {
		try {
			reader.close();
		} catch (IOException e) {
			// we can ignore this one
		}
		
	}

    

}
