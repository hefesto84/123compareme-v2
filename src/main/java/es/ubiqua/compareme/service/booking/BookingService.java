package es.ubiqua.compareme.service.booking;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import es.ubiqua.compareme.exceptions.ServiceException;
import es.ubiqua.compareme.model.Ota;
import es.ubiqua.compareme.model.Price;
import es.ubiqua.compareme.service.Service;
import es.ubiqua.compareme.service.interfaces.ServiceInterface;
import es.ubiqua.compareme.utils.DBLogger;
import es.ubiqua.compareme.utils.Utils;

public class BookingService extends Service implements ServiceInterface{

	private static String OTA = "Booking";
	
	public BookingService setServiceParameters(String language, String name, int guests, int rooms, String dateIn, String dateOut) {
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
		
		if(!isConfigured){
			Logger.getLogger(BookingService.class).error(ServiceException.INVALID_ARGUMENTS);
		}
		
		try {

			Document d = Jsoup.connect("http://www.booking.com/"+hotelName+"."+price.getLanguage()+".html?aid=303651;sid=3e29979d6d50cf92f6cf2d9108161dc0;dcid=1;checkin="+Utils.sanitizeDateForBooking(price.getDateIn())+";checkout="+Utils.sanitizeDateForBooking(price.getDateOut())+";dist=0;group_adults=2;room1=A%2CA;sb_price_type=total;srfid=6093b67beca037cffc10e3dee1c751e4c8f92373X1;type=total;ucfs=1&").get();
		
			if (d.select("strong[data-price-without-addons]")!=null) {
				try{
					price.setPrice(d.select("strong[data-price-without-addons]").get(0).text());
				}catch(Exception e){
					price.setPrice("0");
				}
			}else{
				price.setPrice("0");
			}
			
			if (d.select("span.average")!=null) {
				price.setValoration(Integer.valueOf(d.select("span.average").get(0).text().replace(".", "").replace(",", "")));
			}else{
				price.setValoration(0);
			}
			
		} catch (IOException e) {
			Logger.getLogger(BookingService.class).error(ServiceException.INVALID_CRAWLER_URL);
		}
		
		price.setHash(price.toHash());
		return price;
	}

}
