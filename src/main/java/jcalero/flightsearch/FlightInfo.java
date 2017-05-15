package jcalero.flightsearch;

import java.math.BigDecimal;

import jcalero.flightsearch.flights.Flight;

public class FlightInfo {
	private Flight flight;
	private BigDecimal finalPrice;
	
	public FlightInfo(Flight flight, BigDecimal finalPrice) {
		super();
		this.flight = flight;
		this.finalPrice = finalPrice;
	}

	public Flight flight() {
		return flight;
	}

	public BigDecimal finalPrice() {
		return finalPrice;
	}
	
	
	
	

}
