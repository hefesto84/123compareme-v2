package es.ubiqua.compareme.service.expedia;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import es.ubiqua.compareme.exceptions.ServiceException;
import es.ubiqua.compareme.model.Ota;
import es.ubiqua.compareme.model.Price;
import es.ubiqua.compareme.service.Service;
import es.ubiqua.compareme.service.interfaces.ServiceInterface;
import es.ubiqua.compareme.utils.DBLogger;

public class ExpediaService extends Service implements ServiceInterface{

	private static String OTA = "Expedia";
	
	public ExpediaService setServiceParameters(String language, String name, int guests, int rooms, String dateIn, String dateOut){
		price = new Price();
		price.setOtaId(otaManager.get(new Ota(OTA)).getId());
		price.setLanguage(language);
		price.setGuests(guests);
		price.setRooms(rooms);
		price.setDateIn(dateIn);
		price.setDateOut(dateOut);
		getOtaHotelName("Expedia", name);
		price.setHotelId(hotelId);;
		isConfigured = true;
		return this;
	}
	
	public  Price  trackPrice(){
	
		if(!isConfigured){
			Logger.getLogger(ExpediaService.class).error(ServiceException.INVALID_ARGUMENTS);
		}
		
		try {
			String url = "https://www.expedia."+price.getLanguage()+"/"+hotelName+".Informacion-Hotel?chkin="+price.getDateIn()+"&chkout="+price.getDateOut()+"&rm1=a2";
			Logger.getLogger(ExpediaService.class).debug("URL: "+url);
			Document d = Jsoup.connect("https://www.expedia."+price.getLanguage()+"/"+hotelName+".Informacion-Hotel?chkin="+price.getDateIn()+"&chkout="+price.getDateOut()+"&rm1=a2").get();
			
			if (d.select("a.price.link-to-rooms")!=null) {
				
				try{
					price.setPrice(d.select("a.price.link-to-rooms").text());
					//price.setPrice(d.select("span.room-price.one-night-room-price").get(0).text());
				}catch(Exception e){
					price.setPrice("0");
					DBLogger.getLogger().Error(getClass().getName()+"|"+url+" ERROR: "+e.getMessage());
				}
			}else{
				price.setPrice("0");
			}
			
			if (d.select("span.recommend-percentage")!=null) {
				price.setValoration(Integer.valueOf(d.select("span.recommend-percentage").text().replace("%", "")));
			}else{
				price.setValoration(0);
			}
			
		} catch (IOException e) {
			Logger.getLogger(ExpediaService.class).error(ServiceException.INVALID_CRAWLER_URL);
		}
		price.setHash(price.toHash());
		return price;
	}
	
}
