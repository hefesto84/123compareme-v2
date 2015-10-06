package es.ubiqua.compareme;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;

import es.ubiqua.compareme.manager.CustomerManager;
import es.ubiqua.compareme.manager.HotelManager;
import es.ubiqua.compareme.manager.HotelOtaManager;
import es.ubiqua.compareme.manager.OtaManager;
import es.ubiqua.compareme.manager.PriceManager;
import es.ubiqua.compareme.model.Customer;
import es.ubiqua.compareme.model.Hotel;
import es.ubiqua.compareme.model.HotelOta;
import es.ubiqua.compareme.model.Ota;
import es.ubiqua.compareme.model.Price;
import es.ubiqua.compareme.service.booking.BookingService;
import es.ubiqua.compareme.service.expedia.ExpediaService;
import es.ubiqua.compareme.service.hotels.HotelsService;
import es.ubiqua.compareme.service.venere.VenereService;
import junit.framework.TestCase;

public class CrawlerServiceTest extends TestCase{
	
	/*
	public void testCrawlerService() throws Exception{
		
		ExpediaService es = new ExpediaService();
		String result = es.setServiceParameters("es", "Nice-Hoteles-Park-Inn-By-Radisson-Nice-Airport-Hotel.h889870", 2,1, "08/10/2015", "10/10/2015").trackPrice().toString();
		System.out.println(result);
		
		BookingService be = new BookingService();
		String result2 = be.setServiceParameters("it", "hotel/fr/park-inn-nice", 2, 1, "08/10/2015", "10/10/2015").trackPrice().toString();
		System.out.println(result2);
		
		HotelsService he = new HotelsService();
		String result3 = he.setServiceParameters("de", "hotel/details.html?tab=description&destinationId=494528&destination=Niza%2C+Francia&hotelId=149150", 2, 1, "08/10/2015", "10/10/2015").trackPrice().toString();
		System.out.println(result3); 
		
		VenereService ve = new VenereService();
		String result4 = ve.setServiceParameters("de", "hotel/details.html?tab=description&destinationId=494528&destination=Niza%2C+Francia&hotelId=149150", 2, 1, "08/10/2015", "10/10/2015").trackPrice().toString();
		System.out.println(result4); 
		
	}
	
	
	public void testExpediaService() throws Exception{
		ExpediaService es = new ExpediaService();
		Price p = es.setServiceParameters("es", "Nice-Hoteles-Park-Inn-By-Radisson-Nice-Airport-Hotel.h889870", 2,1, "08/10/2015", "10/10/2015").trackPrice();
		assertEquals(true,(p.getPrice().length()>0));
	}
	
	public void testBookingService() throws Exception{
		BookingService be = new BookingService();
		Price p = be.setServiceParameters("it", "hotel/fr/park-inn-nice", 2, 1, "08/10/2015", "10/10/2015").trackPrice();
		assertEquals(true,(p.getPrice().length()>0));
	}
	
	public void testHotelsService() throws Exception{
		HotelsService he = new HotelsService();
		Price p = he.setServiceParameters("de", "hotel/details.html?tab=description&destinationId=494528&destination=Niza%2C+Francia&hotelId=149150", 2, 1, "08/10/2015", "10/10/2015").trackPrice();
		assertEquals(true,(p.getPrice().length()>0));
	}
	
	public void testVenereService() throws Exception{
		VenereService ve = new VenereService();
		Price p = ve.setServiceParameters("de", "hotel/details.html?tab=description&destinationId=494528&destination=Niza%2C+Francia&hotelId=149150", 2, 1, "08/10/2015", "10/10/2015").trackPrice();
		assertEquals(true,(p.getPrice().length()>0));
	}
	*/
	
	public void testPrice() throws Exception{
		/*
		PriceManager priceManager = new PriceManager();
		Price p = new Price();
		
		p.setHotelId(1);
		p.setDateIn("2015-10-10");
		p.setDateOut("2015-10-12");
		p.setGuests(1);
		p.setRooms(1);
		p.setLanguage("es");
		p.setOtaId(2);
		p.setPrice("123 €");
		p.setValoration(97);
		p.setBasePrice("122 €");
		p.setHash(p.toHash());
		
		//p = priceManager.getByHash(p);
		System.out.println(priceManager.list().size());
		*/
	}
	
	public void testHotel() throws Exception{
		/*
		HotelManager hotelManager = new HotelManager();
		Hotel hotel = new Hotel();
		hotel.setCustomerId(100);
		hotel.setName("NH Calderón");
		hotel = hotelManager.add(hotel);
		*/
	}

