package es.ubiqua.compareme.service.booking;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.frozenbullets.api.currencyconverter.CurrencyConverter;
import com.google.gson.Gson;

import es.ubiqua.compareme.exceptions.ServiceException;
import es.ubiqua.compareme.model.Domain;
import es.ubiqua.compareme.model.FuturePrice;
import es.ubiqua.compareme.model.Ota;
import es.ubiqua.compareme.model.Price;
import es.ubiqua.compareme.model.Query;
import es.ubiqua.compareme.service.Service;
import es.ubiqua.compareme.service.interfaces.ServiceInterface;
import es.ubiqua.compareme.utils.DBLogger;
import es.ubiqua.compareme.utils.Utils;
import es.ubiqua.compareme.utils.Utils.BookingPair;

public class BookingService extends Service implements ServiceInterface{

	private static String OTA = "Booking";
	private Ota mOta;
	private String mDomain;
	
	public BookingService setServiceParameters(Query query){
		mOta = otaManager.get(new Ota(OTA));
		currencyResponse = query.getCurrency();
		
		Domain d = new Domain();
		d.setCurrency(query.getCurrency());
		d.setIdOta(mOta.getId());
		d = domainManager.get(d);
		mDomain = d.getDomain();
		
		query.setDateIn(Utils.formatDate(d.getFormat(), query.getDateIn(),mOta.getId()));
		query.setDateOut(Utils.formatDate(d.getFormat(), query.getDateOut(),mOta.getId()));
		
		Logger.getLogger(this.getClass()).debug("Crawling service booking");
		
		return setServiceParameters(query.getLang(), query.getHotel(), query.getGuests(), query.getRooms(), query.getDateIn(), query.getDateOut());
	}
	
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
		
		price.setHotelId(hotelId);
		isConfigured = true;
		return this;
	}

	public Price trackPrice() {
		
		String url = "";
		
		if(!isConfigured){
			Logger.getLogger(BookingService.class).error(ServiceException.INVALID_ARGUMENTS);
		}
		
		try {
			url = "http://www.booking.com/"+hotelName+"."+"es"+".html?aid=303651;sid=3e29979d6d50cf92f6cf2d9108161dc0;dcid=1;checkin="+Utils.sanitizeDateForBooking(price.getDateIn())+";checkout="+Utils.sanitizeDateForBooking(price.getDateOut())+";dist=0;selected_currency="+currencyResponse+"&group_adults="+price.getGuests()+";room1=A%2CA&";
			Document d = Jsoup.connect(url).get();
						
			String querySelect = "tr[data-occupancy]";
			
			if (d.select(querySelect)!=null){
				try{
					BookingPair bp = Utils.getBookingBestPrice(d.select(querySelect),price.getGuests());
					
					price.setPurePrice(bp.sprice);
					price.setPrice(bp.fprice);
				
					mOta.setQueryOk(1);
				}catch(Exception e){
					price.setPrice("0");
					price.setPurePrice("0");
					DBLogger.getLogger().Error(getClass().getName()+"|"+url+" ERROR: "+e.getMessage());
					Logger.getLogger(this.getClass()).error(e.getMessage());
				}
			}else{
				price.setPrice("0");
				price.setPurePrice("0");
				DBLogger.getLogger().Warning(getClass().getName()+"|"+url+" WARNING: Weird Behaviour");
			}
			
			/*if (d.select("strong[data-price-without-addons]")!=null) {
				try{
					System.out.println("MESSI");
					BookingPair bp = Utils.getBookingBestPrice(d.select("strong[data-price-without-addons]"));
					
					price.setPurePrice(bp.sprice);
					price.setPrice(bp.fprice);
				
					mOta.setQueryOk(1);
				}catch(Exception e){
					price.setPrice("0");
					price.setPurePrice("0");
					DBLogger.getLogger().Error(getClass().getName()+"|"+url+" ERROR: "+e.getMessage());
					Logger.getLogger(this.getClass()).error(e.getMessage());
				}
			}else{
				price.setPrice("0");
				price.setPurePrice("0");
				DBLogger.getLogger().Warning(getClass().getName()+"|"+url+" WARNING: Weird Behaviour");
			}*/
			
			price.setValoration(0);
		
			
		} catch (IOException e) {
			price.setPrice("0");
			price.setPurePrice("0");
			DBLogger.getLogger().Error(getClass().getName()+"|"+url+" ERROR: "+e.getMessage());
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}
		price.setQuery(url);
		price.setHash(price.toHash());
		otaManager.update(mOta);
		return price;
	}

}
