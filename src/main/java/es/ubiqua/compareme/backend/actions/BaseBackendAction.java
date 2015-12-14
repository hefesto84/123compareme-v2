package es.ubiqua.compareme.backend.actions;

import java.util.Map;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import es.ubiqua.compareme.manager.CustomerManager;
import es.ubiqua.compareme.model.Customer;

public class BaseBackendAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	protected CustomerManager customerManager = new CustomerManager();

	public boolean isLogged(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		if(session.containsKey("login") && session.containsKey("context") && session.containsKey("sid")){
			
			return true;
		}
		return false;
	}
	
	public Customer getLoggedCustomer(){
		Customer c = new Customer();
		Integer id = (Integer)ActionContext.getContext().getSession().get("sid");
		c.setId(id);
		try{
			c = new CustomerManager().get(c);
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(/*e.getMessage()*/e);
		}
		return c;
	}
	
	public boolean isCorrectCustomer(String widget){
		Customer c = new Customer();
		Integer id = (Integer)ActionContext.getContext().getSession().get("sid");
		c.setId(id);
		try{
			c = new CustomerManager().get(c);
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(/*e.getMessage()*/e);
		}
		
		if (c.getAdmin() == 1){
			return true;
		}
		if (c.getIdentifier().equals(widget)){
			return true;
		}
		return false;
	}
	
}
