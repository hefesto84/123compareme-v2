package es.ubiqua.compareme.service.hrs;

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

public class HRSService extends Service implements ServiceInterface{

	private static String OTA = "HRS";
	private Ota mOta;
	private String mDomain;
	
	public HRSService setServiceParameters(Query query){
		mOta = otaManager.get(new Ota(OTA));
		currencyResponse = query.getCurrency();

		Domain d = new Domain();
		d.setCurrency(query.getCurrency());
		d.setIdOta(mOta.getId());
		d = domainManager.get(d);
		mDomain = d.getDomain();
	
		query.setDateIn(Utils.formatDate(d.getFormat(), query.getDateIn(),mOta.getId()));
		query.setDateOut(Utils.formatDate(d.getFormat(), query.getDateOut(),mOta.getId()));
		
		Logger.getLogger(this.getClass()).debug("Crawling service hrs");
		
		return setServiceParameters(query.getLang(), query.getHotel(), query.getGuests(), query.getRooms(), query.getDateIn(), query.getDateOut());
	}
	
	public HRSService setServiceParameters(String language, String name, int guests, int rooms, String dateIn, String dateOut) {
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
		String singleRooms = "1";
		String dobleRooms = "0";
		
		if (price.getGuests() >= 2){
			singleRooms = "0";
			dobleRooms = "1";
		}
	
		try{
			url = "http://www.hrs.com/web3/hotelData.do?currency="+currencyResponse+"&activity=photo&&singleRooms="+singleRooms+"&doubleRooms="+dobleRooms+"&adults="+price.getGuests()+"&children=0&startDateDay="+price.getDateIn().substring(0, 2)+"&startDateMonth="+price.getDateIn().substring(3, 5)+"&startDateYear="+price.getDateIn().substring(6, 10)+"&endDateDay="+price.getDateOut().substring(0, 2)+"&endDateMonth="+price.getDateOut().substring(3, 5)+"&endDateYear="+price.getDateOut().substring(6, 10)+"&availability=true&hotelnumber="+hotelName;
			Document d = Jsoup.connect(url).get();
			Elements e0 = d.select("span.basketTotalAltPrice");
			Elements e1 = d.select("span.basketTotalPrice");
			
			String p = "";

			if (!e0.hasClass("hide")){
				String entero = e0.first().html().substring(e0.first().html().indexOf("(") + 1, e0.first().html().indexOf("<sup>")).trim();
				String decimal = e0.first().html().substring(e0.first().html().indexOf("<sup>") + 5, e0.first().html().indexOf("</sup>")).trim();
				p = entero + "." + decimal;
			} else{
				if(e1.size()!=0){
					String entero = e1.first().html().substring(e1.first().html().indexOf("<strong>") + 8, e1.first().html().indexOf("<sup>")).trim();
					String decimal = e1.first().html().substring(e1.first().html().indexOf("<sup>") + 5, e1.first().html().indexOf("</sup>")).trim();
					p = entero + "." + decimal;
				}
			}
			 
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
	
		price.setQuery(url);
		price.setHash(price.toHash());
		otaManager.update(mOta);
		return price;
	}

}
