package es.ubiqua.compareme.manager;

import java.util.List;

import es.ubiqua.compareme.dao.BookingDAO;
import es.ubiqua.compareme.dao.LogDAO;
import es.ubiqua.compareme.model.Booking;
import es.ubiqua.compareme.model.Log;

public class BookingManager {
	
	private BookingDAO bookingDao = new BookingDAO();
	
	public BookingManager(){
		
	}
	
	public Booking add(Booking booking){
		return bookingDao.add(booking);
	}

}
