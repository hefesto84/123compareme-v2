package es.ubiqua.compareme;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.google.gson.Gson;

import es.ubiqua.compareme.manager.CustomerManager;
import es.ubiqua.compareme.manager.HotelManager;
import es.ubiqua.compareme.manager.HotelOtaManager;
import es.ubiqua.compareme.manager.MailManager;
import es.ubiqua.compareme.manager.OtaManager;
import es.ubiqua.compareme.manager.PriceManager;
import es.ubiqua.compareme.model.Customer;
import es.ubiqua.compareme.model.Hotel;
import es.ubiqua.compareme.model.HotelOta;
import es.ubiqua.compareme.model.Ota;
import es.ubiqua.compareme.model.Price;
import es.ubiqua.compareme.model.Query;
import es.ubiqua.compareme.service.booking.BookingService;
import es.ubiqua.compareme.service.crawler.CrawlingService;
import es.ubiqua.compareme.service.expedia.ExpediaService;
import es.ubiqua.compareme.service.hotels.HotelsService;
import es.ubiqua.compareme.service.venere.VenereService;
import es.ubiqua.compareme.utils.DBLogger;
import es.ubiqua.compareme.utils.Utils;
import junit.framework.TestCase;

public class CrawlerServiceTest  extends TestCase{
	
	 private List<Price> datos;
	 
	 public void testMail() throws Exception {
		/*
		 Query query = new Query("es","Park Inn By Radisson Nice Airport",1,2,"10/01/2016","14/01/2016","66,00");
	        CrawlingService service = new CrawlingService();
	        datos = service.weaving(CrawlingService.MONOTHREAD_MODE, query);
	        //Utils.checkCoherence("UA-41604199-10",datos);
	         * 
	         */
	 }

}
