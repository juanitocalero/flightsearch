package jcalero.flightsearch.airports;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import jcalero.flightsearch.airports.Airport;
import jcalero.flightsearch.airports.AirportsCatalog;
import jcalero.flightsearch.airports.CsvAirportsCatalog;
import jcalero.flightsearch.catalog.InvalidCatalogException;

public class AirportsCatalogTest {
	
	@Test
	public void basicCatalogTest() throws InvalidCatalogException {
		
		AirportsCatalog catalog = new  CsvAirportsCatalog(this.getClass().getResource("/airports.csv"));
		
		assertTrue(catalog.findAirport("FRA").isPresent());
		assertTrue(catalog.findAirport("IST").isPresent());
		assertTrue(catalog.findAirport("AMS").isPresent());
		
		assertFalse(catalog.findAirport("TEST").isPresent());
		assertFalse(catalog.findAirport("NO").isPresent());
	}
	
	@Test(expected = InvalidCatalogException.class)
	public void bdaSourceCatalogTest() throws InvalidCatalogException {
		/*AirlinesCatalog catalog = */ new  CsvAirportsCatalog(this.getClass().getResource("/invalid-file.csv"));
	}
	
	@Test
	public void basicAirportInfoTest() throws InvalidCatalogException {
		
		AirportsCatalog catalog = new  CsvAirportsCatalog(this.getClass().getResource("/airports.csv"));
		
		Airport airport = catalog.findAirport("MAD").get();
		assertEquals("MAD", airport.code());
		assertEquals("Madrid", airport.name());
		
		
		airport = catalog.findAirport("BCN").get();
		assertEquals("BCN", airport.code());
		assertEquals("Barcelona", airport.name());
		
	}

}

