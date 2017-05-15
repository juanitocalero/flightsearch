package jcalero.flightsearch.airports;

import java.util.Optional;

public interface AirportsCatalog {
	
	Optional<Airport> findAirport(String airportCode);
	
	Iterable<Airport> allAirports(); 

}
