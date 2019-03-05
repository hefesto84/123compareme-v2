package es.ubiqua.compareme.backend.actions;

import java.util.Date;
import java.util.Map;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import es.ubiqua.compareme.exceptions.CustomerException;
import es.ubiqua.compareme.manager.CustomerManager;
import es.ubiqua.compareme.model.Customer;

public class LoginBackendAction  extends BaseBackendAction{

	private static final long serialVersionUID = -2527001795404427911L;
	
	private String username;
	private String password;
	
	private CustomerManager customerManager = new CustomerManager();
	
	public String execute(){
		return SUCCESS;
	}

	public String signin(){
		if(username!=null && password!=null){
			Customer c = new Customer();
			c.setUsername(username);
			c.setPassword(password);
			
			try {
				c = customerManager.login(c);
				if(c==null){
					return ERROR;
				}
				Map<String, Object> session = ActionContext.getContext().getSession();
				session.put("login", true);
				session.put("context",new Date());
				session.put("sid",c.getId());
				return SUCCESS;
			} catch (CustomerException e) {
				return ERROR;
			}
		}
		return ERROR;
	}
	
	public String signout(){
		Map<String,Object> session = ActionContext.getContext().getSession();
		session.remove("login");
		session.remove("context");
		return SUCCESS;
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
