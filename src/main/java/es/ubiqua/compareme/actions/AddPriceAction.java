package es.ubiqua.compareme.actions;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

import es.ubiqua.compareme.model.AddPriceResponse;
import es.ubiqua.compareme.model.Price;


public class AddPriceAction extends ActionSupport {

	private static final long serialVersionUID = -2527009795402427983L;

	private String response;
	public AddPriceResponse mResponse;
	
    public String execute() {
        Gson gson = new Gson();
        
        mResponse = new AddPriceResponse(true, "Price added");
   
        response = gson.toJson(mResponse);

        return SUCCESS;
    }

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

}
