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
	private Ota mOta;
	
	public BookingService setServiceParameters(String language, String name, int guests, int rooms, String dateIn, String dateOut) {
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
		
		if(!isConfigured){
			Logger.getLogger(BookingService.class).error(ServiceException.INVALID_ARGUMENTS);
		}
		
		try {
			url = "http://www.booking.com/"+hotelName+"."+price.getLanguage()+".html?aid=303651;sid=3e29979d6d50cf92f6cf2d9108161dc0;dcid=1;checkin="+Utils.sanitizeDateForBooking(price.getDateIn())+";checkout="+Utils.sanitizeDateForBooking(price.getDateOut())+";dist=0;selected_currency=EUR&group_adults="+price.getGuests()+";room1=A%2CA&";
			Document d = Jsoup.connect(url).get();
			System.out.println(url);
			if (d.select("strong[data-price-without-addons]")!=null) {
				try{
					price.setPrice(d.select("strong[data-price-without-addons]").get(0).text());
					mOta.setQueryOk(1);
				}catch(Exception e){
					price.setPrice("0");
					DBLogger.getLogger().Error(getClass().getName()+"|"+url+" ERROR: "+e.getMessage());
					Logger.getLogger(this.getClass()).error(e.getMessage());
				}
			}else{
				price.setPrice("0");
				DBLogger.getLogger().Warning(getClass().getName()+"|"+url+" WARNING: Weird Behaviour");
			}
			
			if (d.select("span.average")!=null) {
				price.setValoration(Integer.valueOf(d.select("span.average").get(0).text().replace(".", "").replace(",", "")));
			}else{
				price.setValoration(0);
			}
			
		} catch (IOException e) {
			price.setPrice("0");
			DBLogger.getLogger().Error(getClass().getName()+"|"+url+" ERROR: "+e.getMessage());
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}
		
		price.setHash(price.toHash());
		otaManager.update(mOta);
		return price;
	}

}
