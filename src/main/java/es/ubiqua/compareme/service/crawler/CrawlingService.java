package es.ubiqua.compareme.service.crawler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.gson.Gson;

import es.ubiqua.compareme.manager.CurrencyManager;
import es.ubiqua.compareme.manager.CustomerManager;
import es.ubiqua.compareme.manager.DomainManager;
import es.ubiqua.compareme.manager.FuturePriceManager;
import es.ubiqua.compareme.manager.HotelManager;
import es.ubiqua.compareme.manager.OtaManager;
import es.ubiqua.compareme.manager.PriceManager;
import es.ubiqua.compareme.model.Currency;
import es.ubiqua.compareme.model.Customer;
import es.ubiqua.compareme.model.Domain;
import es.ubiqua.compareme.model.FuturePrice;
import es.ubiqua.compareme.model.Hotel;
import es.ubiqua.compareme.model.Ota;
import es.ubiqua.compareme.model.Price;
import es.ubiqua.compareme.model.Query;
import es.ubiqua.compareme.service.booking.BookingService;
import es.ubiqua.compareme.service.expedia.ExpediaService;
import es.ubiqua.compareme.service.hotels.HotelsService;
import es.ubiqua.compareme.service.hrs.HRSService;
import es.ubiqua.compareme.service.venere.VenereService;
import es.ubiqua.compareme.utils.Utils;

public class CrawlingService {
	
	public static final int MONOTHREAD_MODE = 0;
	public static final int MULTITHREAD_MODE = 1;
	private List<Price> prices;
	private List<FuturePrice> futurePrices;
	private PriceManager priceManager;
	private FuturePriceManager futurePriceManager;
	private OtaManager otaManager;
	private CustomerManager customerManager;
	private CurrencyManager currencyManager;
	private String dateIn = "";
	private String dateOut = "";
	
	public CrawlingService(){
		priceManager = new PriceManager();
		futurePriceManager = new FuturePriceManager();
		otaManager = new OtaManager();
		customerManager = new CustomerManager();
		currencyManager = new CurrencyManager();
	}
	
	public List<Price> weaving(int mode, Query query){
		
		dateIn = query.getDateIn();
		dateOut = query.getDateOut();
		
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
			p = crawlPrice(ota.getId(),query);
			addPriceToResponse(p);
		}
		
