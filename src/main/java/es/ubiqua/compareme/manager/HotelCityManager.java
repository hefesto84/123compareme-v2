package es.ubiqua.compareme.manager;

import es.ubiqua.compareme.dao.HotelCityDAO;
import es.ubiqua.compareme.model.HotelCity;

public class HotelCityManager {
	
	private HotelCityDAO hotelCityDao = new HotelCityDAO();
	
	public HotelCityManager(){
		
	}
	
	/*public List<Hotel> list(){
		return hotelDao.list();
	}

	public List<Hotel> list(Customer c){
		return hotelDao.list(c);
	}*/
	
	public HotelCity get(HotelCity hotelCity){
		return hotelCityDao.get(hotelCity);
	}
	
	public HotelCity getByIdHotel(HotelCity hotelCity){
		return hotelCityDao.getByIdHotel(hotelCity);
	}
	
	
	/*public Hotel getHotelByHotelName(Hotel hotel){
		return hotelDao.getHotelByHotelName(hotel);
	}
	
	public Hotel add(Hotel hotel){
		return hotelDao.add(hotel);
	}*/
	
}
