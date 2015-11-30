package es.ubiqua.compareme.model;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
	
	private int id;
	private String name;
	private int customerId;
	private String customerName;
	private int otaId;
	private String currency;
	private int modelWidget;
	private String model;
	private String css;
	
	public Hotel(){
		
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getOtaId() {
		return otaId;
	}

	public void setOtaId(int otaId) {
		this.otaId = otaId;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getModelWidget() {
		return modelWidget;
	}

	public void setModelWidget(int modelWidget) {
		this.modelWidget = modelWidget;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getCss() {
		return css;
	}

	public void setCss(String css) {
		this.css = css;
	}

}
