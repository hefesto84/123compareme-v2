package es.ubiqua.compareme.cron;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.google.gson.Gson;

import es.ubiqua.compareme.manager.HotelsToCrawlManager;
import es.ubiqua.compareme.manager.SerhsManager;
import es.ubiqua.compareme.model.FuturePrice;
import es.ubiqua.compareme.model.HotelsToCrawl;
import es.ubiqua.compareme.model.Query;
import es.ubiqua.compareme.model.Serhs;
import es.ubiqua.compareme.model.SerhsFutureDays;
import es.ubiqua.compareme.service.crawler.CrawlingService;

public class cronSerhs implements Job {
	
	private List<FuturePrice> futureDatos;
	
	private String respuesta;
	private List<HotelsToCrawl> hotelsToCrawl;
	private HotelsToCrawlManager hotelsToCrawlManager;
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		hotelsToCrawlManager = new HotelsToCrawlManager();
		    	
    	hotelsToCrawl = hotelsToCrawlManager.listCrawlable();
    	
    	for( HotelsToCrawl hotels : hotelsToCrawl){
    		if (hotels.getCustomer() == 3){
    			try {
					crawlSerhs(hotels);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
		
	}
	
	private void crawlSerhs(HotelsToCrawl hotels) throws Exception{
		
		Serhs hotel = new Serhs();
		hotel.setId_hotel(hotels.getId_hotel());
		SerhsManager serhsManager = new SerhsManager();
		hotel = serhsManager.get(hotel);
		//int count = 0;
		float resultado = 0;
		SerhsFutureDays serhs = new SerhsFutureDays();
		
		serhs = obtenerDatos(hotel);
		
		if ((serhs.getBestprice().equals("")) || (serhs.getBestprice() == null)){
    		serhs.setBestprice("0.0");
    	} 
					
		resultado = Float.valueOf(serhs.getBestprice());
		
		/*do{
			
			serhs = obtenerDatos(hotel, count);
			//String json = "{'bestprice':'59.94','currency':'eur','dateFrom':'2016-03-10','dateTo':'2016-03-11','pax':'2','childs':'0'}";
			//String json = "{'bestprice':'','currency':'','dateFrom':'2016-02-05','dateTo':'2016-02-06','pax':'2','childs':'0'}";
			
			//serhs = new Gson().fromJson(json, SerhsFutureDays.class);
			
			if ((serhs.getBestprice().equals("")) || (serhs.getBestprice() == null)){
	    		serhs.setBestprice("0.0");
	    	} 
						
			resultado = Float.valueOf(serhs.getBestprice());
			count  = count + 1;
			
		} while ((count < 5) && (resultado == 0));*/
		
		if (resultado != 0){ 
			
			CrawlingService service = new CrawlingService();
			Query query = new Query(Integer.toString(hotels.getCustomer()),"es",hotels.getName_hotel(),1,2,formatDate(serhs.getDateFrom()),formatDate(serhs.getDateTo()),Float.toString(resultado),serhs.getCurrency());
	        futureDatos = service.weavingFutureDays(CrawlingService.MONOTHREAD_MODE, query, hotels.getDays());
		
		}
	}
	
	/*public SerhsFutureDays obtenerDatos(Serhs hotel,int count) throws Exception{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar fini = Calendar.getInstance();
		fini.add(Calendar.DATE, count);
		Calendar fout = Calendar.getInstance();
		fout.add(Calendar.DATE, count + 1);
		
		String url = "https://booking.serhshotels.com/"+hotel.getChannelkey()+"/hotel"+hotel.getHotelcode()+"/from"+dateFormat.format(fini.getTime())+"/to"+dateFormat.format(fout.getTime())+"/pax2/childs0/priceInfo";
		
		respuesta = readUrl(url);
				
    	Gson gson = new Gson();
    	SerhsFutureDays serhs = gson.fromJson(respuesta, SerhsFutureDays.class);
    	
    	return serhs;
	}*/
	
	public SerhsFutureDays obtenerDatos(Serhs hotel) throws Exception{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar fini = Calendar.getInstance();;
		Calendar fout = Calendar.getInstance();
		fout.add(Calendar.DATE, 1);
		
		String url = "https://booking.serhshotels.com/"+hotel.getChannelkey()+"/hotel"+hotel.getHotelcode()+"/from"+dateFormat.format(fini.getTime())+"/to"+dateFormat.format(fout.getTime())+"/pax2/childs0/priceInfo";
		
		respuesta = readUrl(url);
				
    	Gson gson = new Gson();
    	SerhsFutureDays serhs = gson.fromJson(respuesta, SerhsFutureDays.class);
    	
    	return serhs;
	}
	
	public String formatDate(String date){
		String conversion = date.substring(8, 10) + "/" + date.substring(5,7) + "/" + date.substring(0,4);
		return conversion;
	}
	
	
	private static String readUrl(String urlString) throws Exception {
	    BufferedReader reader = null;
	    try {
	        URL url = new URL(urlString);
	        reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        StringBuffer buffer = new StringBuffer();
	        int read;
	        char[] chars = new char[1024];
	        while ((read = reader.read(chars)) != -1)
	            buffer.append(chars, 0, read); 

	        return buffer.toString();
	    } finally {
	        if (reader != null)
	            reader.close();
	    }
	}


	public List<FuturePrice> getFutureDatos() {
		return futureDatos;
	}


	public void setFutureDatos(List<FuturePrice> futureDatos) {
		this.futureDatos = futureDatos;
	}
}
