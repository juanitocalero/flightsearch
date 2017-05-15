## Introduction

This is my implementation of a simple Flight Search, according to the requisites provided (See [REQUISITES.md](REQUISITES.md))


## Use

To use the flight search, you must instantiate a SearchEngine object, passing in all the previously loaded 
data, fill a SearchTerms object, and call `SearchEngine.search()` passing the `SearchTerm` object:

```java

FlightsData flightsData = new CsvFlightsData(this.getClass().getResource("/flights.csv"))7;
AirportsCatalog airportsCatalog = new CsvAirportsCatalog(this.getClass().getResource("/airports.csv"));
AirlinesCatalog airlinesCatalog = new CsvAirlinesCatalog(this.getClass().getResource("/airlines.csv"));
		
searchEngine = new SearchEngine(airlinesCatalog, airportsCatalog, flightsData);

searchTerms = new SearchTerms(origin, destination, 
	ZonedDateTime.now().plus(daysToDeparture, ChronoUnit.DAYS), 
	new Passengers(numAdults, numChilds, numInfants));
				
List<FlightInfo> searchedFlights = searchEngine.search(searchTerms);

```

## Tests

The project has some Unit Tests implemented with JUnit, and acceptance tests written with Cucumber ([See flight-search.feature](src/test/resources/jcalero/flightsearch/flight-search.feature)) 

The project is linked to travis-ci to automate testing runs.


## Design considerations
- Maybe all the CSV reading and Catalog concept is a little bit "overkill" for the problem at hand, but it has been implemented like that to isolate the main module from the data source (which is likely to change)
and to show up different programming techniques (interfaces, generics, etc...). 

- On the other hand, the Price Calculation module has been implemented in the most simple and straight way,
and it could lead to problems if the complexity in this area grows (which is likely, too).
But given the current requisites, more elaborated alternatives were too complicated or unnecessary 
(an attempt of PriceRules was tried and discarded).


## ToDos and Improvements

- Add Javadocs
- Better logging system
- Take encoding issues into account
- Test input failures and edge cases
- Add code coverage

