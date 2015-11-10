package es.ubiqua.compareme.backend.actions;

import com.opensymphony.xwork2.ActionSupport;

import es.ubiqua.compareme.manager.CustomerManager;
import es.ubiqua.compareme.model.Customer;

public class WidgetConfigurationBackendAction extends BaseBackendAction{

	private static final long serialVersionUID = 1L;
	private Customer c;
	private CustomerManager customerManager = new CustomerManager();
	
	public String execute(){
		c = getLoggedCustomer();
		return SUCCESS;
	}

	public Customer getC() {
		return c;
	}

	public void setC(Customer c) {
		this.c = c;
	}
}
