package es.ubiqua.compareme.model;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
	
	private int id;
	private String name;
	private int customerId;
	private int otaId;
	
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

}
