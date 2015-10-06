package es.ubiqua.compareme.model;

import es.ubiqua.compareme.utils.Utils;

public class Query {
	
	private String lang;
	private String hotel;
	private int rooms;
	private int guests;
	private String dateIn;
	private String dateOut;
	
	public Query(String lang, String hotel, int rooms, int guests, String dateIn, String dateOut){
		this.lang = lang;
		this.hotel = hotel;
		this.rooms = rooms;
		this.guests = guests;
		this.dateIn = dateIn;
		this.dateOut = dateOut;
	}

	public String getLang() {
		return lang;
	}

	public String getHotel() {
		return hotel;
	}

	public int getRooms() {
		return rooms;
	}

	public int getGuests() {
		return guests;
	}

	public String getDateIn() {
		return dateIn;
	}

	public String getDateOut() {
		return dateOut;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public void setHotel(String hotel) {
		this.hotel = hotel;
	}

	public void setRooms(int rooms) {
		this.rooms = rooms;
	}

	public void setGuests(int guests) {
		this.guests = guests;
	}

	public void setDateIn(String dateIn) {
		this.dateIn = dateIn;
	}

	public void setDateOut(String dateOut) {
		this.dateOut = dateOut;
	}
	
	public String toHash(int hotelId,int otaId){
		return Utils.compute(this.dateIn+this.dateOut+this.rooms+this.guests+hotelId+otaId+this.lang);
	}
}