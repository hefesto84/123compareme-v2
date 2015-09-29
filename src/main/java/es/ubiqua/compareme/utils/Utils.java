package es.ubiqua.compareme.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

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
}
