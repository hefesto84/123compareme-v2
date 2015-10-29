package es.ubiqua.compareme.model;

import java.io.Serializable;

public class PricesByHotel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 546990681515308719L;
	private int times;
	private String name;
	
	public PricesByHotel(){
		
	}

	public int getTimes() {
		return times;
	}

	public String getName() {
		return name;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public void setName(String name) {
		this.name = name;
	}
}
