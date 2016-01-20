package es.ubiqua.compareme.manager;

import es.ubiqua.compareme.dao.SerhsDAO;
import es.ubiqua.compareme.model.Serhs;

public class SerhsManager {
	
	private SerhsDAO serhsDao = new SerhsDAO();
	
	public SerhsManager(){
		
	}
	
	/*public List<Hotel> list(){
		return hotelDao.list();
	}

	public List<Hotel> list(Customer c){
		return hotelDao.list(c);
	}*/
	
	public Serhs get(Serhs hotel){
		return serhsDao.get(hotel);
	}
	
	/*public Hotel getHotelByHotelName(Hotel hotel){
		return hotelDao.getHotelByHotelName(hotel);
	}
	
	public Hotel add(Hotel hotel){
		return hotelDao.add(hotel);
	}*/
	
}
