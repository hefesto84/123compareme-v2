package es.ubiqua.compareme.backend.actions.info;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import es.ubiqua.compareme.backend.actions.BaseBackendAction;
import es.ubiqua.compareme.manager.BookingManager;
import es.ubiqua.compareme.manager.OtaManager;
import es.ubiqua.compareme.manager.PriceManager;
import es.ubiqua.compareme.model.Booking;
import es.ubiqua.compareme.model.Ota;
import es.ubiqua.compareme.model.PricesByHotel;

public class LogBookingBackendAction extends BaseBackendAction{
	
	private static final long serialVersionUID = 1L;
	private BookingManager bookingManager = new BookingManager();
	private Booking booking;
	
	public String execute(){
		bookingManager.add(booking);
		return SUCCESS;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

}
