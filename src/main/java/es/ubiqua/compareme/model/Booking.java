package es.ubiqua.compareme.model;

public class Booking {

	private int id;
	private String bookingId;
	private String customerId;
	private String total;
	
	public Booking(){
		
	}

	public int getId() {
		return id;
	}

	public String getBookingId() {
		return bookingId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
	
}
