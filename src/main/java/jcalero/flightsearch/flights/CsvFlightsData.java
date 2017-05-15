package jcalero.flightsearch.flights;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import jcalero.flightsearch.catalog.InvalidCatalogException;
import jcalero.flightsearch.csv.CsvReader;
import jcalero.flightsearch.csv.InvalidCsvFormatException;

public class CsvFlightsData implements FlightsData {
	
	protected List<Flight> flightsList = new ArrayList<>();
	
	public CsvFlightsData(URL csvUrl) throws InvalidCatalogException   {
		if (csvUrl == null)
			throw new InvalidCatalogException("Unable to load data. Invalid URL");
		try {
			loadFlights(csvUrl.openStream());
		} catch (IOException | InvalidCsvFormatException e) {
			throw new InvalidCatalogException("Unable to load data", e);
		}
	}
	
	/**
	 * Loads the catalog from a source into the map 
	 * Closes the provided input stream
	 */
	private void loadFlights(InputStream csvStream) throws IOException, InvalidCsvFormatException {
		
		flightsList.clear();
		try (CsvReader reader = CsvReader.open(new InputStreamReader(csvStream), ",")) {
			
			while (reader.nextLine()) {
				Flight flight = buildFlightObj(reader.currentLine());
				// We don't stop if a problem is found in a line
				if (flight != null)
					flightsList.add(flight);
			}
			
		} 
		
	}

	/**
	 * BUils a Flight Object from a CSV line.
	 * If Null is returned, the line is ignored
	 */
	// @Nullable
	protected Flight buildFlightObj(String[] currentLine) {
		
		try {
			BigDecimal price = new BigDecimal(currentLine[3]);
			return new Flight(currentLine[2], currentLine[0], currentLine[1], price);
		} catch (Exception e) {
			// TODO: Better logging system
			System.out.println("Error reading csv line: " + Arrays.toString(currentLine));
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Flight> findFlights(String origAirportCode, String destAirportCode) {
		return flightsList.stream()
			.filter(flight -> flight.isRoute(origAirportCode, destAirportCode))
			.collect(Collectors.toList());
	}
	


}
