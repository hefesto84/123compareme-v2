package es.ubiqua.compareme.backend.actions;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import es.ubiqua.compareme.manager.CustomerManager;

public class BaseBackendAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	protected CustomerManager customerManager = new CustomerManager();

	public boolean isLogged(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		if(session.containsKey("login") && session.containsKey("context")){
			return true;
		}
		return false;
	}
	
}
