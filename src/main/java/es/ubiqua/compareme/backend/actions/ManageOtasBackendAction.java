package es.ubiqua.compareme.backend.actions;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import es.ubiqua.compareme.manager.OtaManager;
import es.ubiqua.compareme.model.Ota;

public class ManageOtasBackendAction extends BaseBackendAction{

	private static final long serialVersionUID = 1L;

	List<Ota> otas;
	String a;
	
	public String execute(){
		
		if(!isLogged()){ return ERROR; }

		otas = new OtaManager().list();
		a = otas.get(0).getName();
		return SUCCESS;
	}

	public List<Ota> getOtas() {
		return this.otas;
	}

	public void setOtas(List<Ota> otas) {
		this.otas = otas;
	}
	
	public String getA(){
		return this.a;
	}
	
}
