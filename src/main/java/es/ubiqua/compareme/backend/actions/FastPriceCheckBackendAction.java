package es.ubiqua.compareme.backend.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;

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
	private String hotelNameAutocompletar;
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
	
	private String term;
	private String response;
	
	private static final long serialVersionUID = -2527001795402427911L;

	public String execute(){
		
		if(!isLogged()){ return ERROR; }
		
		ip = Utils.getIP();
		otas = otaManager.list();
		hotels = hotelManager.listOrdered(getLoggedCustomer());
		
		currencies = exchangeManager.list();
		
		lastCurrency = currency;
		lastLanguage = lang;
	
		try{
			if (!hotelNameAutocompletar.equals("")){
				hotelName = hotelNameAutocompletar;
			}
			Hotel h = new Hotel();
			h.setName(new String(hotelName.getBytes("iso-8859-1"),"UTF-8"));
			h = hotelManager.get(h);
			idHotel = h.getId();
			
			dateIn = converseDate(dateIn);
			dateOut = converseDate(dateOut);
			
			boolean needToBeConverted = false;
			boolean needToBeConvertedHRS = false;
			Query q = new Query("10000",lang, new String(hotelName.getBytes("iso-8859-1"),"UTF-8"), Integer.valueOf(rooms), Integer.valueOf(guests), dateIn, dateOut, "100",currency);
			CrawlingService service = new CrawlingService();
			
			if (!exchangeManager.isCurrencyRestrictiveHrs(currency)){
				needToBeConvertedHRS = true;
			}
			
			if(exchangeManager.isCurrencyRestrictive(currency)){
				needToBeConverted = false;
				q.setConverted(needToBeConverted);
				q.setConvertedHrs(needToBeConvertedHRS);
			}else{
				if(!exchangeManager.isCurrencyAvailable(currency)){		
					currency = "XXX";
					return SUCCESS;
				}else{
					needToBeConverted = true;
					q.setCurrency("EUR");
					q.setCurrencyTemp(currency);
					q.setConverted(needToBeConverted);
					q.setConvertedHrs(needToBeConvertedHRS);
				}
			}

	        datos = service.weaving(CrawlingService.MONOTHREAD_MODE, q);
	        
	        if (needToBeConverted || needToBeConvertedHRS){
		        for (Price p : datos){
		        	if (((needToBeConverted) && (p.getOtaId() != 5)) || ((needToBeConvertedHRS) && (p.getOtaId() == 5))){
		        		Utils.convertPrice(p,currency);
			        	p.setPrice(String.valueOf(exchangeManager.change(Float.valueOf(p.getPrice()), currency)));
		        	}
		        }
	        }
	  
	        //this.query = "http://www.meneame.net";
			//this.query = new Gson().toJson(datos);
		}catch(Exception e){
			
		}
		return SUCCESS;
	}
	
	public String autocompletar(){
				
		List<Hotel> hotels = new ArrayList<Hotel>();
		hotels = new HotelManager().getHotelAutocompletarSimple(term,getLoggedCustomer());
		Map[] array = new Map[hotels.size()];
		int count = 0;
		for(Hotel hotel:hotels){
			Map<String, String> hoteles = new HashMap<String, String>();
			hoteles.put("label" , hotel.getName());
			array[count] = hoteles;
			count++;
		}
		
		
		response = new Gson().toJson(array);
				
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
	
	private String converseDate(String date){
				
		int primeraAparicion = StringUtils.ordinalIndexOf(date, "/", 1);
		int segundaAparicion = StringUtils.ordinalIndexOf(date, "/", 2);
		
		int day = Integer.parseInt(date.substring(0,primeraAparicion));
		int month = Integer.parseInt(date.substring(primeraAparicion + 1,segundaAparicion));
		String year = date.substring(segundaAparicion + 1,segundaAparicion + 5);
		String dayText;
		String monthText;
		
		if (day < 10){
			dayText = "0"+day;
		} else {
			dayText = ""+day;
		}
		
		if (month < 10){
			monthText = "0"+month;
		} else {
			monthText = ""+month;
		}
		
		String dateConverted = dayText+"/"+monthText+"/"+year;
		
		return dateConverted;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getHotelNameAutocompletar() {
		return hotelNameAutocompletar;
	}

	public void setHotelNameAutocompletar(String hotelNameAutocompletar) {
		this.hotelNameAutocompletar = hotelNameAutocompletar;
	}
	
	

}
