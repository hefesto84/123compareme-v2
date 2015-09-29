package es.ubiqua.compareme.manager;

import java.util.ArrayList;
import java.util.List;

import es.ubiqua.compareme.dao.HotelOtaDAO;
import es.ubiqua.compareme.model.Hotel;
import es.ubiqua.compareme.model.HotelOta;
import es.ubiqua.compareme.model.Ota;

public class HotelOtaManager {
	
	private HotelOtaDAO hotelOtaDao = new HotelOtaDAO();
	
	public HotelOtaManager(){
		
	}
	
	public List<HotelOta> list(){
		return hotelOtaDao.list();
	}

	public HotelOta get(HotelOta hotelota){
		return hotelOtaDao.get(hotelota);
	}
	
	public HotelOta get(Hotel hotel, Ota ota){
		return hotelOtaDao.get(hotel, ota);
	}
	
	public HotelOta add(HotelOta hotelota){
		return hotelOtaDao.add(hotelota);
	}
}
