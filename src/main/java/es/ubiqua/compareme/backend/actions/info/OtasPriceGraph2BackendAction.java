package es.ubiqua.compareme.backend.actions.info;

import es.ubiqua.compareme.backend.actions.BaseBackendAction;
import es.ubiqua.compareme.manager.OtaManager;
import es.ubiqua.compareme.model.Ota;

import java.util.List;

public class OtasPriceGraph2BackendAction extends BaseBackendAction{
	
	private static final long serialVersionUID = 1L;

	List<Ota> otas;

    private String hotelName;

	public String execute(){
        System.out.print(hotelName);
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
