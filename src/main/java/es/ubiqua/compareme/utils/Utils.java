package es.ubiqua.compareme.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.log4j.Logger;

import com.tunyk.currencyconverter.BankUaCom;
import com.tunyk.currencyconverter.api.Currency;
import com.tunyk.currencyconverter.api.CurrencyConverter;
import com.tunyk.currencyconverter.api.CurrencyConverterException;
import com.tunyk.currencyconverter.api.CurrencyNotSupportedException;

import es.ubiqua.compareme.model.Ota;
import es.ubiqua.compareme.model.Price;

public class Utils {
	
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
		/*
		String head = "<script type=\"text/javascript\"> var barChartData = {";
		String otasContent  = " labels: [";
		for(Ota o : otas){
			otasContent = otasContent + "\""+o.getName()+"\",";
		}
		otasContent = otasContent.substring(0, otasContent.lastIndexOf(",")) + "],";
		String dataSetHeader =  "datasets : [{fillColor : \"rgba(220,220,220,0.5)\",strokeColor : \"rgba(220,220,220,0.8)\",highlightFill: \"rgba(220,220,220,0.75)\",highlightStroke: \"rgba(220,220,220,1)\",";
		String dataSetContent = " data : [";
		
		for(Ota o : otas){
			dataSetContent = dataSetContent + "\""+o.getQuality()+"\",";
		}
		dataSetContent = dataSetContent.substring(0, dataSetContent.lastIndexOf(",")) + "]}] }";
		String footer = "; window.onload = function(){var ctx = document.getElementById(\"canvas\").getContext(\"2d\");window.myBar = new Chart(ctx).Bar(barChartData, {responsive : true});}</script>";
		
		content = head + otasContent + dataSetHeader + dataSetContent + footer;
		*/
		return content;
	}
	
	public static String changeCurrency(String price, String currencyFrom, String currencyTo){
		System.out.println("CURRENCY FROM: "+currencyFrom);
		System.out.println("CURRENCY TO: "+currencyTo);
		try {
			CurrencyConverter currencyConverter = new BankUaCom(Currency.fromString(currencyFrom), Currency.fromString(currencyTo));
			return String.valueOf(currencyConverter.convertCurrency(Float.valueOf(price)));
		} catch (CurrencyNotSupportedException e) {
			price = "0";
			System.out.println("CURRENCY FROM: "+currencyFrom);
			e.printStackTrace();
		} catch (CurrencyConverterException e) {
		   price = "0";
			e.printStackTrace();
		}
		return price;
	
	}
}
