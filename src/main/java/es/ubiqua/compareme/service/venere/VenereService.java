package es.ubiqua.compareme.service.venere;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.frozenbullets.api.currencyconverter.CurrencyConverter;
import com.google.gson.Gson;

import es.ubiqua.compareme.exceptions.ServiceException;
import es.ubiqua.compareme.model.Domain;
import es.ubiqua.compareme.model.Ota;
import es.ubiqua.compareme.model.Price;
import es.ubiqua.compareme.model.Query;
import es.ubiqua.compareme.service.Service;
import es.ubiqua.compareme.service.booking.BookingService;
import es.ubiqua.compareme.service.hotels.HotelsService;
import es.ubiqua.compareme.service.interfaces.ServiceInterface;
import es.ubiqua.compareme.utils.DBLogger;
import es.ubiqua.compareme.utils.Utils;

public class VenereService extends Service implements ServiceInterface{

	private static String OTA = "Venere";
	private Ota mOta;
	private String mDomain;
	
	public VenereService setServiceParameters(Query query){
		mOta = otaManager.get(new Ota(OTA));
		currencyResponse = query.getCurrency();

		Domain d = new Domain();
		d.setCurrency(query.getCurrency());
		d.setIdOta(mOta.getId());
		d = domainManager.get(d);
		mDomain = d.getDomain();
	
		query.setDateIn(Utils.formatDate(d.getFormat(), query.getDateIn(),mOta.getId()));
		query.setDateOut(Utils.formatDate(d.getFormat(), query.getDateOut(),mOta.getId()));
		
		Logger.getLogger(this.getClass()).debug("Crawling service venere");
		
		return setServiceParameters(query.getLang(), query.getHotel(), query.getGuests(), query.getRooms(), query.getDateIn(), query.getDateOut());
	}
	
	public VenereService setServiceParameters(String language, String name, int guests, int rooms, String dateIn, String dateOut) {
		price = new Price();
		mOta = otaManager.get(new Ota(OTA));
		mOta.setQueryOk(0);
		price.setOtaId(mOta.getId());
		price.setLanguage(language);
		price.setGuests(guests);
		price.setRooms(rooms);
		price.setDateIn(dateIn);
		price.setDateOut(dateOut);
		getOtaHotelName(OTA, name);
		price.setHotelId(hotelId);;
		isConfigured = true;
		return this;
	}

	public Price trackPrice() {

		String url = "";
		
		if(hotelActived){
	
			try{
				Connection.Response response = Jsoup.connect("http://es.venere.com/change_currency.html?currency="+currencyResponse).method(Connection.Method.GET).execute();
				url = "http://es.venere.com/hotel/details.html?tab=description&q-localised-check-in="+price.getDateIn()+"&hotel-id="+hotelName+"&q-room-0-adults="+price.getGuests()+"&YGF=0&MGT=2&WOE=6&q-localised-check-out="+price.getDateOut()+"&WOD=4&ZSX=0&SYE=3&q-room-0-children=0";
				
				Document doc = Jsoup.connect(url).timeout(5000).cookies(response.cookies()).ignoreHttpErrors(true).followRedirects(true).get();
				 Elements e = doc.select("form");
				 Map<String,String> data = new HashMap<String,String>();
				 for(int i = 0; i<e.size(); i++){
					 if(e.get(i).hasAttr("id")){
						 if(e.get(i).attr("id").equals("room-1-rateplan-1")){
							 Elements fields = e.get(i).select("input");
							 for(int j = 0; j<fields.size(); j++){
								 data.put(fields.get(j).attr("name"), fields.get(j).attr("value"));
							 }
						 }
					 }
				 }
				 Document request = Jsoup.connect("https://ssl-es.venere.com/bookingInitialise.do").timeout(5000).ignoreHttpErrors(true).followRedirects(true).data(data).post();
				 Elements rq = request.select("strong[id=financial-details-total-price]");
				 
				 String p =rq.text();
				 
				 // Si el preu no es null i tampoc està buit, busca preu
				 if(p!=null && p.length()>2){
					 price.setPurePrice(p);
					 price.setPrice(String.valueOf(Utils.change(p)));
					//price.setPrice(CurrencyConverter.getInstance().convertCurrency(price.getPrice(), getCurrency(hotelId)));
					
				 }else{
					 price.setPurePrice("0");
					 price.setPrice("0");
				 }
				 
				 //price.setPurePrice(p);
				//	price.setPrice(String.valueOf(Utils.change(p)));
					//price.setPrice(CurrencyConverter.getInstance().convertCurrency(price.getPrice(), getCurrency(hotelId)));
					mOta.setQueryOk(1);
					
			} catch (IOException e) {
				price.setPrice("0");
				price.setPurePrice("0");
				DBLogger.getLogger().Error(getClass().getName()+"|"+url+" ERROR: "+e.getMessage());
			}
			
		} else {
			price.setPrice("0");
			price.setPurePrice("0");
		}
		price.setQuery(url);
		price.setHash(price.toHash());
		otaManager.update(mOta);
		return price;
	}

}
