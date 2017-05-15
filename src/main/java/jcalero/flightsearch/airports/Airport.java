package jcalero.flightsearch.airports;

public class Airport {
	
	private String code;
	private String name;
	
	
	public Airport(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public String code() {
		return code;
	}

	public String name() {
		return name;
	}

}
