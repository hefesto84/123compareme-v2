package es.ubiqua.compareme.javascripts.actions;

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

public class JavascriptsAction extends ActionSupport {
	
	public String text;
	
    public String execute() {
    
        return SUCCESS;
    }

    
	public String prova(){
		
		System.out.println("LEO MESSI");
		
		text = "LEO MESSI BALON DE ORO";
		
		return SUCCESS;
		
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}
	
	

}
