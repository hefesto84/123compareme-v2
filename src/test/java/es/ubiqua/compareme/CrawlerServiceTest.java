package es.ubiqua.compareme;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.tunyk.currencyconverter.api.Currency;

import es.ubiqua.compareme.manager.CustomerManager;
import es.ubiqua.compareme.manager.OtaManager;
import es.ubiqua.compareme.model.Customer;
import es.ubiqua.compareme.model.Price;
import es.ubiqua.compareme.model.Query;
import es.ubiqua.compareme.service.crawler.CrawlingService;
import es.ubiqua.compareme.utils.Utils;
import junit.framework.TestCase;

public class CrawlerServiceTest  extends TestCase{
	
	private List<Price> datos = new ArrayList<Price>();
	private Map<String,String> data = new HashMap<String,String>();
	
	 public void testMail() throws Exception {
		
	
		 /*
		 Query query = new Query("es","Hesperia Playa Dorada",1,2,"04/07/2016","05/07/2016","179,10");
	        CrawlingService service = new CrawlingService();
	        datos = service.weaving(CrawlingService.MONOTHREAD_MODE, query);
	        
	        System.out.println(new Gson().toJson(datos));
	 	*/
		 
		  data = Utils.searchHotelIdentifiers("Radisson Blu Edwardian Bloomsbury Street");
		 //System.out.println(result);
		 
	
		 
		 //String exp = "https://www.expedia.com/London-Hotels-Radisson-Blu-Edwardian-Bloomsbury-Street-Hotel.h19825.Hotel-Information";
		 //String book = "http://www.booking.com/hotel/gb/radissonedwardianmarlborough.es.html";

		 //System.out.println(exp.substring(exp.indexOf("com/")+4, exp.lastIndexOf(".")));
		 
		 //int from = book.indexOf("com/")+4;
		 //book = book.substring(from);
		 //int to = book.indexOf(".");
		 
		 //System.out.println(book.substring(0,to));
		// System.out.println(book.substring(from,to));
		//CustomerManager m = new CustomerManager();
	     // Customer c = new Customer();
	      //c.setUsername("nhhotels");
	      //c.setPassword("nhhotels");
	      //System.out.println(new Gson().toJson(m.login(c)));
	        //System.out.println(Utils.changeCurrency(datos.get(0).getPrice(), "EUR", "GBP"));
		//System.out.println(Utils.createOtaStatusJavascriptContent(new OtaManager().list()));
		 /*
		 if(Currency.AUD.toString().equals("AUD")){
			 System.out.println(Currency.AUD);
		 }else{
			 System.out.println("KO");
		 }
		*/
		 
		// String p = "100";
		// System.out.println(Utils.changeCurrency(p, "GBP","EUR"));
		 
		 /*
		 Document doc = Jsoup.connect("http://es.hoteles.com/hotel/details.html?tab=description&q-localised-check-in=29/03/2016&hotel-id=355619&q-room-0-adults=2&YGF=0&MGT=2&WOE=6&q-localised-check-out=30/03/2016&WOD=4&ZSX=0&SYE=3&q-room-0-children=0").get();
		 Elements e = doc.select("form");
		 Map<String,String> data = new HashMap<String,String>();
		 for(int i = 0; i<e.size(); i++){
			 if(e.get(i).hasAttr("id")){
				 if(e.get(i).attr("id").equals("room-1-rateplan-1")){
					 Elements fields = e.get(i).select("input");
					 for(int j = 0; j<fields.size(); j++){
						 data.put(fields.get(j).attr("name"), fields.get(j).attr("value"));
					 }
				 }
			 }
		 }
		 Document request = Jsoup.connect("https://ssl-fr.hotels.com/bookingInitialise.do").data(data).post();
		 Elements rq = request.select("strong[id=financial-details-total-price]");
		 System.out.println(rq.text());
		 */
	 }
}
