package es.ubiqua.compareme.backend.actions;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import es.ubiqua.compareme.manager.HotelManager;
import es.ubiqua.compareme.manager.OtaManager;
import es.ubiqua.compareme.model.Hotel;
import es.ubiqua.compareme.model.Ota;
import es.ubiqua.compareme.model.Query;


public class FastPriceCheckBackendAction extends BaseBackendAction{

	private List<Ota> otas;
	private List<Hotel> hotels;
	private OtaManager otaManager = new OtaManager();
	private HotelManager hotelManager = new HotelManager();
	
	private int rooms;
	
	private static final long serialVersionUID = 1L;

	public String execute(){
		otas = otaManager.list();
		hotels = hotelManager.list();
		System.out.println(this.rooms);
		return SUCCESS;
	}

	public List<Ota> getOtas() {
		return otas;
	}

	public void setOtas(List<Ota> otas) {
		this.otas = otas;
	}

	public List<Hotel> getHotels() {
		return hotels;
	}

	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
	}

	public HotelManager getHotelManager() {
		return hotelManager;
	}

	public void setHotelManager(HotelManager hotelManager) {
		this.hotelManager = hotelManager;
	}

	public OtaManager getOtaManager() {
		return otaManager;
	}

	public void setOtaManager(OtaManager otaManager) {
		this.otaManager = otaManager;
	}

	public int getRooms() {
		return rooms;
	}

	public void setRooms(int rooms) {
		this.rooms = rooms;
	}

}
