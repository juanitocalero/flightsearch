package jcalero.flightsearch.flights;

import java.util.List;

public interface FlightsData {
	
	List<Flight> findFlights(String origAirportCode, String destAirportCode);
	

}
