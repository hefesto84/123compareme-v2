package es.ubiqua.compareme.service.expedia;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.frozenbullets.api.currencyconverter.CurrencyConverter;

import es.ubiqua.compareme.exceptions.ServiceException;
import es.ubiqua.compareme.model.Domain;
import es.ubiqua.compareme.model.Ota;
import es.ubiqua.compareme.model.Price;
import es.ubiqua.compareme.model.Query;
import es.ubiqua.compareme.service.Service;
import es.ubiqua.compareme.service.hotels.HotelsService;
import es.ubiqua.compareme.service.interfaces.ServiceInterface;
import es.ubiqua.compareme.utils.DBLogger;
import es.ubiqua.compareme.utils.Utils;

public class ExpediaService extends Service implements ServiceInterface{

	private static String OTA = "Expedia";
	private Ota mOta;
	private String mDomain;
	private String mDateIn;
	private String mDateOut;
	
	public ExpediaService setServiceParameters(Query query){
		mOta = otaManager.get(new Ota(OTA));
		currencyResponse = query.getCurrency();
		
		Domain d = new Domain();
		d.setCurrency(query.getCurrency());
		d.setIdOta(mOta.getId());
		
		d = domainManager.get(d);
		
		mDomain = d.getDomain();
		
		query.setDateIn(Utils.formatDate(d.getFormat(), query.getDateIn()));
		query.setDateOut(Utils.formatDate(d.getFormat(), query.getDateOut()));
		
		return setServiceParameters(query.getLang(), query.getHotel(), query.getGuests(), query.getRooms(), query.getDateIn(), query.getDateOut());
	}
	
	public ExpediaService setServiceParameters(String language, String name, int guests, int rooms, String dateIn, String dateOut){
		price = new Price();
		//mOta = otaManager.get(new Ota(OTA));
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
		
			url = "https://"+mDomain+"/"+hotelName+".Informacion-Hotel?chkin="+price.getDateIn()+"&chkout="+price.getDateOut()+"&rm"+price.getRooms()+"=a"+price.getGuests()+"";
			Document d = Jsoup.connect(url).get();
			System.out.println("URL ++++++++++++++++++++++++++ "+url);
			
			Elements e1 = d.select("span.room-price.one-night-room-price");
			Elements e2 = d.select("a.price.link-to-rooms");
		
			boolean priceDetected = false;
			if(e1.size()!=0){
				priceDetected = true;
				try{
					String p = d.select("span.room-price.one-night-room-price").get(0).text();
					price.setPurePrice(p);
					price.setPrice(String.valueOf(Utils.change(p)));
					price.setPrice(CurrencyConverter.getInstance().convertCurrency(price.getPrice(), currencyResponse));
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
					//price.setPrice(CurrencyConverter.getInstance().convertCurrency(price.getPrice(), getCurrency(hotelId)));
					mOta.setQueryOk(1);
					
				}catch(Exception e){
					price.setPrice("0");
					DBLogger.getLogger().Error(getClass().getName()+"|"+url+" ERROR: "+e.getMessage());
				}
			}
			
			if(!priceDetected){
				Elements soldout = d.select("span.badge.badge-urgent.badge-notification.soldOutMsg.soldOutGeneral");
				System.out.println("SOLDOUT: "+soldout.html());
				price.setPrice("0");
				price.setPurePrice("0");
				DBLogger.getLogger().Warning(getClass().getName()+"|"+url+" WARNING: Weird Behaviour");
			}
			
			if (d.select("span.recommend-percentage")!=null) {
				/*
				price.setValoration(Integer.valueOf(d.select("span.recommend-percentage").text().replace("%", "")));
				*/
				price.setValoration(0);
			}else{
				price.setValoration(0);
			}
		
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
