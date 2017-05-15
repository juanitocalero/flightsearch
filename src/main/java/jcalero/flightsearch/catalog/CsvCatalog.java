package jcalero.flightsearch.catalog;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import jcalero.flightsearch.csv.CsvReader;
import jcalero.flightsearch.csv.InvalidCsvFormatException;

public abstract class CsvCatalog<T>  {
	
	protected Map<String, T> catalogMap = new HashMap<>();
	
	public CsvCatalog(URL csvUrl) throws InvalidCatalogException   {
		if (csvUrl == null)
			throw new InvalidCatalogException("Unable to load catalog. Invalid URL");
		try {
			loadCatalog(csvUrl.openStream());
		} catch (IOException | InvalidCsvFormatException e) {
			throw new InvalidCatalogException("Unable to load catalog", e);
		}
	}
	
	/**
	 * Loads the catalog from a source into the map 
	 * Closes the provided input stream
	 */
	private void loadCatalog(InputStream csvStream) throws IOException, InvalidCsvFormatException {
		
		catalogMap.clear();
		try (CsvReader reader = CsvReader.open(new InputStreamReader(csvStream), ",")) {
			
			while (reader.nextLine()) {
				T catalogObj = buildCatalogObj(reader.currentLine());
				// We don't stop if a problem is found in a line
				if (catalogObj != null)
					catalogMap.put(getObjectCode(catalogObj), catalogObj);
			}
			
		} 
		
	}

	/**
	 * BUils an Catalog Object from a CSV line.
	 * If Null is returned, the line is ignored
	 */
	// @Nullable
	protected abstract T buildCatalogObj(String[] currentLine);
	
	protected abstract String getObjectCode(T catalogObject);


}
