package jcalero.flightsearch.csv;

@SuppressWarnings("serial")
public class InvalidCsvFormatException extends Exception {
	
	public InvalidCsvFormatException(String message) {
		super(message);
	}
	
	public InvalidCsvFormatException(String message, Exception cause) {
		super(message, cause);
	}
}
