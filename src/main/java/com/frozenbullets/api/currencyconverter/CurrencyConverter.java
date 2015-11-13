package com.frozenbullets.api.currencyconverter;

import java.io.File;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.ibatis.io.Resources;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.gson.Gson;

import uk.ltd.getahead.dwr.util.Logger;

public class CurrencyConverter {
	
	private static CurrencyConverter INSTANCE = null;
	private File fXmlFile;
	private DocumentBuilderFactory dbFactory;
	private DocumentBuilder dBuilder;
	private Document doc;
	private HashMap<String, Float> currencyMap;
	
	private CurrencyConverter(){
		try{
			
			fXmlFile = Resources.getResourceAsFile("eurofxref-daily.xml");
			dbFactory = DocumentBuilderFactory.newInstance();
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(fXmlFile);
			
			currencyMap = new HashMap<String,Float>();
			currencyMap.put("EUR", 1f);
			
			NodeList nl = doc.getElementsByTagName("Cube");
			for(int i = 0; i<nl.getLength(); i++){
				if(nl.item(i).getNodeType()==Node.ELEMENT_NODE && nl.item(i).hasAttributes()){
					Element e = (Element) nl.item(i);
					if(e.hasAttribute("currency")){
						String currency = e.getAttribute("currency");
						String rate = e.getAttribute("rate");
						currencyMap.put(currency, Float.valueOf(rate));
						System.out.println(new Gson().toJson(currencyMap));
					}
				}
			}
			
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error("Error parsing currency");
		}
	}
	
	public static CurrencyConverter getInstance(){
		if(INSTANCE==null){
			synchronized (CurrencyConverter.class) {
				if(INSTANCE==null){
					INSTANCE = new CurrencyConverter();
				}
			}
		}
		return INSTANCE;
	}
	
	@Override
	public CurrencyConverter clone() throws CloneNotSupportedException {
    	throw new CloneNotSupportedException(); 
	}
	
	public String convertCurrency(float price, String to){
		float v = price* currencyMap.get(to);
		return String.format("%.2f", v);
		//return String.valueOf(price* currencyMap.get(to));
	}
	
	public String convertCurrency(String price, String to){
		float v = Float.valueOf(price) * currencyMap.get(to);
		return String.format("%.2f", v);
	}
	
	public String convertCurrency(String price, String from, String to){
		float v = Float.valueOf(price) * currencyMap.get(to);
		return String.format("%.2f", v);
	}
	
	public String convertCurrency(float price, String from, String to){
		float v = price * currencyMap.get(to);
		return String.format("%.2f", v);
	}
}
