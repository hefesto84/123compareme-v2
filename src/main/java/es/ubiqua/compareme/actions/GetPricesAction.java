package es.ubiqua.compareme.actions;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

import es.ubiqua.compareme.exceptions.CustomerException;
import es.ubiqua.compareme.exceptions.ExpediaServiceException;
import es.ubiqua.compareme.manager.PriceManager;
import es.ubiqua.compareme.manager.TestManager;
import es.ubiqua.compareme.model.Price;
import es.ubiqua.compareme.model.Query;
import es.ubiqua.compareme.model.Test;
import es.ubiqua.compareme.service.crawler.CrawlingService;
import es.ubiqua.compareme.service.expedia.ExpediaService;
import es.ubiqua.compareme.utils.Utils;

public class GetPricesAction extends ActionSupport {

	private static final long serialVersionUID = -2527009795402427983L;

	private List<Price> datos;
	
	private String code;
	private String lang;
	private String hotel;
	private String fin;
	private String fout;
	private String base;
	private int rooms;
	private int guests;
	private String currency;
	
    public String execute() {
    
        Query query = new Query(code,lang,hotel,rooms,guests,fin,fout,base,currency);
        CrawlingService service = new CrawlingService();
        datos = service.weaving(CrawlingService.MONOTHREAD_MODE, query);
        //Utils.checkCoherence(code,datos);
        return SUCCESS;
    }

    
	public List<Price> getDatos() {
		return datos;
	}

	public void setDatos(List<Price> datos) {
		this.datos = datos;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setRooms(int rooms) {
		this.rooms = rooms;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public void setHotel(String hotel) {
		this.hotel = hotel;
	}

	public void setFin(String fin) {
		this.fin = fin;
	}

	public void setFout(String fout) {
		this.fout = fout;
	}

	public void setGuests(int guests) {
		this.guests = guests;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}


	public String getCurrency() {
		return currency;
	}


	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
