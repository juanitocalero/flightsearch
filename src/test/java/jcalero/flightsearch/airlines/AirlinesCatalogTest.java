package jcalero.flightsearch.airlines;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

import jcalero.flightsearch.airlines.Airline;
import jcalero.flightsearch.airlines.AirlinesCatalog;
import jcalero.flightsearch.airlines.CsvAirlinesCatalog;
import jcalero.flightsearch.catalog.InvalidCatalogException;

public class AirlinesCatalogTest {
	
	@Test
	public void basicCatalogTest() throws InvalidCatalogException {
		
		AirlinesCatalog catalog = new  CsvAirlinesCatalog(this.getClass().getResource("/airlines.csv"));
		
		assertTrue(catalog.findAirline("IB").isPresent());
		assertTrue(catalog.findAirline("BA").isPresent());
		assertTrue(catalog.findAirline("U2").isPresent());
		
		assertFalse(catalog.findAirline("TEST").isPresent());
		assertFalse(catalog.findAirline("NO").isPresent());
	}
	
	@Test(expected = InvalidCatalogException.class)
	public void bdaSourceCatalogTest() throws InvalidCatalogException {
		/*AirlinesCatalog catalog = */ new  CsvAirlinesCatalog(this.getClass().getResource("/invalid-file.csv"));
	}
	
	@Test
	public void basicAirlineInfoTest() throws InvalidCatalogException {
		
		AirlinesCatalog catalog = new  CsvAirlinesCatalog(this.getClass().getResource("/airlines.csv"));
		
		Airline airline = catalog.findAirline("IB").get();
		assertEquals("IB", airline.code());
		assertEquals("Iberia", airline.name());
		assertEquals(BigDecimal.valueOf(10), airline.infantPrice());
		
		airline = catalog.findAirline("BA").get();
		assertEquals("BA", airline.code());
		assertEquals("British Airways", airline.name());
		assertEquals(BigDecimal.valueOf(15), airline.infantPrice());
	}

}

