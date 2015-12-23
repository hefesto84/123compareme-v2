package es.ubiqua.compareme;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.text.DecimalFormat;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.frozenbullets.api.currencyconverter.CurrencyConverter;
import com.google.gson.Gson;

import es.ubiqua.compareme.manager.CurrencyManager;
import es.ubiqua.compareme.manager.CustomerManager;
import es.ubiqua.compareme.manager.DomainManager;
import es.ubiqua.compareme.manager.ExchangeManager;
import es.ubiqua.compareme.manager.OtaManager;
import es.ubiqua.compareme.model.Currency;
import es.ubiqua.compareme.model.Customer;
import es.ubiqua.compareme.model.Domain;
import es.ubiqua.compareme.model.Exchange;
import es.ubiqua.compareme.model.Ota;
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
		 
		 ExchangeManager em = new ExchangeManager();
		 //Document d = Jsoup.connect("http://www.xe.com/currencyconverter/convert/?Amount=1&From=EUR&To=NZD").userAgent(String.valueOf(Calendar.getInstance().getTimeInMillis())).get();
		 
		 //Elements e = d.select("tr.uccRes").select("td.rightCol");
		 //System.out.println(e.html().substring(0, e.html().indexOf("&")));
		
		 Document list = Jsoup.connect("http://www.xe.com/currency/").userAgent(String.valueOf(Calendar.getInstance().getTimeInMillis())).get();
		 Elements currencies = list.select("ul#popCurr").select("a");
		
		 for(Element e : currencies){
			 String currencyISO = e.html().substring(0,3);
			 String currencyName = e.html().substring(6);
			 
			 Document value = Jsoup.connect("http://www.xe.com/currencyconverter/convert/?Amount=1&From=EUR&To="+currencyISO).userAgent(String.valueOf(Calendar.getInstance().getTimeInMillis())).get();
			 Elements v = value.select("tr.uccRes").select("td.rightCol");
			 float currencyValue = Float.valueOf(v.html().substring(0, v.html().indexOf("&")).replace(",", "")).floatValue();
			 System.out.println(currencyISO+"|"+currencyName+"|"+v.html().substring(0, v.html().indexOf("&")));
			 em.add(new Exchange(currencyISO,currencyValue,currencyName));
		 }
		 /*
		 Query query = new Query("10000","es","Park Inn By Radisson Nice Airport",1,2,"06/08/2016","10/08/2016","179,10","EUR");

	        CrawlingService service = new CrawlingService();
	        datos = service.weaving(CrawlingService.MONOTHREAD_MODE, query);
	        
	        System.out.println(new Gson().toJson(datos));
		
		 
		 Exchange e = new Exchange("EUR",1);
		 Exchange e1 = new Exchange("GBP", 1.6f);
		 
		 ExchangeManager em = new ExchangeManager();
		 em.add(e1);
		 em.add(e);
		
		 
		 //em.update();
		 List<String> c = new ArrayList<String>();
		 for(Exchange e : em.list()){
			 c.add(e.getCurrency());
		 }
		 System.out.println(new Gson().toJson(c));
		  */
	 }
}
