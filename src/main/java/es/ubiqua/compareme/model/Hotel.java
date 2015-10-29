package es.ubiqua.compareme.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Hotel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -439124803012544985L;
	private int id;
	private String name;
	private int customerId;
	private int otaId;
	private String currency;
	
	public Hotel(){
		
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getOtaId() {
		return otaId;
	}

	public void setOtaId(int otaId) {
		this.otaId = otaId;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
