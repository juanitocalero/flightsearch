package jcalero.flightsearch;

@SuppressWarnings("serial")
public class InvalidSearchTermsException extends Exception {
	
	public InvalidSearchTermsException(String message) {
		super(message);
	}
	
	public InvalidSearchTermsException(String message, Exception cause) {
		super(message, cause);
	}

}
