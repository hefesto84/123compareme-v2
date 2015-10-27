package es.ubiqua.compareme.backend.actions.info;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import es.ubiqua.compareme.backend.actions.BaseBackendAction;
import es.ubiqua.compareme.manager.OtaManager;
import es.ubiqua.compareme.manager.PriceManager;
import es.ubiqua.compareme.model.Ota;
import es.ubiqua.compareme.model.PricesByHotel;

public class OtasInformationBackendAction extends BaseBackendAction{
	
	private static final long serialVersionUID = 1L;

	List<Ota> otas;
	
	public String execute(){
		otas = new OtaManager().list();
		return SUCCESS;
	}

	public List<Ota> getOtas() {
		return this.otas;
	}

	public void setOtas(List<Ota> otas) {
		this.otas = otas;
	}

}
