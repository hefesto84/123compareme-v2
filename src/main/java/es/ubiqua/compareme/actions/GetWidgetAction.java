package es.ubiqua.compareme.actions;

import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Result;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

import es.ubiqua.compareme.exceptions.ExpediaServiceException;
import es.ubiqua.compareme.manager.PriceManager;
import es.ubiqua.compareme.model.Price;
import es.ubiqua.compareme.model.Query;
import es.ubiqua.compareme.service.crawler.CrawlingService;
import es.ubiqua.compareme.service.expedia.ExpediaService;

public class GetWidgetAction extends ActionSupport {

	private static final long serialVersionUID = -2527009795402427981L;
	private InputStream output;
	
	
    public String execute() {
    	output = new StringBufferInputStream("This a stream test");
        return SUCCESS;
    }

    public InputStream getOutput() {
        return output;
       }
     

}
