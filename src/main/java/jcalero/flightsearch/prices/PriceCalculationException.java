package jcalero.flightsearch.prices;

@SuppressWarnings("serial")
public class PriceCalculationException extends Exception {
	
	public PriceCalculationException(String message) {
		super(message);
	}
	
	public PriceCalculationException(String message, Exception cause) {
		super(message, cause);
	}

}
