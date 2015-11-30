package es.ubiqua.compareme.javascripts.actions;

import java.net.URLEncoder;
import static org.apache.commons.lang3.StringEscapeUtils.escapeHtml4;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.util.URIUtil;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

import es.ubiqua.compareme.exceptions.CustomerException;
import es.ubiqua.compareme.exceptions.ExpediaServiceException;
import es.ubiqua.compareme.manager.HotelManager;
import es.ubiqua.compareme.manager.PriceManager;
import es.ubiqua.compareme.manager.TestManager;
import es.ubiqua.compareme.model.Hotel;
import es.ubiqua.compareme.model.Price;
import es.ubiqua.compareme.model.Query;
import es.ubiqua.compareme.model.Test;
import es.ubiqua.compareme.model.WidgetData;
import es.ubiqua.compareme.service.crawler.CrawlingService;
import es.ubiqua.compareme.service.expedia.ExpediaService;
import es.ubiqua.compareme.utils.Utils;

public class JavascriptsAction extends ActionSupport {
	
	public String text;
	public String datos;
	private WidgetData data;
	private Gson gson = new Gson();
	private String CSS;
	private String HTML;
	
	
	private Hotel hotel;
	
    public String execute() {
    
        return SUCCESS;
    }

    
	public String prova(){
		
		System.out.println("LEO MESSI");
		
		text = "LEO MESSI BALON DE ORO";
		
		return SUCCESS;
		
	}
	
	public String widget(){
		
		data = gson.fromJson(datos, WidgetData.class);
		
		hotel = new Hotel();
		hotel.setName(data.getHotel());
		hotel = new HotelManager().getHotelByHotelName(hotel);
		
		HTML = hotel.getModel();
		CSS = hotel.getCss();
		
		HTML = this.replaceBreakLines(HTML);
		CSS = this.replaceBreakLines(CSS);
		
		return SUCCESS;
		
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public String getDatos() {
		return datos;
	}


	public void setDatos(String datos) {
		this.datos = datos;
	}


	public String getCSS() {
		return CSS;
	}


	public void setCSS(String cSS) {
		CSS = cSS;
	}


	public Hotel getHotel() {
		return hotel;
	}


	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}


	public String getHTML() {
		return HTML;
	}


	public void setHTML(String hTML) {
		HTML = hTML;
	}
	
	private String replaceBreakLines(String html){
		
		String html_encoded = html.replaceAll("[\n\r]","");		
	
		return html_encoded;
		
	}


	public WidgetData getData() {
		return data;
	}


	public void setData(WidgetData data) {
		this.data = data;
	}

}
