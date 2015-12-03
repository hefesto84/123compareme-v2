package es.ubiqua.compareme.manager;

import java.util.ArrayList;
import java.util.List;

import es.ubiqua.compareme.dao.CustomerDAO;
import es.ubiqua.compareme.dao.HotelDAO;
import es.ubiqua.compareme.dao.OtaDAO;
import es.ubiqua.compareme.exceptions.CustomerException;
import es.ubiqua.compareme.exceptions.OtaException;
import es.ubiqua.compareme.model.Customer;
import es.ubiqua.compareme.model.Hotel;
import es.ubiqua.compareme.model.Ota;

public class HotelManager {
	
	private HotelDAO hotelDao = new HotelDAO();
	
	public HotelManager(){
		
	}
	
	public List<Hotel> list(){
		return hotelDao.list();
	}

	public List<Hotel> list(Customer c){
		return hotelDao.list(c);
	}
	
	public Hotel get(Hotel hotel){
		return hotelDao.get(hotel);
	}
	
	public Hotel getHotelByHotelName(Hotel hotel){
		return hotelDao.getHotelByHotelName(hotel);
	}
	
	public Hotel add(Hotel hotel){
		return hotelDao.add(hotel);
	}
	
}
