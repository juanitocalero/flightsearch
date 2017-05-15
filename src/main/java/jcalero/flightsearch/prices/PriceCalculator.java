package jcalero.flightsearch.prices;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import jcalero.flightsearch.SearchTerms;
import jcalero.flightsearch.SearchTerms.Passengers;
import jcalero.flightsearch.TimeProvider;
import jcalero.flightsearch.airlines.Airline;
import jcalero.flightsearch.airlines.AirlinesCatalog;
import jcalero.flightsearch.flights.Flight;

public class PriceCalculator {
	
	private AirlinesCatalog airlinesCatalog;
	private TimeProvider timeProvider;


	public PriceCalculator( AirlinesCatalog airlinesCatalog, TimeProvider timeProvider) {
		this.airlinesCatalog = airlinesCatalog;
		this.timeProvider = timeProvider;
	}
	

	
	public BigDecimal calculatePrice(SearchTerms searchTerms, Flight flight) 
			throws PriceCalculationException {
		Airline airline = airlinesCatalog.findAirline(flight.getAirlineCode())
				.orElseThrow(() -> new PriceCalculationException("Flight airline does not exist"));
		
		BigDecimal dateAdjustedPrice = adjustDepartureDate(flight.basePrice(), searchTerms.date());
		
		BigDecimal flightTotalPrice = BigDecimal.ZERO;
		Passengers passengers = searchTerms.passengers();
		
		if (passengers.numAdults() > 0)
			flightTotalPrice = flightTotalPrice.add(
					dateAdjustedPrice.multiply(BigDecimal.valueOf(passengers.numAdults())));
		if (passengers.numChilds() > 0)
			flightTotalPrice = flightTotalPrice.add(
					dateAdjustedPrice.multiply(BigDecimal.valueOf(passengers.numChilds()))
					.multiply(BigDecimal.valueOf(0.67)));
		if (passengers.numInfants() > 0)
			flightTotalPrice = flightTotalPrice.add(
					airline.infantPrice().multiply(BigDecimal.valueOf(passengers.numInfants())));
		
		// TODO: ¿Is this the right rounding?
		return flightTotalPrice.setScale(2, RoundingMode.HALF_UP);
	}
	

	private BigDecimal adjustDepartureDate(BigDecimal currentPrice, ZonedDateTime flightDate) 
			throws PriceCalculationException {
		// Get the difference in days from current date to date of departure
		long daysBetween = ChronoUnit.DAYS.between(timeProvider.getCurrentTime(), flightDate);
		
		if (daysBetween >= 31)
			return currentPrice.multiply(BigDecimal.valueOf(0.8));
		else if (daysBetween >= 16)
			return currentPrice;
		else if (daysBetween >= 3)
			return currentPrice.multiply(BigDecimal.valueOf(1.2));
		else if (daysBetween >= 0)
			return currentPrice.multiply(BigDecimal.valueOf(1.5));
		else
			throw new PriceCalculationException("Invalid dates");
	}
	
}
