package es.ubiqua.compareme.backend.actions.info;

import java.util.List;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

import es.ubiqua.compareme.backend.actions.BaseBackendAction;
import es.ubiqua.compareme.exceptions.CustomerException;
import es.ubiqua.compareme.manager.CustomerManager;
import es.ubiqua.compareme.manager.OtaManager;
import es.ubiqua.compareme.manager.PriceManager;
import es.ubiqua.compareme.model.Customer;
import es.ubiqua.compareme.model.Ota;
import es.ubiqua.compareme.model.PricesByHotel;

public class CustomerBackendAction extends BaseBackendAction{

	private static final long serialVersionUID = 1L;
	private CustomerManager customerManager = new CustomerManager();
	private OtaManager otaManager = new OtaManager();
	private Customer customer;
	
	
	public String execute(){
		return SUCCESS;
	}

	public String add(){
		try {
			customerManager.add(customer);
		} catch (CustomerException e) {
			Logger.getLogger(this.getClass()).error("Algo pasa");
		}
		return SUCCESS;
	}
	
	public String del(){
		try {
			customerManager.del(customer);
		} catch (CustomerException e) {
			Logger.getLogger(this.getClass()).error("Algo pasa");
		}
		return SUCCESS;
	}
	
	public String get(){
		try {
			customer = customerManager.get(customer);
		} catch (CustomerException e) {
			Logger.getLogger(this.getClass()).error("Algo pasa");
		}
		return SUCCESS;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
