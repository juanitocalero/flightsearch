package jcalero.flightsearch.airports;

import java.net.URL;
import java.util.Arrays;
import java.util.Optional;

import jcalero.flightsearch.catalog.CsvCatalog;
import jcalero.flightsearch.catalog.InvalidCatalogException;

public class CsvAirportsCatalog extends CsvCatalog<Airport> implements AirportsCatalog {
	
	
	public CsvAirportsCatalog(URL csvUrl) throws InvalidCatalogException   {
		super(csvUrl);
	}
	
	@Override
	public Optional<Airport> findAirport(String airportCode) {
		return Optional.ofNullable(catalogMap.get(airportCode));
	}

	@Override
	public Iterable<Airport> allAirports() {
		return catalogMap.values();
	}

	/**
	 * BUils an Airport Object from a CSV line.
	 * Returns Null if line can not be parsed
	 */
	// @Nullable
	@Override
	protected Airport buildCatalogObj(String[] currentLine) {
		try {
			return new Airport(currentLine[0], currentLine[1]);
		} catch (Exception e) {
			// TODO: Better logging system
			System.out.println("Error reading csv line: " + Arrays.toString(currentLine));
			e.printStackTrace();
			return null;
		}
	}

	@Override
	protected String getObjectCode(Airport airport) {
		return airport.code();
	}


}
