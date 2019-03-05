package es.ubiqua.compareme.backend.actions;

import java.util.List;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

import es.ubiqua.compareme.manager.CustomerManager;
import es.ubiqua.compareme.manager.HotelManager;
import es.ubiqua.compareme.manager.HotelOtaManager;
import es.ubiqua.compareme.manager.OtaManager;
import es.ubiqua.compareme.model.Customer;
import es.ubiqua.compareme.model.Hotel;
import es.ubiqua.compareme.model.HotelOta;
import es.ubiqua.compareme.model.Ota;
import es.ubiqua.compareme.utils.Utils;

public class ManageHotelsBackendAction extends BaseBackendAction{

	private static final long serialVersionUID = 1L;

	private List<Customer> customers;
	private List<Hotel> hotels;
	private Hotel hotel;
	private CustomerManager customerManager = new CustomerManager();
	private HotelManager hotelManager = new HotelManager();
	private List<String> currencies;
	private int id;
	private List<HotelOta> hotelOtas;
	
	private String name;
	private String expediaId;
	private String bookingId;
	private String hotelsId;
	private String venereId;
	private String hrsId;
	private String currency;
	private String stars;
	private Boolean expediaActived;
	private Boolean bookingActived;
	private Boolean hotelsActived;
	private Boolean venereActived;
	private Boolean hrsActived;
	
	public String execute(){
		
		if(!isLogged()){ return ERROR; }
		
		currencies = Utils.LoadCurrencies();
		
		try{
			customers = customerManager.list(getLoggedCustomer());
			setHotels(hotelManager.list(getLoggedCustomer()));
		}catch(Exception e){
			System.out.println("error getting hotels...");
		}
		return SUCCESS;
	}
	
	public String edit(){
		System.out.println("LEO MESSI : "+id);
		
		currencies = Utils.LoadCurrencies();
		
		hotel = new Hotel();
		hotel.setId(id);
		hotel = hotelManager.get(hotel);
	
		hotelOtas = new HotelOtaManager().listByHotelId(id);
		
		System.out.println("STARS : "+hotel.getStars()+" - CURRENCY : "+hotel.getCurrency());
		System.out.println(new Gson().toJson(hotelOtas));
		
		return SUCCESS;
	}
	
	public String update(){
		
		System.out.println("LEO MESSI : "+name+" "+expediaId+" "+bookingId+" "+hotelsId+" "+venereId+" "+hrsId+" "+currency+" "+stars+" "+expediaActived+" "+bookingActived+" "+hotelsActived+" "+venereActived+" "+hrsActived);
		HotelOtaManager hotelOtaManager = new HotelOtaManager();
		
		Hotel hotel = new Hotel();
		hotel.setId(id);
		hotel = hotelManager.get(hotel);
		
		hotel.setName(name);
		hotel.setCurrency(currency);
		hotel.setStars(stars);
		hotelManager.update(hotel);
		
		HotelOta expediaOta = new HotelOta();
		expediaOta.setIdHotel(id);
		expediaOta.setIdOta(1);
		expediaOta.setName(expediaId);
		expediaOta.setActived(expediaActived);
		hotelOtaManager.update(expediaOta);
		
		HotelOta bookingOta = new HotelOta();
		bookingOta.setIdHotel(id);
		bookingOta.setIdOta(2);
		bookingOta.setName(bookingId);
		bookingOta.setActived(bookingActived);
		hotelOtaManager.update(bookingOta);
		
		HotelOta hotelsOta = new HotelOta();
		hotelsOta.setIdHotel(id);
		hotelsOta.setIdOta(3);
		hotelsOta.setName(hotelsId);
		hotelsOta.setActived(hotelsActived);
		hotelOtaManager.update(hotelsOta);
		
		HotelOta venereOta = new HotelOta();
		venereOta.setIdHotel(id);
		venereOta.setIdOta(4);
		venereOta.setName(venereId);
		venereOta.setActived(venereActived);
		hotelOtaManager.update(venereOta);
		
		HotelOta hrsOta = new HotelOta();
		hrsOta.setIdHotel(id);
		hrsOta.setIdOta(5);
		hrsOta.setName(hrsId);
		hrsOta.setActived(hrsActived);
		hotelOtaManager.update(hrsOta);
		
		return SUCCESS;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public List<Hotel> getHotels() {
		return hotels;
	}

	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public List<String> getCurrencies() {
		return currencies;
	}

	public void setCurrencies(List<String> currencies) {
		this.currencies = currencies;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<HotelOta> getHotelOtas() {
		return hotelOtas;
	}

	public void setHotelOtas(List<HotelOta> hotelOtas) {
		this.hotelOtas = hotelOtas;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExpediaId() {
		return expediaId;
	}

	public void setExpediaId(String expediaId) {
		this.expediaId = expediaId;
	}

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public String getHotelsId() {
		return hotelsId;
	}

	public void setHotelsId(String hotelsId) {
		this.hotelsId = hotelsId;
	}

	public Boolean getVenereActived() {
		return venereActived;
	}

	public void setVenereActived(Boolean venereActived) {
		this.venereActived = venereActived;
	}

	public Boolean getHotelsActived() {
		return hotelsActived;
	}

	public void setHotelsActived(Boolean hotelsActived) {
		this.hotelsActived = hotelsActived;
	}

	public Boolean getHrsActived() {
		return hrsActived;
	}

	public void setHrsActived(Boolean hrsActived) {
		this.hrsActived = hrsActived;
	}

	public String getVenereId() {
		return venereId;
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

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getStars() {
		return stars;
	}

	public void setStars(String stars) {
		this.stars = stars;
	}

	public Boolean getExpediaActived() {
		return expediaActived;
	}

	public void setExpediaActived(Boolean expediaActived) {
		this.expediaActived = expediaActived;
	}

	public Boolean getBookingActived() {
		return bookingActived;
	}

	public void setBookingActived(Boolean bookingActived) {
		this.bookingActived = bookingActived;
	}

}
