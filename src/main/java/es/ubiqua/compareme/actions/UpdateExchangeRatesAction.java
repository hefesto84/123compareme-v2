package es.ubiqua.compareme.actions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.frozenbullets.api.currencyconverter.CurrencyConverter;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.tunyk.currencyconverter.api.CurrencyConverterException;

import es.ubiqua.compareme.exceptions.CustomerException;
import es.ubiqua.compareme.exceptions.ExpediaServiceException;
import es.ubiqua.compareme.manager.ExchangeManager;
import es.ubiqua.compareme.manager.PriceManager;
import es.ubiqua.compareme.manager.TestManager;
import es.ubiqua.compareme.model.Price;
import es.ubiqua.compareme.model.Query;
import es.ubiqua.compareme.model.Test;
import es.ubiqua.compareme.service.crawler.CrawlingService;
import es.ubiqua.compareme.service.expedia.ExpediaService;
import es.ubiqua.compareme.utils.Utils;

public class UpdateExchangeRatesAction extends ActionSupport {

	private static final long serialVersionUID = -2527009795402427983L;
	private ExchangeManager exchangeManager;
	private String datetime;
	private boolean updated;
	
    public String execute() {
    	exchangeManager = new ExchangeManager();
    	updated = exchangeManager.update();
    	if(updated){
    		datetime = Calendar.getInstance().getTime().toString();
    	}
    	return SUCCESS;
    }

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public boolean isUpdated() {
		return updated;
	}

	public void setUpdated(boolean updated) {
		this.updated = updated;
	}

	
}
