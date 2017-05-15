package jcalero.flightsearch.flights;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import jcalero.flightsearch.catalog.InvalidCatalogException;

public class FlightsDataTest {
	
	@Test
	public void basicDataTest() throws InvalidCatalogException {
		
		FlightsData flightData = new  CsvFlightsData(this.getClass().getResource("/flights.csv"));
		
		assertEquals(1, flightData.findFlights("CDG", "MAD").size());
		assertEquals(2, flightData.findFlights("LHR", "IST").size());
		assertEquals(0, flightData.findFlights("TEST", "TOST").size());
		
	}

}

