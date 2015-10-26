package es.ubiqua.compareme;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.tunyk.currencyconverter.api.Currency;

import es.ubiqua.compareme.manager.OtaManager;
import es.ubiqua.compareme.model.Price;
import es.ubiqua.compareme.model.Query;
import es.ubiqua.compareme.service.crawler.CrawlingService;
import es.ubiqua.compareme.utils.Utils;
import junit.framework.TestCase;

public class CrawlerServiceTest  extends TestCase{
	
	 private List<Price> datos = new ArrayList<Price>();
	 
	 public void testMail() throws Exception {
		
	/*
		 Query query = new Query("es","NH Barcelona Calderón",1,2,"22/10/2015","23/10/2015","179,10");
	        CrawlingService service = new CrawlingService();
	        datos = service.weaving(CrawlingService.MONOTHREAD_MODE, query);
	        
	        System.out.println(new Gson().toJson(datos));
	 	*/
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
	 }
}
