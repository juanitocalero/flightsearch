Feature: Flight Search

  Scenario: First example
    Given I use the flight search with the test data provided 
    When I search for 1 adult, 31 days to the departure date, flying AMS -> FRA
    Then I should get this flights:
     | TK2372 | 157.6 € |
     | TK2659 | 198.4 € |
     | LH5909 | 90.4 €  |
     
     
  Scenario: Second example
    Given I use the flight search with the test data provided 
    When I search for 2 adults, 1 child, 1 infant, 15 days to the departure date, flying LHR -> IST
    Then I should get this flights:
     | TK8891 | 806 €    |
     | LH1085 | 481.19 € |
     
     
 Scenario: Third example
    Given I use the flight search with the test data provided 
    When I search for 1 adult, 2 children, 2 days to the departure date, flying BCN -> MAD
    Then I should get this flights:
     | IB2171 | 909.09 €  |
     | LH5496 | 1028.43 € |
 
     
  Scenario: Fourth example
    Given I use the flight search with the test data provided 
    When I search for 1 adult, 15 days to the departure date, flying CDG -> FRA
    Then I should get no flights


