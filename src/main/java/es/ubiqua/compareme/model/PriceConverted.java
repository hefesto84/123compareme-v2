package es.ubiqua.compareme.model;

import java.io.Serializable;
import java.util.Date;

import com.google.gson.Gson;

import es.ubiqua.compareme.utils.Utils;

public class PriceConverted implements Serializable {
	
	private static final long serialVersionUID = -6558361774322743634L;
	
	private int id;
	private int hotelId;
	private String language;
	private String dateIn;
	private String dateOut;
	private int guests;
	private int rooms;
	private int otaId;
	private String price;
	private String currency;
	private String priceEuro;
	private String tipoCanvio;
	private String basePrice = "0";
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
		this.price = price;
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

	public void setOtaId(int otaId) {
		this.otaId = otaId;
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

	public String getPriceEuro() {
		return priceEuro;
	}

	public void setPriceEuro(String priceEuro) {
		this.priceEuro = priceEuro;
	}

	public String getTipoCanvio() {
		return tipoCanvio;
	}

	public void setTipoCanvio(String tipoCanvio) {
		this.tipoCanvio = tipoCanvio;
	}

}
