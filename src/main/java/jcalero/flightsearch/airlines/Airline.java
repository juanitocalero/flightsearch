package jcalero.flightsearch.airlines;

import java.math.BigDecimal;

public class Airline {
	private String code;
	private String name;
	
	private BigDecimal infantPrice;
	
	public Airline(String code, String name, BigDecimal infantPrice) {
		this.code = code;
		this.name = name;
		this.infantPrice = infantPrice;
	}
	
	public String code() {
		return code;
	}

	public String name() {
		return name;
	}

	public BigDecimal infantPrice() {
		return infantPrice;
	}
	
	

}
