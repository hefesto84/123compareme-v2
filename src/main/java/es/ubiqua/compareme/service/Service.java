package es.ubiqua.compareme.service;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import com.gargoylesoftware.htmlunit.BrowserVersion;

import es.ubiqua.compareme.manager.HotelManager;
import es.ubiqua.compareme.manager.HotelOtaManager;
import es.ubiqua.compareme.manager.OtaManager;
import es.ubiqua.compareme.model.Hotel;
import es.ubiqua.compareme.model.HotelOta;
import es.ubiqua.compareme.model.Ota;
import es.ubiqua.compareme.model.Price;

public class Service {
	
	protected Price price;
	protected boolean isConfigured = false;
	protected HtmlUnitDriver driver;
	protected OtaManager otaManager = new OtaManager();
	protected HotelOtaManager hotelOtaManager = new HotelOtaManager();
	protected HotelManager hotelManager = new HotelManager();
	protected String hotelName = "";
	protected int hotelId = 0;
	protected HotelOta ho;
	protected Hotel mHotel;
	
	public void initDriver(){
		this.driver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_8);
		this.driver.setJavascriptEnabled(true);
	}
	
	public void stop(){
		this.driver.close();
	}
	
	public void getOtaHotelName(String ota, String hotel){
		Hotel h = new Hotel();
		Ota o = new Ota();
		h.setName(hotel);
		o.setName(ota);
		o = otaManager.get(o);
		h.setOtaId(o.getId());
		HotelOta ho = hotelOtaManager.get(h, o);
		hotelName =ho.getName();
		hotelId = ho.getIdHotel();
	}
	
	public String getCurrency(int hotelId){
		mHotel = new Hotel();
		mHotel.setId(hotelId);
		mHotel = hotelManager.get(mHotel);
		System.out.println("++++++++++++++++++++++++++: "+mHotel.getCurrency());
		return mHotel.getCurrency();
	}
}
