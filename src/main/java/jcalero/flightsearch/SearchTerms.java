package jcalero.flightsearch;

import java.time.ZonedDateTime;

public class SearchTerms {
	
	private String origin;
	private String destination;
	private ZonedDateTime date;
	private Passengers passengers;
	
	public SearchTerms(String origin, String destination, ZonedDateTime date, Passengers passengers) {
		this.origin = origin;
		this.destination = destination;
		this.date = date;
		this.passengers = passengers;
	}
	
	public String origin() {
		return origin;
	}
	
	public String destination() {
		return destination;
	}
	
	public ZonedDateTime date() {
		return date;
	}
	
	public Passengers passengers() {
		return passengers;
	}
	
	
	public static class Passengers {
		private int numAdults;
		private int numChilds;
		private int numInfants;
		
		public Passengers(int numAdults, int numChilds, int numInfants) {
			this.numAdults = numAdults;
			this.numChilds = numChilds;
			this.numInfants = numInfants;
		}
		
		public int numAdults() {
			return numAdults;
		}
		
		public int numChilds() {
			return numChilds;
		}
		
		public int numInfants() {
			return numInfants;
		}
		
		public int total() {
			return numAdults + numChilds + numInfants;
		}
	}
	

}
