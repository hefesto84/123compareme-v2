package es.ubiqua.compareme.service.venere;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import es.ubiqua.compareme.exceptions.ServiceException;
import es.ubiqua.compareme.model.Ota;
import es.ubiqua.compareme.model.Price;
import es.ubiqua.compareme.service.Service;
import es.ubiqua.compareme.service.booking.BookingService;
import es.ubiqua.compareme.service.interfaces.ServiceInterface;
import es.ubiqua.compareme.utils.DBLogger;
import es.ubiqua.compareme.utils.Utils;

public class VenereService extends Service implements ServiceInterface{

	private static String OTA = "Venere";
	private Ota mOta;
	
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

		url = "http://es.venere.com/hotel/details.html?tab=description&q-localised-check-in="+price.getDateIn()+"&hotel-id="+hotelName+"&q-room-0-adults="+price.getGuests()+"&YGF=0&MGT=2&WOE=6&q-localised-check-out="+price.getDateOut()+"&WOD=4&ZSX=0&SYE=3&q-room-0-children=0";
	
		try {
			Document d = Jsoup.connect(url).get();
			System.out.println("URL ++++++++++++++++++++++++++ "+url);
			Elements newPrice = d.select("span.current-price.bold");
			
			if(newPrice!=null){
				String p = Utils.changeCurrency(newPrice.text(), getCurrency(hotelId), "EUR");
				price.setPrice(p);
				mOta.setQueryOk(1);
			}else{
				price.setPrice("0");
				DBLogger.getLogger().Warning(getClass().getName()+"|"+url+" WARNING: Weird Behaviour");
			}
		
		} catch (IOException e) {
			price.setPrice("0");
			DBLogger.getLogger().Error(getClass().getName()+"|"+url+" ERROR: "+e.getMessage());
		}
		price.setHash(price.toHash());
		otaManager.update(mOta);
		return price;
	}

}
