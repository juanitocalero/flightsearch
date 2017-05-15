package jcalero.flightsearch;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import jcalero.flightsearch.airlines.AirlinesCatalog;
import jcalero.flightsearch.airports.Airport;
import jcalero.flightsearch.airports.AirportsCatalog;
import jcalero.flightsearch.flights.Flight;
import jcalero.flightsearch.flights.FlightsData;
import jcalero.flightsearch.prices.PriceCalculationException;
import jcalero.flightsearch.prices.PriceCalculator;

public class SearchEngine {
	
	@SuppressWarnings("unused")
	private AirlinesCatalog airlinesCatalog;
	private AirportsCatalog airportsCatalog;
	private FlightsData flightsData;
	private PriceCalculator priceCalculator;
	private TimeProvider timeProvider;

	public SearchEngine(AirlinesCatalog airlinesCatalog, AirportsCatalog airportsCatalog, 
			FlightsData flightsData) {
		this.airlinesCatalog = airlinesCatalog;
		this.airportsCatalog = airportsCatalog;
		this.flightsData = flightsData;
		
		this.timeProvider = ()-> {
				return ZonedDateTime.now();
		};
		
		this.priceCalculator = new PriceCalculator(airlinesCatalog, timeProvider);
	}
	
	public List<FlightInfo> search(SearchTerms search) 
			throws PriceCalculationException, InvalidSearchTermsException {
		checkTerms(search);
		
		List<Flight> flights = flightsData.findFlights(search.origin(), search.destination());
		
		List<FlightInfo> returnInfo = new ArrayList<>();
		for (Flight flight : flights) {
			FlightInfo flightInfo = new FlightInfo(flight, 
					priceCalculator.calculatePrice(search, flight));
			returnInfo.add(flightInfo);
		}
		return returnInfo;
	}

	private void checkTerms(SearchTerms search) throws InvalidSearchTermsException {
		// at least one passenger
		if (search.passengers().total() < 1)
			throw new InvalidSearchTermsException("Invalid number of passengers");
		
		// Ensure that airports are correct
		Airport origAirport = airportsCatalog.findAirport(search.origin())
				.orElseThrow(()-> new InvalidSearchTermsException("Invalid origin airport"));
		
		Airport destAirport = airportsCatalog.findAirport(search.destination())
				.orElseThrow(()-> new InvalidSearchTermsException("Invalid destination airport"));
		
		if (origAirport.code().equals(destAirport.code())) {
			throw new InvalidSearchTermsException("Invalid flight route");
		}
		
		// Ensure that departure date is in the future (TODO add a margin?)
		if (search.date().isBefore(timeProvider.getCurrentTime()))
			throw new InvalidSearchTermsException("Invalid departure flight date");
		
	}

}
