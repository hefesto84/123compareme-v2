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
import es.ubiqua.compareme.utils.DBLogger;
import junit.framework.TestCase;

public class CrawlerServiceTest extends TestCase{

}
