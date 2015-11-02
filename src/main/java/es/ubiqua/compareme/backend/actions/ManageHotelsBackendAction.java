package es.ubiqua.compareme.backend.actions;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import es.ubiqua.compareme.manager.CustomerManager;
import es.ubiqua.compareme.manager.HotelManager;
import es.ubiqua.compareme.manager.OtaManager;
import es.ubiqua.compareme.model.Customer;
import es.ubiqua.compareme.model.Hotel;
import es.ubiqua.compareme.model.Ota;

public class ManageHotelsBackendAction extends BaseBackendAction{

	private static final long serialVersionUID = 1L;

	private List<Customer> customers;
	private List<Hotel> hotels;
	private Hotel hotel;
	private CustomerManager customerManager = new CustomerManager();
	private HotelManager hotelManager = new HotelManager();
	
	public String execute(){
		
		if(!isLogged()){ return ERROR; }
		
		try{
			customers = customerManager.list();
			setHotels(hotelManager.list());
		}catch(Exception e){
			System.out.println("error getting hotels...");
		}
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

}