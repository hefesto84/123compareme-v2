package es.ubiqua.compareme.actions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

import es.ubiqua.compareme.manager.HotelsToCrawlManager;
import es.ubiqua.compareme.manager.SerhsManager;
import es.ubiqua.compareme.model.FuturePrice;
import es.ubiqua.compareme.model.HotelsToCrawl;
import es.ubiqua.compareme.model.Query;
import es.ubiqua.compareme.model.Serhs;
import es.ubiqua.compareme.model.SerhsFutureDays;
import es.ubiqua.compareme.service.crawler.CrawlingService;

public class GetFuturePricesAction extends ActionSupport {

	private static final long serialVersionUID = -2527009795402427983L;

	private List<FuturePrice> futureDatos;
	
	private String respuesta;
	private List<HotelsToCrawl> hotelsToCrawl;
	private HotelsToCrawlManager hotelsToCrawlManager;

    public String execute() throws Exception {
    	
    	//long init = System.currentTimeMillis();
    	
    	hotelsToCrawlManager = new HotelsToCrawlManager();
    	
    	hotelsToCrawl = hotelsToCrawlManager.listCrawlable();
    	
    	for( HotelsToCrawl hotels : hotelsToCrawl){
    		if (hotels.getCustomer() == 3){
    			crawlSerhs(hotels);
    		}
    		
    	}
    	
    	//long fin = System.currentTimeMillis();	// Instante final del procesamiento
        //System.out.println("Tiempo total de procesamiento: "+(fin-init)/1000+" Segundos");
     
        return SUCCESS;
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


	public String getRespuesta() {
		return respuesta;
	}


	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}


	public List<HotelsToCrawl> getHotelsToCrawl() {
		return hotelsToCrawl;
	}


	public void setHotelsToCrawl(List<HotelsToCrawl> hotelsToCrawl) {
		this.hotelsToCrawl = hotelsToCrawl;
	}
	
	private void crawlSerhs(HotelsToCrawl hotels) throws Exception{
		
		Serhs hotel = new Serhs();
		hotel.setId_hotel(hotels.getId_hotel());
		SerhsManager serhsManager = new SerhsManager();
		hotel = serhsManager.get(hotel);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar fini = Calendar.getInstance();
		Calendar fout = Calendar.getInstance();
		fout.add(Calendar.DATE, hotels.getDays());
		
		String url = "https://booking.serhshotels.com/"+hotel.getChannelkey()+"/hotel"+hotel.getHotelcode()+"/from"+dateFormat.format(fini.getTime())+"/to"+dateFormat.format(fout.getTime())+"/pax2/childs0/priceInfo";
		
		respuesta = readUrl(url);
				
    	Gson gson = new Gson();
    	SerhsFutureDays serhs = gson.fromJson(respuesta, SerhsFutureDays.class);
    	
    	if ((serhs.getBestprice().equals("")) || (serhs.getBestprice() == null)){
    		serhs.setBestprice("0.0");
    	} 
    	
    	Float price = Float.valueOf(serhs.getBestprice()) / hotels.getDays();
    	
    	DateFormat dateFormatCrawler = new SimpleDateFormat("dd/MM/yyyy");
		    		
		CrawlingService service = new CrawlingService();
		Query query = new Query(Integer.toString(hotels.getCustomer()),"es",hotels.getName_hotel(),1,2,dateFormatCrawler.format(fini.getTime()),dateFormatCrawler.format(fout.getTime()),Float.toString(price),serhs.getCurrency());

        setFutureDatos(service.weavingFutureDays(CrawlingService.MONOTHREAD_MODE, query, hotels.getDays()));
		
	}

	public List<FuturePrice> getFutureDatos() {
		return futureDatos;
	}

	public void setFutureDatos(List<FuturePrice> futureDatos) {
		this.futureDatos = futureDatos;
	}
	
}
