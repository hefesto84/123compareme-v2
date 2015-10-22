package es.ubiqua.compareme;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import es.ubiqua.compareme.model.Price;
import es.ubiqua.compareme.model.Query;
import es.ubiqua.compareme.service.crawler.CrawlingService;
import junit.framework.TestCase;

public class CrawlerServiceTest  extends TestCase{
	
	 private List<Price> datos = new ArrayList<Price>();
	 
	 public void testMail() throws Exception {
		
		 /*
		 Query query = new Query("es","NH Madrid Atocha",1,2,"08/02/2016","11/02/2016","179,10");
	        CrawlingService service = new CrawlingService();
	        datos = service.weaving(CrawlingService.MONOTHREAD_MODE, query);
	        
	        System.out.println(new Gson().toJson(datos));
	 	*/
	 }

}
