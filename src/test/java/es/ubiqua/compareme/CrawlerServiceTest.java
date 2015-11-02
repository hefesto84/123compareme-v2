package es.ubiqua.compareme;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.text.DecimalFormat;

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
	
	public float change(String val){
		val = val.replaceAll("[^\\d\\.\\,+]", "").replace(".", "#").replace(",", "#");
		val = val.replaceAll("(#[0-9][0-9])$", "."+val.substring(val.length()-2, val.length())).replace("#", "");
		return Float.parseFloat(val);
	}
	
	 public void testMail() throws Exception {
		
		 /*
		 String price1 = "€ 13,060.50";
		 String price2 = "1 060";
		 String price3 = "1 060 €";
		 String price4 = "1,060 €";
		 String price5 = "1.060 €";
		 String price6 = "1.060,40 €";
		 String price7 = "1,060.18 €";
		 String price8 = "1 060.31 €";
		 String price9 = "1 060,03 €";
		 
		 System.out.println(change(price1));
		 System.out.println(change(price2));
		 System.out.println(change(price3));
		 System.out.println(change(price4));
		 System.out.println(change(price5));
		 System.out.println(change(price6));
		 System.out.println(change(price7));
		 System.out.println(change(price8));
		 System.out.println(change(price9));
		
		
		  
		 NumberFormat nf = NumberFormat.getNumberInstance(Locale.GERMANY);
		 ((DecimalFormat)nf).setParseBigDecimal(true);
		 
		 
		 System.out.println("PRICE: "+(BigDecimal)nf.parse(price));
		 Price p = new Price();
		 p.setPrice(price);
		
		 System.out.println(Utils.changeCurrency(p.getPrice(), "EUR", "EUR"));
		*/
		 
		 
		 Query query = new Query("es","The May Fair Hotel",1,2,"06/08/2016","10/08/2016","179,10");
	        CrawlingService service = new CrawlingService();
	        datos = service.weaving(CrawlingService.MONOTHREAD_MODE, query);
	        
	        System.out.println(new Gson().toJson(datos));
	 	
		 
		 // data = Utils.searchHotelIdentifiers("Radisson Blu Edwardian Bloomsbury Street");
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
