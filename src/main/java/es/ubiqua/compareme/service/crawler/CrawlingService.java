package es.ubiqua.compareme.service.crawler;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import es.ubiqua.compareme.manager.HotelManager;
import es.ubiqua.compareme.manager.OtaManager;
import es.ubiqua.compareme.manager.PriceManager;
import es.ubiqua.compareme.model.Hotel;
import es.ubiqua.compareme.model.Ota;
import es.ubiqua.compareme.model.Price;
import es.ubiqua.compareme.model.Query;
import es.ubiqua.compareme.service.booking.BookingService;
import es.ubiqua.compareme.service.expedia.ExpediaService;
import es.ubiqua.compareme.service.hotels.HotelsService;
import es.ubiqua.compareme.service.venere.VenereService;

public class CrawlingService {
	
	public static int MONOTHREAD_MODE = 0;
	public static int MULTITHREAD_MODE = 1;
	private List<Price> prices;
	private PriceManager priceManager;
	private OtaManager otaManager;
	
	public CrawlingService(){
		priceManager = new PriceManager();
		otaManager = new OtaManager();
	}
	
	public List<Price> weaving(int mode, Query query){
		prices = new ArrayList<Price>();
		
		List<Ota> otas = new OtaManager().list();
		Hotel hotel = new Hotel();
		hotel.setName(query.getHotel());
	
		hotel = new HotelManager().get(hotel);
		
		for(Ota ota : otas){
			Price p = new Price();
		
			p.setHash(query.toHash(hotel.getId(), ota.getId()));
			p = priceManager.getByHash(p);
			if(p==null){
				p = crawlPrice(ota.getId(),query);
			}
			
			addPriceToResponse(p);
		}
		
		return prices;
	}
	
	private synchronized void addPriceToResponse(Price price){
		prices.add(price);
	}
	
	private Price crawlPrice(int otaId, Query query){
		Price p = new Price();
		Ota o = new Ota();
		o.setId(otaId);
		
		switch(otaId){
		case 1:
			ExpediaService es = new ExpediaService();
			p = es.setServiceParameters(query.getLang(), query.getHotel(), query.getGuests(),query.getRooms(),query.getDateIn(), query.getDateOut()).trackPrice();
			break;
		case 2:
			BookingService be = new BookingService();
			p = be.setServiceParameters(query.getLang(), query.getHotel(), query.getGuests(),query.getRooms(),query.getDateIn(), query.getDateOut()).trackPrice();
			break;
		case 3:
			HotelsService he = new HotelsService();
			p = he.setServiceParameters(query.getLang(), query.getHotel(), query.getGuests(),query.getRooms(),query.getDateIn(), query.getDateOut()).trackPrice();
			break;
		case 4:
			VenereService ve = new VenereService();
			p = ve.setServiceParameters(query.getLang(), query.getHotel(), query.getGuests(),query.getRooms(),query.getDateIn(), query.getDateOut()).trackPrice();
			break;
		}
		
		p.setSite(otaManager.get(o).getIcon());
		p.setBasePrice(query.getBase());
		priceManager.add(p);
	
		return p;
	}
}
