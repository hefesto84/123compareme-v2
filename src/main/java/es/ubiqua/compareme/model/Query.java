package es.ubiqua.compareme.model;

import es.ubiqua.compareme.utils.Utils;

public class Query {
	
	private String customerId;
	private String lang;
	private String hotel;
	private int rooms;
	private int guests;
	private String dateIn;
	private String dateOut;
	private String base;
	private String currency;
	private String currencyTemp;
	
	public Query(String customerId, String lang, String hotel, int rooms, int guests, String dateIn, String dateOut, String base, String currency){
		if(customerId==null){
			this.customerId = "10000";
		}else{
			this.customerId = customerId;
		}
		this.lang = lang;
		this.hotel = hotel;
		this.rooms = rooms;
		this.guests = guests;
		this.dateIn = dateIn;
		this.dateOut = dateOut;
		this.base = base;
		if(currency==null){
			this.currency = "EUR";
		}else{
			this.currency = currency;
		}
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
	
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String toHash(int hotelId,int otaId){
		String hash = Utils.compute(this.dateIn+this.dateOut+this.rooms+this.guests+hotelId+otaId);
		System.out.println("HASH: "+hash);
		return Utils.compute(this.dateIn+this.dateOut+this.rooms+this.guests+hotelId+otaId);
	}

	public String getCurrencyTemp() {
		return currencyTemp;
	}

	public void setCurrencyTemp(String currencyTemp) {
		this.currencyTemp = currencyTemp;
	}
}
