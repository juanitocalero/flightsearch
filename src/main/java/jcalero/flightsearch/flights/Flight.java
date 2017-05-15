package jcalero.flightsearch.flights;

import java.math.BigDecimal;

public class Flight {
	private String code;
	private String origin;
	private String destination;
	private BigDecimal basePrice;
	
	public Flight(String code, String origin, String destination, BigDecimal basePrice) {
		this.code = code;
		this.origin = origin;
		this.destination = destination;
		this.basePrice = basePrice;
	}

	public String code() {
		return code;
	}

	public String origin() {
		return origin;
	}

	public String destination() {
		return destination;
	}

	public BigDecimal basePrice() {
		return basePrice;
	}
	
	public String getAirlineCode() {
		return code.substring(0, 2);
	}
	
	/**
	 * Returns true if this flight flies from origin to destination passed 
	 */
	public boolean isRoute(String origin, String destination) {
		return origin.equals(this.origin) && destination.equals(this.destination);
	}
	

}
