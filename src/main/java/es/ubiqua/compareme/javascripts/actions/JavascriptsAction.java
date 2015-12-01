package es.ubiqua.compareme.javascripts.actions;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

import es.ubiqua.compareme.manager.HotelManager;
import es.ubiqua.compareme.manager.WidgetTranslationsManager;
import es.ubiqua.compareme.model.Hotel;
import es.ubiqua.compareme.model.WidgetData;
import es.ubiqua.compareme.model.WidgetTranslations;

public class JavascriptsAction extends ActionSupport {
	
	public String datos;
	private WidgetData data;
	private Gson gson = new Gson();
	private String CSS;
	private String HTML;
	private Hotel hotel;
	private List<WidgetTranslations> traducciones;
	private String translation;
	
    public String execute() {
    
        return SUCCESS;
    }

    
	public String widgetParkinn(){
		
		return SUCCESS;
		
	}
	
	public String widget(){
		
		data = gson.fromJson(datos, WidgetData.class);
		
		hotel = new Hotel();
		hotel.setName(data.getHotel());
		hotel = new HotelManager().getHotelByHotelName(hotel);
		
		HTML = hotel.getModel();
		CSS = hotel.getCss();
		traducciones = new WidgetTranslationsManager().listByCustomerAndLang(hotel.getCustomerId(),data.getLang());
		
		Map<String, String> map = new HashMap<String, String>();
		for (WidgetTranslations trans : traducciones){
			map.put(trans.getLabel(), trans.getTranslation());
		}
		
		HTML = this.replaceBreakLines(HTML);
		CSS = this.replaceBreakLines(CSS);
		translation = new Gson().toJson(map);
		
		return SUCCESS;
		
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


	public String getTranslation() {
		return translation;
	}


	public void setTranslation(String translation) {
		this.translation = translation;
	}

}
