package jcalero.flightsearch;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import jcalero.flightsearch.SearchTerms.Passengers;
import jcalero.flightsearch.airlines.AirlinesCatalog;
import jcalero.flightsearch.airlines.CsvAirlinesCatalog;
import jcalero.flightsearch.airports.AirportsCatalog;
import jcalero.flightsearch.airports.CsvAirportsCatalog;
import jcalero.flightsearch.flights.CsvFlightsData;
import jcalero.flightsearch.flights.FlightsData;
import jcalero.flightsearch.prices.PriceCalculationException;

public class Stepdefs {
	
	SearchEngine searchEngine;
	SearchTerms searchTerms;
    
	@Given("^I use the flight search with the test data provided$")
    public void initSearchEngine() throws Throwable {
        FlightsData flightsData = new CsvFlightsData(this.getClass().getResource("/flights.csv"));
		AirportsCatalog airportsCatalog = new CsvAirportsCatalog(this.getClass().getResource("/airports.csv"));
		AirlinesCatalog airlinesCatalog = new CsvAirlinesCatalog(this.getClass().getResource("/airlines.csv"));
		
		searchEngine = new SearchEngine(airlinesCatalog, airportsCatalog, flightsData);
    }
	
	@When("^I search for (\\d+) adults?, (\\d+) child(?:ren)?, (\\d+) infants?, (\\d+) days? to the departure date, flying (.+) -> (.+)$")
	public void setTerms(int numAdults, int numChilds, int numInfants, int daysToDeparture, 
			String origin, String destination) {
		searchTerms = new SearchTerms(origin, destination, 
				ZonedDateTime.now().plus(daysToDeparture, ChronoUnit.DAYS), 
				new Passengers(numAdults, numChilds, numInfants));
	}
	
	@When("^I search for (\\d+) adults?, (\\d+) days? to the departure date, flying (.+) -> (.+)$")
	public void setTermsOnlyAdults(int numAdults, int daysToDeparture, String origin, String destination ) {
		setTerms(numAdults, 0, 0, daysToDeparture, origin, destination);
	}
	
	@When("^I search for (\\d+) adults?, (\\d+) child(?:ren)?, (\\d+) days? to the departure date, flying (.+) -> (.+)$")
	public void setTerms(int numAdults, int numChilds, int daysToDeparture, 
			String origin, String destination) {
		setTerms(numAdults, numChilds, 0, daysToDeparture, origin, destination);
	}
	
	@Then("^I should get this flights?:$")
	public void checkFlights(DataTable flightsTable) 
			throws PriceCalculationException, InvalidSearchTermsException {
		
		List<FlightInfo> searchedFlights = searchEngine.search(searchTerms);
		
		
		for (List<String> expectedFlight : flightsTable.raw()) {
			FlightInfo foundFlight = findFlight(expectedFlight, searchedFlights);
			
			assertTrue(foundFlight != null);
			@SuppressWarnings("resource")
			BigDecimal expectedPrice = new BigDecimal(new Scanner(expectedFlight.get(1)).next());
			// To compare BigDecimals we should use compareTo!
			// http://docs.oracle.com/javase/6/docs/api/java/math/BigDecimal.html#equals%28java.lang.Object%29
			assertTrue(expectedPrice.compareTo(foundFlight.finalPrice()) == 0);
		}
	}
	
	@Then("^I should get no flights$")
	public void checkNoFlights() 
			throws PriceCalculationException, InvalidSearchTermsException {
		
		List<FlightInfo> searchedFlights = searchEngine.search(searchTerms);
		assertEquals(0, searchedFlights.size());
	}

	private FlightInfo findFlight(List<String> expectedFlight, List<FlightInfo> comparisonFlights) {
		for (FlightInfo flightInfo : comparisonFlights) {
			if (flightInfo.flight().code().equals(expectedFlight.get(0)))
				return flightInfo;
		}
		return null;
		
	}
}
