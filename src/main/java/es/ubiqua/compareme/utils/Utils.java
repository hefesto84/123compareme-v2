package es.ubiqua.compareme.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.log4j.Logger;

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
	
	public static void checkCoherence(List<Price> prices){
		
		for(Price p : prices){
			float bp = Float.valueOf(p.getBasePrice()).floatValue();
			float op = Float.valueOf(p.getPrice()).floatValue();
			
			if ( bp > op ){
				DBLogger.getLogger().Warning("OTA CHEAPER THAN HOTEL: "+p.toDBLogger());
			}else{
				if (op > bp + 5){
					DBLogger.getLogger().Warning("OTA PRICE IS SUSPICIOUS: "+p.toDBLogger());
				}
			}
		}
	}
}
