package es.ubiqua.compareme.service.venere;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import es.ubiqua.compareme.exceptions.ServiceException;
import es.ubiqua.compareme.model.Ota;
import es.ubiqua.compareme.model.Price;
import es.ubiqua.compareme.service.Service;
import es.ubiqua.compareme.service.booking.BookingService;
import es.ubiqua.compareme.service.interfaces.ServiceInterface;
import es.ubiqua.compareme.utils.Utils;

public class VenereService extends Service implements ServiceInterface{

	private static String OTA = "Venere";
	
	public VenereService setServiceParameters(String language, String name, int guests, int rooms, String dateIn, String dateOut) {
		price = new Price();
		price.setOtaId(otaManager.get(new Ota(OTA)).getId());
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
		String url = "http://"+price.getLanguage()+".venere.com/"+hotelName+"&arrivalDate="+Utils.sanitizeDateForHotels(price.getDateIn())+"&departureDate="+Utils.sanitizeDateForHotels(price.getDateOut())+"&rooms[0].numberOfAdults="+price.getGuests()+"&roomno=1&validate=false&previousDateful=false&reviewOrder=date_newest_first&PSRC=TRIP1&pos=HCOM_ES&hotelid=149150&locale=es_ES&wapa1=149150&rffrid=MDP.HCOM.ES.001.126.01.ES-DM_B00.HDSHReb.B.kwrd%3DTAIDVgAV6QokHFQAAEHwV2EAAAAY";
		try {
			Document d = Jsoup.connect(url).get();
			
			if (d.select("span.current-price.has-old-price")!=null) {
				try{
				price.setPrice(d.select("span.current-price.has-old-price").get(0).text());
				}catch(Exception e){
					price.setPrice("0");
				}
			}else{
				price.setPrice("0");
			}
		} catch (IOException e) {
			Logger.getLogger(BookingService.class).error(ServiceException.INVALID_CRAWLER_URL);
		}
		
		price.setHash(price.toHash());
		return price;
	}

}
