package es.ubiqua.compareme.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.ibatis.io.Resources;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.SAXException;

import com.google.gson.Gson;
import com.tunyk.currencyconverter.BankUaCom;
import com.tunyk.currencyconverter.api.Currency;
import com.tunyk.currencyconverter.api.CurrencyConverter;
import com.tunyk.currencyconverter.api.CurrencyConverterException;
import com.tunyk.currencyconverter.api.CurrencyNotSupportedException;

import es.ubiqua.compareme.model.Ota;
import es.ubiqua.compareme.model.Price;

public class Utils {
	
	private static Pattern patternDomainName;
	  private static Matcher matcher;
	  private static final String DOMAIN_NAME_PATTERN 
		= "([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}";
	  static {
		patternDomainName = Pattern.compile(DOMAIN_NAME_PATTERN);
	  }
	  
	public static String sanitizeDateForBooking(String date){	
		String output = "";	
		if(date.contains("/")){
			output = date.substring(6) + "-" + date.substring(3,5)+"-"+ date.substring(0,2);
		}
		return output;
	}
	
	public static String sanitizeDateForHotels(String date){
		String output = "";
		if(date.contains("/")){
			output = date.substring(0,2) + "-" + date.substring(3,5) + "-" + date.substring(6);
		}
		return output;
	}
	
	public static String compute(String string){
		try {
			return String.format("%032X",new BigInteger(1,MessageDigest.getInstance("MD5").digest(string.getBytes())));
		} catch (NoSuchAlgorithmException e) {
			Logger.getLogger(Utils.class).error("Error creating hash for price");
			return "null";
		}
	}
	
	public static void checkCoherence(String customerId, List<Price> prices){
		
		for(Price p : prices){
			float bp = Float.valueOf(p.getBasePrice()).floatValue();
			float op = Float.valueOf(p.getPrice()).floatValue();
			
			if ( bp > op ){
				DBLogger.getLogger().Warning("OTA cheaper than hotel: "+p.toDBLogger());
				DBLogger.getLogger().Critical(customerId,"OTA cheaper than hotel: "+p.toDBLogger());
			}else if (op > bp + 5){
					DBLogger.getLogger().Warning("Suspicious OTA price: "+p.toDBLogger());
					DBLogger.getLogger().Critical(customerId,"Suspicious OTA price: "+p.toDBLogger());
			}else{
				DBLogger.getLogger().Info(" OTA price: "+p.toDBLogger());
			}
		}
	}
	
	public static String getIP(){
		try{
			 URL whatismyip = new URL("http://checkip.amazonaws.com");
			 BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
			 String ip = in.readLine(); //you get the IP as a String
			 return ip;
		}catch(Exception e){
			return "0.0.0.0";
		}
	}
	
	public static String createOtaStatusJavascriptContent(List<Ota> otas){
		
		String content = "";
		return content;
	}
	
	public static List<String> LoadCurrencies(){
		List<String> currencies = new ArrayList<String>();
		for(Currency c : Currency.values()){
			currencies.add(c.name());
		}
		return currencies;
	}
	
	public static String changeCurrency2(String price, String currencyFrom, String currencyTo){
		//returcom.frozenbullets.api.currencyconverter.CurrencyConverter.getInstance().convertCurrency(10.0f, "EUR", "USD");
		return price;
	}
	
	/*
	public static String changeCurrency(String price, String currencyFrom, String currencyTo){
		
		if(currencyFrom.equals(currencyTo)){
			return price;
		}
		
		try {
			CurrencyConverter currencyConverter = new BankUaCom(Currency.fromString(currencyFrom), Currency.fromString(currencyTo));
			return String.valueOf(currencyConverter.convertCurrency(Float.valueOf(price)));
		} catch (CurrencyNotSupportedException e) {
			price = "0";
			e.printStackTrace();
		} catch (CurrencyConverterException e) {
		   price = "0";
			e.printStackTrace();
		}
		return price;
	
	}
	*/
	 public static String getDomainName(String url){
			
			String domainName = "";
			matcher = patternDomainName.matcher(url);
			if (matcher.find()) {
				domainName = matcher.group(0).toLowerCase().trim();
			}
			return domainName;
				
		  }
	 
	 public static float change(String val){
			val = val.replaceAll("[^\\d\\.\\,+]", "").replace(".", "#").replace(",", "#");
			val = val.replaceAll("(#[0-9][0-9])$", "."+val.substring(val.length()-2, val.length())).replace("#", "");
			System.out.println(" NON PARSED: "+val);
			return Float.parseFloat(val);
	}
	 
	public static Map<String,String> searchHotelIdentifiers(String hotelName){
		
		hotelName = hotelName.replaceAll(" ", "+");
		String result = "";
		Map<String,String> data = new HashMap<String,String>();
		
		// EXPEDIA
		
		
		try {

			String request = "https://www.google.es/search?q="+hotelName+"+site:expedia.es&num=20";
			Document doc = Jsoup.connect(request)
				.userAgent(
				  "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)")
				.timeout(5000).get();

				Elements links = doc.select("a[href]");
				for (Element link : links) {
	
					String temp = link.attr("href");		
					if(temp.startsWith("/url?q=")){
						String domain = getDomainName(temp);
						
						if(domain.contains("www.expedia.es")){
							
							temp = temp.substring(temp.indexOf("es/")+3,temp.lastIndexOf("."));
							data.put("www.expedia.com", temp);
							break;
						}
					}
	
				}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// BOOKING
		
		try {

			String request = "https://www.google.es/search?q="+hotelName+"+booking&num=20";
			Document doc = Jsoup.connect(request)
				.userAgent(
				  "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)")
				.timeout(5000).get();

			Elements links = doc.select("a[href]");
			for (Element link : links) {

				String temp = link.attr("href");		
				if(temp.startsWith("/url?q=")){
					String domain = getDomainName(temp);
					if(domain.contains("www.booking.com")){
						
						int from = temp.indexOf("com/")+4;
						temp = temp.substring(from);
						 int to = temp.indexOf(".");
						 
						data.put("www.booking.com", temp.substring(0,to));
					}
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// HOTELS & VENERE
		
		try {

			String request = "https://www.google.es/search?q="+hotelName+"++venere&num=20";
			Document doc = Jsoup.connect(request)
				.userAgent(
				  "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)")
				.timeout(5000).get();

			Elements links = doc.select("a[href]");
			for (Element link : links) {

				String temp = link.attr("href");		
				if(temp.startsWith("/url?q=")){
					String domain = getDomainName(temp);
					
					if(domain.contains("venere.com")){
						 int from = temp.indexOf("com/ho")+6;
						 temp = temp.substring(from);
						 int to = temp.indexOf("/");
						 data.put("www.venere.com", temp.substring(0,to));
						 data.put("www.hotels.com", temp.substring(0,to));
						break;
					}
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//result = new Gson().toJson(data);
		return data;
	}
}
