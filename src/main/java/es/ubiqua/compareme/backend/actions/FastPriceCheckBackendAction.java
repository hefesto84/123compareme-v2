package es.ubiqua.compareme.backend.actions;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

import es.ubiqua.compareme.manager.ExchangeManager;
import es.ubiqua.compareme.manager.HotelManager;
import es.ubiqua.compareme.manager.OtaManager;
import es.ubiqua.compareme.model.Exchange;
import es.ubiqua.compareme.model.Hotel;
import es.ubiqua.compareme.model.Ota;
import es.ubiqua.compareme.model.Price;
import es.ubiqua.compareme.model.Query;
import es.ubiqua.compareme.service.crawler.CrawlingService;
import es.ubiqua.compareme.utils.Utils;


public class FastPriceCheckBackendAction extends BaseBackendAction{

	private List<Ota> otas;
	private List<Hotel> hotels;
	private OtaManager otaManager = new OtaManager();
	private HotelManager hotelManager = new HotelManager();
	private ExchangeManager exchangeManager = new ExchangeManager();
	 private List<Price> datos = new ArrayList<Price>();
	 
	private String rooms;
	private String guests;

	private String hotelName;
	private String dateIn;
	private String dateOut;
	private String lang;
	private String currency;
	private String lastCurrency;
	private String lastLanguage;
	private List<Exchange> currencies = new ArrayList<Exchange>();
	
	private String query;
	private String ip;
	private int idHotel;
	
	private static final long serialVersionUID = -2527001795402427911L;

	public String execute(){
		
		if(!isLogged()){ return ERROR; }
		
		ip = Utils.getIP();
		otas = otaManager.list();
		hotels = hotelManager.list(getLoggedCustomer());
		
		currencies = exchangeManager.list();
		
		lastCurrency = currency;
		lastLanguage = lang;
	
		try{
			Hotel h = new Hotel();
			h.setName(new String(hotelName.getBytes("iso-8859-1"),"UTF-8"));
			h = hotelManager.get(h);
			idHotel = h.getId();
			
			boolean needToBeConverted = false;
			Query q = new Query("10000",lang, new String(hotelName.getBytes("iso-8859-1"),"UTF-8"), Integer.valueOf(rooms), Integer.valueOf(guests), dateIn, dateOut, "100",currency);
			CrawlingService service = new CrawlingService();
			
			if(exchangeManager.isCurrencyRestrictive(currency)){
				needToBeConverted = false;
			}else{
				if(!exchangeManager.isCurrencyAvailable(currency)){		
					currency = "XXX";
					return SUCCESS;
				}else{
					needToBeConverted = true;
					q.setCurrency("EUR");
				}
			}

	        datos = service.weaving(CrawlingService.MONOTHREAD_MODE, q);
	        
	        //if(needToBeConverted){
	        	for(Price p : datos){
		        	p.setPrice(String.valueOf(exchangeManager.change(Float.valueOf(p.getPrice()), currency)));
	        	}
	        //}
	  
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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(int idHotel) {
		this.idHotel = idHotel;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public List<Exchange> getCurrencies() {
		return currencies;
	}

	public void setCurrencies(List<Exchange> currencies) {
		this.currencies = currencies;
	}

	public String getLastCurrency() {
		return lastCurrency;
	}

	public void setLastCurrency(String lastCurrency) {
		this.lastCurrency = lastCurrency;
	}

	public String getLastLanguage() {
		return lastLanguage;
	}

	public void setLastLanguage(String lastLanguage) {
		this.lastLanguage = lastLanguage;
	}

}
