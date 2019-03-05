package es.ubiqua.compareme.javascripts.actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gargoylesoftware.htmlunit.javascript.host.Console;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

import es.ubiqua.compareme.manager.FuturePriceManager;
import es.ubiqua.compareme.manager.HotelCityManager;
import es.ubiqua.compareme.manager.HotelHomeManager;
import es.ubiqua.compareme.manager.HotelManager;
import es.ubiqua.compareme.manager.ModelCSSWidgetManager;
import es.ubiqua.compareme.manager.ModelHTMLWidgetManager;
import es.ubiqua.compareme.manager.WidgetTranslationsManager;
import es.ubiqua.compareme.model.FuturePrice;
import es.ubiqua.compareme.model.Hotel;
import es.ubiqua.compareme.model.HotelCity;
import es.ubiqua.compareme.model.HotelHome;
import es.ubiqua.compareme.model.ModelCSSWidget;
import es.ubiqua.compareme.model.ModelHTMLWidget;
import es.ubiqua.compareme.model.WidgetData;
import es.ubiqua.compareme.model.WidgetHomeData;
import es.ubiqua.compareme.model.WidgetTranslations;

public class JavascriptsAction extends ActionSupport {
	
	public String datos;
	private WidgetData data;
	private WidgetHomeData dataHome;
	private Gson gson = new Gson();
	private String CSS;
	private String HTML;
	private Hotel hotel;
	private HotelHome hotelHome;
	private HotelCity hotelCity;
	private List<WidgetTranslations> traducciones;
	private List<FuturePrice> prices;
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
		
		if(traducciones.size() == 0){
			traducciones = new WidgetTranslationsManager().listByCustomerAndLang(hotel.getCustomerId(),data.getDefaultLang());
		}
		
		Map<String, String> map = new HashMap<String, String>();
		for (WidgetTranslations trans : traducciones){
			map.put(trans.getLabel(), trans.getTranslation());
		}
		
		HTML = this.replaceBreakLines(HTML);
		CSS = this.replaceBreakLines(CSS);
		translation = new Gson().toJson(map);
		
		return SUCCESS;
		
	}
	
	public String widgetHome(){
		
		dataHome = gson.fromJson(datos, WidgetHomeData.class);
		
		hotel = new Hotel();
		hotel.setName(dataHome.getHotel());
		hotel = new HotelManager().get(hotel);
		
		hotelHome = new HotelHome();
		hotelHome.setId_hotel(hotel.getId());
		hotelHome = new HotelHomeManager().getByIdHotel(hotelHome);
		
		ModelHTMLWidget modelHTMLWidget = new ModelHTMLWidget();
		modelHTMLWidget.setId(hotelHome.getModelWidget());
		modelHTMLWidget = new ModelHTMLWidgetManager().get(modelHTMLWidget);
		
		ModelCSSWidget modelCSSWidget = new ModelCSSWidget();
		modelCSSWidget.setId(hotelHome.getCssWidget());
		modelCSSWidget = new ModelCSSWidgetManager().get(modelCSSWidget);
		
		HTML = modelHTMLWidget.getModel();
		CSS = modelCSSWidget.getCss();
		traducciones = new WidgetTranslationsManager().listByCustomerAndLang(hotel.getCustomerId(),dataHome.getLang());
		
		if(traducciones.size() == 0){
			traducciones = new WidgetTranslationsManager().listByCustomerAndLang(hotel.getCustomerId(),dataHome.getDefaultLang());
		}
		
		Map<String, String> map = new HashMap<String, String>();
		for (WidgetTranslations trans : traducciones){
			map.put(trans.getLabel(), trans.getTranslation());
		}
		
		HTML = this.replaceBreakLines(HTML);
		CSS = this.replaceBreakLines(CSS);
		translation = new Gson().toJson(map);
		prices = new FuturePriceManager().listByHotelId(hotel.getId());
		
		return SUCCESS;
		
	}
	
	public String widgetCity(){
		
		data = gson.fromJson(datos, WidgetData.class);
		
		hotel = new Hotel();
		hotel.setName(data.getHotel());
		hotel = new HotelManager().get(hotel);
		
		hotelCity = new HotelCity();
		hotelCity.setId_hotel(hotel.getId());
		hotelCity = new HotelCityManager().getByIdHotel(hotelCity);
		
		System.out.println("ROC : "+new Gson().toJson(hotelCity));
		
		System.out.println("LEO : "+hotelCity.getModelWidget()+" MESSI : "+hotelCity.getCssWidget());
		
		ModelHTMLWidget modelHTMLWidget = new ModelHTMLWidget();
		modelHTMLWidget.setId(hotelCity.getModelWidget());
		modelHTMLWidget = new ModelHTMLWidgetManager().get(modelHTMLWidget);
		
		ModelCSSWidget modelCSSWidget = new ModelCSSWidget();
		modelCSSWidget.setId(hotelCity.getCssWidget());
		modelCSSWidget = new ModelCSSWidgetManager().get(modelCSSWidget);
		
		
		HTML = modelHTMLWidget.getModel();
		CSS = modelCSSWidget.getCss();
		traducciones = new WidgetTranslationsManager().listByCustomerAndLang(hotel.getCustomerId(),data.getLang());
		
		if(traducciones.size() == 0){
			traducciones = new WidgetTranslationsManager().listByCustomerAndLang(hotel.getCustomerId(),data.getDefaultLang());
		}
		
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


	public List<FuturePrice> getPrices() {
		return prices;
	}


	public void setPrices(List<FuturePrice> prices) {
		this.prices = prices;
	}
	
	

}
