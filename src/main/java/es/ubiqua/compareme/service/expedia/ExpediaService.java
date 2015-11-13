package es.ubiqua.compareme.service.expedia;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.frozenbullets.api.currencyconverter.CurrencyConverter;

import es.ubiqua.compareme.exceptions.ServiceException;
import es.ubiqua.compareme.model.Ota;
import es.ubiqua.compareme.model.Price;
import es.ubiqua.compareme.service.Service;
import es.ubiqua.compareme.service.interfaces.ServiceInterface;
import es.ubiqua.compareme.utils.DBLogger;
import es.ubiqua.compareme.utils.Utils;

public class ExpediaService extends Service implements ServiceInterface{

	private static String OTA = "Expedia";
	private Ota mOta;
	
	public ExpediaService setServiceParameters(String language, String name, int guests, int rooms, String dateIn, String dateOut){
		price = new Price();
		mOta = otaManager.get(new Ota(OTA));
		mOta.setQueryOk(0);
		price.setOtaId(mOta.getId());
		price.setLanguage(language);
		price.setGuests(guests);
		price.setRooms(rooms);
		price.setDateIn(dateIn);
		price.setDateOut(dateOut);
		getOtaHotelName("Expedia", name);
		price.setHotelId(hotelId);
		isConfigured = true;
		return this;
	}
	
	public  Price  trackPrice(){
	
		String url  = "";
		
		if(!isConfigured){
			Logger.getLogger(ExpediaService.class).error(ServiceException.INVALID_ARGUMENTS);
		}
		
		try {
			url = "https://www.expedia.es/"+hotelName+".Informacion-Hotel?chkin="+price.getDateIn()+"&chkout="+price.getDateOut()+"&rm1=a2";
			Document d = Jsoup.connect(url).get();
			System.out.println("URL ++++++++++++++++++++++++++ "+url);
			
			Elements e1 = d.select("span.room-price.one-night-room-price");
			Elements e2 = d.select("a.price.link-to-rooms");
			
			System.out.println(e1.size());
			System.out.println(e2.size());
			
			boolean priceDetected = false;
			if(e1.size()!=0){
				priceDetected = true;
				try{
					String p = d.select("span.room-price.one-night-room-price").get(0).text();
					price.setPurePrice(p);
					price.setPrice(String.valueOf(Utils.change(p)));
					price.setPrice(CurrencyConverter.getInstance().convertCurrency(price.getPrice(), getCurrency(hotelId)));
					mOta.setQueryOk(1);
					
				}catch(Exception e){
					price.setPrice("0");
					DBLogger.getLogger().Error(getClass().getName()+"|"+url+" ERROR: "+e.getMessage());
				}
			}
			
			if(e2.size()!=0){
				priceDetected = true;
				try{
					String p = d.select("a.price.link-to-rooms").get(0).text();
					price.setPurePrice(p);
					price.setPrice(String.valueOf(Utils.change(p)));
					price.setPrice(CurrencyConverter.getInstance().convertCurrency(price.getPrice(), getCurrency(hotelId)));
					mOta.setQueryOk(1);
					
				}catch(Exception e){
					price.setPrice("0");
					DBLogger.getLogger().Error(getClass().getName()+"|"+url+" ERROR: "+e.getMessage());
				}
			}
			
			if(!priceDetected){
				price.setPrice("0");
				DBLogger.getLogger().Warning(getClass().getName()+"|"+url+" WARNING: Weird Behaviour");
			}
			
			if (d.select("span.recommend-percentage")!=null) {
				price.setValoration(Integer.valueOf(d.select("span.recommend-percentage").text().replace("%", "")));
			}else{
				price.setValoration(0);
			}
			
			/*
			if(d.select("span.room-price.one-night-room-price")!=null){
				try{

					String p = d.select("span.room-price.one-night-room-price").get(0).text();
					price.setPurePrice(p);
					price.setPrice(p);
					p = price.getPrice();
					p = Utils.changeCurrency(p,"EUR",getCurrency(hotelId));
					price.setPrice(p);
					mOta.setQueryOk(1);
					
				}catch(Exception e){
					price.setPrice("0");
					DBLogger.getLogger().Error(getClass().getName()+"|"+url+" ERROR: "+e.getMessage());
				}
			}else if(d.select("a.price.link-to-rooms")!=null){
				try{

					String p = d.select("a.price.link-to-rooms").get(0).text();
					price.setPurePrice(p);
					price.setPrice(p);
					p = price.getPrice();
					p = Utils.changeCurrency(p,"EUR",getCurrency(hotelId));
					price.setPrice(p);
					mOta.setQueryOk(1);
					
				}catch(Exception e){
					price.setPrice("0");
					DBLogger.getLogger().Error(getClass().getName()+"|"+url+" ERROR: "+e.getMessage());
				}
			}
			else{
				price.setPrice("0");
				DBLogger.getLogger().Warning(getClass().getName()+"|"+url+" WARNING: Weird Behaviour");
			}
		
			if (d.select("span.recommend-percentage")!=null) {
				price.setValoration(Integer.valueOf(d.select("span.recommend-percentage").text().replace("%", "")));
			}else{
				price.setValoration(0);
			}
				*/
		} catch (IOException e) {
			price.setPrice("0");
			DBLogger.getLogger().Error(getClass().getName()+"|"+url+" ERROR: "+e.getMessage());
		}
		price.setHash(price.toHash());
		otaManager.update(mOta);
		return price;
	}
	
}
