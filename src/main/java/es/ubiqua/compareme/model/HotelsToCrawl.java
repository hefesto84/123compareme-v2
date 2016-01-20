package es.ubiqua.compareme.model;

public class HotelsToCrawl {
	
	private int id;
	private int id_hotel;
	private int customer;
	private int days;
	private Boolean active;
	private String name_hotel;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId_hotel() {
		return id_hotel;
	}
	
	public void setId_hotel(int id_hotel) {
		this.id_hotel = id_hotel;
	}
	
	public int getCustomer() {
		return customer;
	}
	
	public void setCustomer(int customer) {
		this.customer = customer;
	}
	
	public int getDays() {
		return days;
	}
	
	public void setDays(int days) {
		this.days = days;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getName_hotel() {
		return name_hotel;
	}

	public void setName_hotel(String name_hotel) {
		this.name_hotel = name_hotel;
	}

}