		return prices;
	}
	
	public List<FuturePrice> weavingFutureDays(int mode, Query query, int days){
		
		dateIn = query.getDateIn();
		dateOut = query.getDateOut();
		
		futurePrices = new ArrayList<FuturePrice>();
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
				
		ExecutorService executor = Executors.newFixedThreadPool(otas.size());
		
		for(Ota ota : otas){
			
			final Query queryParameter = query;
			final Hotel hotelParameter = hotel;
			final Ota otaParameter = ota;
			final int daysParameter = days;
			
			Runnable run = new Runnable() { public void run() { 
				
				FuturePrice fp = new FuturePrice();
				Price p = new Price();
				
				p.setHash(queryParameter.toHash(hotelParameter.getId(), otaParameter.getId()));
				p = crawlFuturePrice(otaParameter.getId(),queryParameter);
								
				fp = priceToFuturePrice(p, daysParameter, queryParameter.getCurrency());
								
				futurePriceManager.add(fp);
				
				addFuturePriceToResponse(fp);
				
			} };
			executor.execute(run);
			
		}
		executor.shutdown();
        while (!executor.isTerminated()) {
        	// Espero a que terminen de ejecutarse todos los procesos 
        	// para pasar a las siguientes instrucciones 
        }
        		
		return futurePrices;

	}
	
	private void addPriceToResponse(Price price){
		prices.add(price);
	}
	
	private void addFuturePriceToResponse(FuturePrice price){
		futurePrices.add(price);
	}
	
	private Price crawlPrice(int otaId, Query query){
		Price p = new Price();
		
		long diffDays = Utils.diffDays(query.getDateIn(), query.getDateOut());
		
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
			query.setDateIn(dateIn);
			query.setDateOut(dateOut);
			p = es.setServiceParameters(query).trackPrice();
			break;
		case 2:
			BookingService be = new BookingService();
			query.setDateIn(dateIn);
			query.setDateOut(dateOut);
			p = be.setServiceParameters(query).trackPrice();
			break;
		
		case 3:
			HotelsService he = new HotelsService();
			query.setDateIn(dateIn);
			query.setDateOut(dateOut);
			p = he.setServiceParameters(query).trackPrice();
			break;
		
		case 4:
			VenereService ve = new VenereService();
			query.setDateIn(dateIn);
			query.setDateOut(dateOut);
			p = ve.setServiceParameters(query).trackPrice();
			break;
			
		case 5:
			HRSService hrse = new HRSService();
			query.setDateIn(dateIn);
			query.setDateOut(dateOut);
			p = hrse.setServiceParameters(query).trackPrice();
			break;
			
		}
		p.setBackend(Integer.valueOf(query.getCustomerId()));
		p.setSite(otaManager.get(o).getIcon());
		p.setBasePrice(query.getBase());
		p.setDateIn(dateIn);
		p.setDateOut(dateOut);
		if ((query.getCurrencyTemp() != null) && !(query.getCurrencyTemp().equals(""))){
			p.setCurrency(query.getCurrencyTemp());
		} else {
			p.setCurrency(query.getCurrency());
		}
		if((d.getRatePerNightExpedia() == true) && (diffDays > 1)){
			p.setPrice(String.valueOf(Float.valueOf(p.getPrice()) * diffDays));
		}
		if (((query.getConverted() == false) && (p.getOtaId() != 5)) || ((query.getConvertedHrs() == false) && (p.getOtaId() == 5))){
			priceManager.add(p);
		}
		
		return p;
	}
	
	private Price crawlFuturePrice(int otaId, Query query){
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
			query.setDateIn(dateIn);
			query.setDateOut(dateOut);
			p = es.setServiceParameters(query).trackPrice();
			break;
		case 2:
			BookingService be = new BookingService();
			query.setDateIn(dateIn);
			query.setDateOut(dateOut);
			p = be.setServiceParameters(query).trackPrice();
			break;
		
		case 3:
			HotelsService he = new HotelsService();
			query.setDateIn(dateIn);
			query.setDateOut(dateOut);
			p = he.setServiceParameters(query).trackPrice();
			break;
		
		case 4:
			VenereService ve = new VenereService();
			query.setDateIn(dateIn);
			query.setDateOut(dateOut);
			p = ve.setServiceParameters(query).trackPrice();
			break;
		
		}
		p.setBackend(Integer.valueOf(query.getCustomerId()));
		p.setSite(otaManager.get(o).getIcon());
		p.setBasePrice(query.getBase());
		p.setDateIn(dateIn);
		p.setDateOut(dateOut);
	
		return p;
	}
	
	private FuturePrice priceToFuturePrice(Price p, int days, String currency){
		
		FuturePrice fp = new FuturePrice();
		
		fp.setHotelId(p.getHotelId());
		fp.setLanguage(p.getLanguage());
		fp.setDateIn(p.getDateIn());
		fp.setDateOut(p.getDateOut());
		fp.setGuests(p.getGuests());
		fp.setRooms(p.getRooms());
		fp.setOtaId(p.getOtaId());
		fp.setPrice(p.getPrice());
		fp.setPurePrice(p.getPurePrice());
		fp.setBasePrice(p.getBasePrice());
		fp.setAveragePrice(String.valueOf((Float.valueOf(p.getPrice()) / days)));
		fp.setCurrency(currency);
		fp.setDays(days);
		fp.setHash(p.getHash());
		
		return fp;
		
	}
	
	
}