	public void testCustomer() throws Exception{
		/*
		CustomerManager customerManager = new CustomerManager();
		Customer customer = new Customer();
		customer.setIdentifier("GA-31242142");
		customer.setName("Ubiqua");
		customer.setUsername("ubiqua");
		customer.setPassword("ubiqua123");
		customer = customerManager.add(customer);
		*/
	}
	
	public void testHotelOta() throws Exception{
		/*
		HotelOtaManager hotelOtaManager = new HotelOtaManager();
		HotelOta hotelOta1 = new HotelOta();
		HotelOta hotelOta2 = new HotelOta();
		HotelOta hotelOta3 = new HotelOta();
		hotelOta1.setIdHotel(1);
		hotelOta1.setIdOta(1);
		hotelOta1.setName("NHCALDERON");
		
		hotelOtaManager.add(hotelOta1);
		
		hotelOta1.setIdOta(2);
		hotelOta1.setName("NH-CALDERON");
		
		hotelOtaManager.add(hotelOta1);
		*/
	}
	
	public void testOta() throws Exception{
		/*
		Ota ota = new Ota();
		ota.setName("Booking");
		ota = new OtaManager().get(ota);
		Hotel hotel = new Hotel();
		hotel.setName("NH Calderón");
		hotel = new HotelManager().get(hotel);
		HotelOtaManager manager = new HotelOtaManager();
		HotelOta p = manager.get(hotel, ota);
		System.out.println(p.getName());
		
		OtaManager otaManager = new OtaManager();
		Ota ota = new Ota();
		ota.setName("Expedia");
		ota.setIcon("expedia.png");
		otaManager.add(ota);
		ota.setName("Booking");
		ota.setIcon("booking.png");
		otaManager.add(ota);
		ota.setName("Hotels");
		ota.setIcon("hotels.png");
		otaManager.add(ota);
		ota.setName("Venere");
		ota.setIcon("venere.png");
		otaManager.add(ota);
		*/
		//assertEquals(true, otaManager.add(ota).getId()!=0);
		//assertEquals(true,otaManager.get(ota).getName().equals("Expedia"));
		
	}
	
	/*
	public void testExpedia() throws Exception{
		ExpediaService es = new ExpediaService();
		Price p = es.setServiceParameters("es", "Park Inn By Radisson Nice Airport", 2,1, "08/10/2015", "10/10/2015").trackPrice();
		new PriceManager().add(p);
		BookingService be = new BookingService();
		p = be.setServiceParameters("es", "Park Inn By Radisson Nice Airport", 2,1, "08/10/2015", "10/10/2015").trackPrice();
		new PriceManager().add(p);
		HotelsService he = new HotelsService();
		p = he.setServiceParameters("es", "Park Inn By Radisson Nice Airport", 2,1, "08/10/2015", "10/10/2015").trackPrice();
		new PriceManager().add(p);
		VenereService ve = new VenereService();
		p = ve.setServiceParameters("es", "Park Inn By Radisson Nice Airport", 2,1, "08/10/2015", "10/10/2015").trackPrice();
		new PriceManager().add(p);
	}
	
	*/
	
	/*
	public void testMultithreading() throws Exception{
		ExecutorService es = Executors.newCachedThreadPool();
		
		es.execute(new Runnable(){

			public void run() {
				ExpediaService es = new ExpediaService();
				Price p = es.setServiceParameters("es", "Park Inn By Radisson Nice Airport", 2,1, "08/10/2015", "10/10/2015").trackPrice();
				new PriceManager().add(p);
			}
			
		});
		
		es.execute(new Runnable(){

			public void run() {
				BookingService be = new BookingService();
				Price p = be.setServiceParameters("es", "Park Inn By Radisson Nice Airport", 2,1, "08/10/2015", "10/10/2015").trackPrice();
				new PriceManager().add(p);
			}
			
		});
		
		es.execute(new Runnable(){

			public void run() {
				HotelsService he = new HotelsService();
				Price p = he.setServiceParameters("es", "Park Inn By Radisson Nice Airport", 2,1, "08/10/2015", "10/10/2015").trackPrice();
				new PriceManager().add(p);
			}
					
		});
		
		es.execute(new Runnable(){

			public void run() {
				VenereService ve = new VenereService();
				Price p = ve.setServiceParameters("es", "Park Inn By Radisson Nice Airport", 2,1, "08/10/2015", "10/10/2015").trackPrice();
				new PriceManager().add(p);
			}
			
		});
		
		es.shutdown();
		boolean finished = es.awaitTermination(30, TimeUnit.SECONDS);
	}
	*/
}
