package jcalero.flightsearch.catalog;

@SuppressWarnings("serial")
public class InvalidCatalogException extends Exception {
	
	public InvalidCatalogException(String message) {
		super(message);
	}
	
	public InvalidCatalogException(String message, Exception cause) {
		super(message, cause);
	}
}
