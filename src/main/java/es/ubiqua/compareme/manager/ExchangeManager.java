package es.ubiqua.compareme.manager;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.SAXException;

import com.google.gson.Gson;

import es.ubiqua.compareme.dao.CurrencyDAO;
import es.ubiqua.compareme.dao.ExchangeDAO;
import es.ubiqua.compareme.dao.OtaDAO;
import es.ubiqua.compareme.exceptions.CurrencyException;
import es.ubiqua.compareme.exceptions.OtaException;
import es.ubiqua.compareme.model.Currency;
import es.ubiqua.compareme.model.Exchange;
import es.ubiqua.compareme.model.Ota;
import es.ubiqua.compareme.utils.DBLogger;

public class ExchangeManager {
	
	private ExchangeDAO exchangeDao = new ExchangeDAO();
	
	public ExchangeManager(){
		
	}
	
	public List<Exchange> list(){
		return exchangeDao.list();
	}

	public Exchange get(Exchange exchange) throws CurrencyException{
		return exchangeDao.get(exchange);
	}

	public Exchange add(Exchange exchange) throws CurrencyException{
		return exchangeDao.add(exchange);
	}
	
	public void del(Exchange exchange) throws CurrencyException{
		exchangeDao.del(exchange);
	}
	
	public Exchange restrictive(Exchange exchange) throws CurrencyException{
		return exchangeDao.restrictive(exchange);
	}
	
	public boolean isCurrencyRestrictive(String currency){
		Exchange e = exchangeDao.restrictive(new Exchange(currency,0,""));
		if(e==null){
			return false;
		}
		return true;
	}
	
	public boolean isCurrencyRestrictiveHrs(String currency){
		Exchange e = exchangeDao.restrictiveHrs(new Exchange(currency,0,""));
		if(e==null){
			return false;
		}
		return true;
	}
	
	public boolean isCurrencyAvailable(String currency){
		Exchange e = exchangeDao.get(new Exchange(currency,0,""));
		if(e==null){
			return false;
		}
		return true;
	}
	
	public float change(float value, String to){
		Exchange e = exchangeDao.get(new Exchange(to,value,""));
		if(e!=null){
			value = value*e.getValue();
			String v = String.format(Locale.ENGLISH,"%.2f", value);
			return Float.valueOf(v);
		}else{
			DBLogger.getLogger().Error("Bad conversion: "+value+" TO : "+to);
			return value;
		}
	}
	
	public boolean update(){
		try{
			Document list = Jsoup.connect("http://www.xe.com/currency/").userAgent(String.valueOf(Calendar.getInstance().getTimeInMillis())).get();
			 Elements currencies = list.select("ul#popCurr").select("a");
			
			 for(Element e : currencies){
				 String currencyISO = e.html().substring(0,3);
				 String currencyName = e.html().substring(6);
				 
				 Document value = Jsoup.connect("http://www.xe.com/currencyconverter/convert/?Amount=1&From=EUR&To="+currencyISO).userAgent(String.valueOf(Calendar.getInstance().getTimeInMillis())).get();
				 Elements v = value.select("tr.uccRes").select("td.rightCol");
				 float currencyValue = Float.valueOf(v.html().substring(0, v.html().indexOf("&")).replace(",", "")).floatValue();
				 add(new Exchange(currencyISO,currencyValue,currencyName));
			 }
		}catch(Exception e){
			return false;
		}
		return true;
	}
}
