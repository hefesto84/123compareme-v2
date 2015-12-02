package es.ubiqua.compareme.service.crawler;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import es.ubiqua.compareme.manager.CurrencyManager;
import es.ubiqua.compareme.manager.CustomerManager;
import es.ubiqua.compareme.manager.DomainManager;
import es.ubiqua.compareme.manager.HotelManager;
import es.ubiqua.compareme.manager.OtaManager;
import es.ubiqua.compareme.manager.PriceManager;
import es.ubiqua.compareme.model.Currency;
import es.ubiqua.compareme.model.Customer;
import es.ubiqua.compareme.model.Domain;
import es.ubiqua.compareme.model.Hotel;
import es.ubiqua.compareme.model.Ota;
import es.ubiqua.compareme.model.Price;
import es.ubiqua.compareme.model.Query;
import es.ubiqua.compareme.service.booking.BookingService;
import es.ubiqua.compareme.service.expedia.ExpediaService;
import es.ubiqua.compareme.service.hotels.HotelsService;
import es.ubiqua.compareme.service.venere.VenereService;

public class CrawlingService {
	
	public static final int MONOTHREAD_MODE = 0;
	public static final int MULTITHREAD_MODE = 1;
	private List<Price> prices;
	private PriceManager priceManager;
	private OtaManager otaManager;
	private CustomerManager customerManager;
	private CurrencyManager currencyManager;
	
	public CrawlingService(){
		priceManager = new PriceManager();
		otaManager = new OtaManager();
		customerManager = new CustomerManager();
		currencyManager = new CurrencyManager();
	}
	
	public List<Price> weaving(int mode, Query query){
		prices = new ArrayList<Price>();
		Customer c = new Customer();
		c.setIdentifier(query.getCustomerId());
		
		try{
			c = customerManager.get(c);
		}catch(Exception e){
			c.setId(10000);
			c.setIdentifier("10000");
		}
		
		List<Ota> otas = new OtaManager().list(c);
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
	
	private void addPriceToResponse(Price price){
		prices.add(price);
	}
	
	private Price crawlPrice(int otaId, Query query){
		Price p = new Price();
		
		Ota o = new Ota();
		o.setId(otaId);
		
		DomainManager dm = new DomainManager();
		Domain d = new Domain();
	    d.setCurrency(query.getCurrency());
	    d.setIdOta(otaId);
		d = dm.get(d);

		if(d==null){
			p.setHash("0");
			return p;
		}

		switch(otaId){
	
		case 1:
			ExpediaService es = new ExpediaService();
			p = es.setServiceParameters(query).trackPrice();
			//p = es.setServiceParameters(query.getLang(), query.getHotel(), query.getGuests(),query.getRooms(),query.getDateIn(), query.getDateOut()).trackPrice();
			break;
		case 2:
			BookingService be = new BookingService();
			p = be.setServiceParameters(query).trackPrice();
			//p = be.setServiceParameters(query.getLang(), query.getHotel(), query.getGuests(),query.getRooms(),query.getDateIn(), query.getDateOut()).trackPrice();
			break;
		
		case 3:
			HotelsService he = new HotelsService();
			p = he.setServiceParameters(query).trackPrice();
			//p = he.setServiceParameters(query.getLang(), query.getHotel(), query.getGuests(),query.getRooms(),query.getDateIn(), query.getDateOut()).trackPrice();
			break;
		
		case 4:
			VenereService ve = new VenereService();
			p = ve.setServiceParameters(query).trackPrice();
			//p = ve.setServiceParameters(query.getLang(), query.getHotel(), query.getGuests(),query.getRooms(),query.getDateIn(), query.getDateOut()).trackPrice();
			break;
		
		}
		p.setBackend(Integer.valueOf(query.getCustomerId()));
		p.setSite(otaManager.get(o).getIcon());
		p.setBasePrice(query.getBase());
		priceManager.add(p);
	
		return p;
	}
}
