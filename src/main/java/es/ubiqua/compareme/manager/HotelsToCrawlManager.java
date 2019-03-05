package es.ubiqua.compareme.manager;

import java.util.List;

import es.ubiqua.compareme.dao.HotelsToCrawlDAO;
import es.ubiqua.compareme.model.HotelsToCrawl;

public class HotelsToCrawlManager {
	
	private HotelsToCrawlDAO hotelsToCrawlDao = new HotelsToCrawlDAO();
	
	public HotelsToCrawlManager(){
		
	}
	
	public List<HotelsToCrawl> listCrawlable(){
		return hotelsToCrawlDao.listCrawlable();
	}

	/*public List<Hotel> list(Customer c){
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
	}*/
	
}
