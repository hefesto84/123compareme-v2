package es.ubiqua.compareme.manager;

import es.ubiqua.compareme.dao.HotelHomeDAO;
import es.ubiqua.compareme.model.HotelHome;

public class HotelHomeManager {
	
	private HotelHomeDAO hotelHomeDao = new HotelHomeDAO();
	
	public HotelHomeManager(){
		
	}
	
	/*public List<Hotel> list(){
		return hotelDao.list();
	}

	public List<Hotel> list(Customer c){
		return hotelDao.list(c);
	}*/
	
	public HotelHome get(HotelHome hotelHome){
		return hotelHomeDao.get(hotelHome);
	}
	
	public HotelHome getByIdHotel(HotelHome hotelHome){
		return hotelHomeDao.getByIdHotel(hotelHome);
	}
	
	
	/*public Hotel getHotelByHotelName(Hotel hotel){
		return hotelDao.getHotelByHotelName(hotel);
	}
	
	public Hotel add(Hotel hotel){
		return hotelDao.add(hotel);
	}*/
	
}
