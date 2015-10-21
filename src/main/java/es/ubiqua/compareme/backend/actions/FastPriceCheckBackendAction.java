package es.ubiqua.compareme.backend.actions;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

import es.ubiqua.compareme.manager.HotelManager;
import es.ubiqua.compareme.manager.OtaManager;
import es.ubiqua.compareme.model.Hotel;
import es.ubiqua.compareme.model.Ota;
import es.ubiqua.compareme.model.Price;
import es.ubiqua.compareme.model.Query;
import es.ubiqua.compareme.service.crawler.CrawlingService;


public class FastPriceCheckBackendAction extends ActionSupport{

	private List<Ota> otas;
	private List<Hotel> hotels;
	private OtaManager otaManager = new OtaManager();
	private HotelManager hotelManager = new HotelManager();
	 private List<Price> datos = new ArrayList<Price>();
	 
	private String rooms;
	private String guests;

	private String hotelName;
	private String dateIn;
	private String dateOut;
	private String lang;
	
	private String query;
	
	private static final long serialVersionUID = -2527001795402427911L;

	public String execute(){
		otas = otaManager.list();
		hotels = hotelManager.list();
		try{
			Query q = new Query(lang, hotelName, Integer.valueOf(rooms), Integer.valueOf(rooms), dateIn, dateOut, "100");
			CrawlingService service = new CrawlingService();
			setDatos(service.weaving(CrawlingService.MONOTHREAD_MODE, q));
			this.query = new Gson().toJson(datos);
		}catch(Exception e){
			
		}
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

	public String getHotelName() {
		return hotelName;
	}

	public String getDateIn() {
		return dateIn;
	}

	public String getDateOut() {
		return dateOut;
	}

	public String getLang() {
		return lang;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public void setDateIn(String dateIn) {
		this.dateIn = dateIn;
	}

	public void setDateOut(String dateOut) {
		this.dateOut = dateOut;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getRooms() {
		return rooms;
	}

	public void setRooms(String rooms) {
		this.rooms = rooms;
	}

	public String getGuests() {
		return guests;
	}

	public void setGuests(String guests) {
		this.guests = guests;
	}

	public List<Price> getDatos() {
		return datos;
	}

	public void setDatos(List<Price> datos) {
		this.datos = datos;
	}

}
