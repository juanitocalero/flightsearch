package jcalero.flightsearch.airlines;

import java.util.Optional;

public interface AirlinesCatalog {
	
	Optional<Airline> findAirline(String airlineCode);
	
	Iterable<Airline> allAirlines(); 

}
