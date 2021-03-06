package es.ubiqua.compareme.model;

import java.io.Serializable;
import java.util.Date;

import com.google.gson.Gson;

import es.ubiqua.compareme.utils.Utils;

public class Price implements Serializable {
	
	private static final long serialVersionUID = -6558361774322743634L;
	
	private int id;
	private int hotelId;
	private String language;
	private String dateIn;
	private String dateOut;
	private int guests;
	private int rooms;
	private int otaId;
	private String site;
	private String price;
	private String currency;
	private String purePrice;
	private String basePrice = "0";
	private int valoration;
	private String hash = "";
	private String query;
	private int backend;
	private Date date;
	
	public String getLanguage() {
		return language;
	}
	
	public String getDateIn() {
		return dateIn;
	}
	
	public String getDateOut() {
		return dateOut;
	}
	
	public int getGuests() {
		return guests;
	}
	
	public int getRooms() {
		return rooms;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public void setDateIn(String dateIn) {
		this.dateIn = dateIn;
	}
	
	public void setDateOut(String dateOut) {
		this.dateOut = dateOut;
	}
	
	public void setGuests(int guests) {
		this.guests = guests;
	}
	
	public void setRooms(int rooms) {
		this.rooms = rooms;
	}
	
	public String getPrice() {
		return price;
	}
	
	public void setPrice(String price) {
		this.price = price;//String.valueOf(Utils.change(price));
		//this.price = price.replaceAll("[^\\d.,]", "");
		//this.price = this.price.replace(",", ".");
		
	}
	
	public int getValoration() {
		return valoration;
	}
	
	public void setValoration(int valoration) {
		this.valoration = valoration;
	}
	
	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	public String getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(String basePrice) {
		this.basePrice = basePrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOtaId() {
		return otaId;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public void setOtaId(int otaId) {
		this.otaId = otaId;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getPurePrice() {
		return purePrice;
	}

	public void setPurePrice(String purePrice) {
		this.purePrice = purePrice;
	}
	
	@Override
	public String toString(){
		return new Gson().toJson(this);
	}
	
	public String toHash(){
		return Utils.compute(this.dateIn+this.dateOut+this.rooms+this.guests+this.hotelId+this.otaId/*+this.language*/);
	}
	
	public String toDBLogger(){
		String s = this.dateIn+"|"+this.dateOut+"|"+this.rooms+"|"+this.guests+"|"+this.hotelId+"|"+this.otaId+"|"+this.language;
		return s;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public int getBackend() {
		return backend;
	}

	public void setBackend(int backend) {
		this.backend = backend;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
