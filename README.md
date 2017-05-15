

## Design decisions
- Maybe all the CSV reading and Catalog concept is a little bit "overkill" for the problem at hand, but it has been implemented like that to isolate the main module from the data source (which is likely to change)
and to show up different programming techniques (interfaces, generics, etc...). 

- On the other hand, the Price Calculation module has been implemented in the most simple and straight way,
and it could lead to problems if the complexity in this area grows (which is likely, too)
But given the current requisites, more elaborated alternatives were too complicated and unnecessary 
(an attempt of PriceRules was tried and discarded)

## Improvements

- Better logging system
- Take encoding issues into account
- Test input failures and edge cases
- Add code coverage

