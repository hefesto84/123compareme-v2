package es.ubiqua.compareme.backend.actions.info;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

import es.ubiqua.compareme.backend.actions.BaseBackendAction;
import es.ubiqua.compareme.manager.CustomerManager;
import es.ubiqua.compareme.manager.HotelManager;
import es.ubiqua.compareme.manager.HotelOtaManager;
import es.ubiqua.compareme.manager.OtaManager;
import es.ubiqua.compareme.model.Customer;
import es.ubiqua.compareme.model.Hotel;
import es.ubiqua.compareme.model.HotelOta;
import es.ubiqua.compareme.model.Ota;
import es.ubiqua.compareme.utils.Utils;

public class AddHotelBackendAction extends BaseBackendAction{

	private static final long serialVersionUID = 1L;

	private Hotel hotel;

	private HotelManager hotelManager = new HotelManager();
	private HotelOtaManager hotelOtaManager = new HotelOtaManager();
	private Map<String,String> data = new HashMap<String,String>();
	
	private String name;
	private String currency;
	private int customer;
	private String expediaId;
	private String bookingId;
	private String hotelsId;
	private String venereId;
	private String hrsId;
	
	
	public String execute(){
		
		//if(!isLogged()){ return ERROR; }

		return SUCCESS;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public String add(){
		hotel = new Hotel();
		hotel.setName(name);
		hotel.setCurrency(currency);
		hotel.setCustomerId(customer);
		hotel = hotelManager.add(hotel);
		
		HotelOta expedia = new HotelOta();
		HotelOta booking = new HotelOta();
		HotelOta hotels = new HotelOta();
		HotelOta venere = new HotelOta();
		HotelOta hrs = new HotelOta();
		
		expedia.setCurrency(currency);
		expedia.setIdHotel(hotel.getId());
		expedia.setIdOta(1);
		expedia.setName(expediaId);
		hotelOtaManager.add(expedia);
		
		booking.setCurrency(currency);
		booking.setIdHotel(hotel.getId());
		booking.setIdOta(2);
		booking.setName(bookingId);
		hotelOtaManager.add(booking);
		
		hotels.setCurrency(currency);
		hotels.setIdHotel(hotel.getId());
		hotels.setIdOta(3);
		hotels.setName(hotelsId);
		hotelOtaManager.add(hotels);
		
		venere.setCurrency(currency);
		venere.setIdHotel(hotel.getId());
		venere.setIdOta(4);
		venere.setName(venereId);
		hotelOtaManager.add(venere);
		
		hrs.setCurrency(currency);
		hrs.setIdHotel(hotel.getId());
		hrs.setIdOta(5);
		hrs.setName(hrsId);
		hotelOtaManager.add(hrs);
		
		return SUCCESS;
	}
	
	public String find(){
		hotel = new Hotel();
		hotel.setName(name);
		hotel.setCurrency(currency);
		hotel.setCustomerId(customer);
		
		setData(Utils.searchHotelIdentifiers(hotel.getName()));
		return SUCCESS;
	}

	public Map<String,String> getData() {
		return data;
	}

	public void setData(Map<String,String> data) {
		this.data = data;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setCustomer(int customer) {
		this.customer = customer;
	}
	
	public String getName() {
		return name;
	}

	public String getCurrency() {
		return currency;
	}

	public int getCustomer() {
		return customer;
	}

	public String getExpediaId() {
		return expediaId;
	}

	public String getBookingId() {
		return bookingId;
	}

	public String getHotelsId() {
		return hotelsId;
	}

	public String getVenereId() {
		return venereId;
	}

	public void setExpediaId(String expediaId) {
		this.expediaId = expediaId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public void setHotelsId(String hotelsId) {
		this.hotelsId = hotelsId;
	}

	public void setVenereId(String venereId) {
		this.venereId = venereId;
	}

	public String getHrsId() {
		return hrsId;
	}

	public void setHrsId(String hrsId) {
		this.hrsId = hrsId;
	}

}
