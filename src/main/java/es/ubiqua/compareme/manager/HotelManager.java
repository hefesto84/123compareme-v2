package es.ubiqua.compareme.manager;

import java.util.List;

import es.ubiqua.compareme.dao.HotelDAO;
import es.ubiqua.compareme.model.Customer;
import es.ubiqua.compareme.model.Hotel;

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

	public List<Hotel> listOrdered(Customer c){
		return hotelDao.listOrdered(c);
	}
	
	public Hotel get(Hotel hotel){
		return hotelDao.get(hotel);
	}
	
	public List<Hotel> getHotelAutocompletar(String term){
		return hotelDao.getHotelAutocompletar(term);
	}
	
	public List<Hotel> getHotelAutocompletarSimple(String term, Customer c){
		return hotelDao.getHotelAutocompletarSimple(term,c);
	}
	
	public Hotel getHotelByHotelName(Hotel hotel){
		return hotelDao.getHotelByHotelName(hotel);
	}
	
	public Hotel add(Hotel hotel){
		return hotelDao.add(hotel);
	}
	
	public void update(Hotel hotel){
		hotelDao.update(hotel);
	}
	
}
