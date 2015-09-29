package es.ubiqua.compareme.backend.actions;

import com.opensymphony.xwork2.ActionSupport;

public class BaseBackendAction extends ActionSupport{

	private static final long serialVersionUID = 1L;

	@Override
	public  String execute(){
		return SUCCESS;
	}
}
