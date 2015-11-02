package es.ubiqua.compareme.backend.actions.info;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

import es.ubiqua.compareme.backend.actions.BaseBackendAction;
import es.ubiqua.compareme.manager.CustomerManager;
import es.ubiqua.compareme.manager.HotelManager;
import es.ubiqua.compareme.manager.OtaManager;
import es.ubiqua.compareme.model.Customer;
import es.ubiqua.compareme.model.Hotel;
import es.ubiqua.compareme.model.Ota;
import es.ubiqua.compareme.utils.Utils;

public class AddHotelBackendAction extends BaseBackendAction{

	private static final long serialVersionUID = 1L;

	private Hotel hotel;

	private HotelManager hotelManager = new HotelManager();
	private Map<String,String> data = new HashMap<String,String>();
	
	private String name;
	private String currency;
	private int customer;
	
	public String execute(){
		
		//if(!isLogged()){ return ERROR; }

		return SUCCESS;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
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
}
