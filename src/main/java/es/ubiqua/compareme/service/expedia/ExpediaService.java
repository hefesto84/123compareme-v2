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
	
		String url  = "";
		
		if(!isConfigured){
			Logger.getLogger(ExpediaService.class).error(ServiceException.INVALID_ARGUMENTS);
		}
		
		try {
			url = "https://www.expedia."+price.getLanguage()+"/"+hotelName+".Informacion-Hotel?chkin="+price.getDateIn()+"&chkout="+price.getDateOut()+"&rm1=a2";
			Document d = Jsoup.connect(url).get();
			System.out.println("URL ++++++++++++++++++++++++++ "+url);
			if (d.select("a.price.link-to-rooms")!=null) {
				
				try{
					price.setPrice(d.select("a.price.link-to-rooms").text());
				}catch(Exception e){
					price.setPrice("0");
					DBLogger.getLogger().Error(getClass().getName()+"|"+url+" ERROR: "+e.getMessage());
				}
			}else{
				price.setPrice("0");
				DBLogger.getLogger().Warning(getClass().getName()+"|"+url+" WARNING: Weird Behaviour");
			}
			
			if (d.select("span.recommend-percentage")!=null) {
				price.setValoration(Integer.valueOf(d.select("span.recommend-percentage").text().replace("%", "")));
			}else{
				price.setValoration(0);
			}
			
		} catch (IOException e) {
			DBLogger.getLogger().Error(getClass().getName()+"|"+url+" ERROR: "+e.getMessage());
		}
		price.setHash(price.toHash());
		return price;
	}
	
}
