package jcalero.flightsearch.airlines;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Arrays;
import java.util.Optional;

import jcalero.flightsearch.catalog.CsvCatalog;
import jcalero.flightsearch.catalog.InvalidCatalogException;

public class CsvAirlinesCatalog extends CsvCatalog<Airline> implements AirlinesCatalog {
	
	
	public CsvAirlinesCatalog(URL csvUrl) throws InvalidCatalogException   {
		super(csvUrl);
	}
	
	@Override
	public Optional<Airline> findAirline(String airlineCode) {
		return Optional.ofNullable(catalogMap.get(airlineCode));
	}

	@Override
	public Iterable<Airline> allAirlines() {
		return catalogMap.values();
	}
	
	

	/**
	 * BUils an AIrport Object from a CSV line.
	 * Returns Null if line can not be parsed
	 */
	// @Nullable
	@Override
	protected Airline buildCatalogObj(String[] currentLine) {
		try {
			BigDecimal infantPrice = new BigDecimal(currentLine[2]);
			return new Airline(currentLine[0], currentLine[1], infantPrice);
		} catch (Exception e) {
			// TODO: Better logging system
			System.out.println("Error reading csv line: " + Arrays.toString(currentLine));
			e.printStackTrace();
			return null;
		}
	}

	@Override
	protected String getObjectCode(Airline airline) {
		return airline.code();
	}


}
