package es.ubiqua.compareme.backend.actions;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import es.ubiqua.compareme.manager.OtaManager;
import es.ubiqua.compareme.model.Hotel;
import es.ubiqua.compareme.model.Ota;
import es.ubiqua.compareme.utils.Utils;

public class DashboardBackendAction extends BaseBackendAction{

	private static final long serialVersionUID = 1L;

	private List<Ota> otas;
	private List<Hotel> hotels;
	private OtaManager otaManager = new OtaManager();
	private String otaJavascriptContent = "";
	private int numOtas = 0;
	
	public String execute(){
		otas = otaManager.list();
		setNumOtas(otas.size());
		otaJavascriptContent = Utils.createOtaStatusJavascriptContent(otas);
		return SUCCESS;
	}

	public List<Ota> getOtas() {
		return otas;
	}

	public List<Hotel> getHotels() {
		return hotels;
	}

	public OtaManager getOtaManager() {
		return otaManager;
	}

	public void setOtas(List<Ota> otas) {
		this.otas = otas;
	}

	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
	}

	public void setOtaManager(OtaManager otaManager) {
		this.otaManager = otaManager;
	}

	public String getOtaJavascriptContent() {
		return otaJavascriptContent;
	}

	public int getNumOtas() {
		return numOtas;
	}

	public void setNumOtas(int numOtas) {
		this.numOtas = numOtas;
	}

	public void setOtaJavascriptContent(String otaJavascriptContent) {
		this.otaJavascriptContent = otaJavascriptContent;
	}
}
