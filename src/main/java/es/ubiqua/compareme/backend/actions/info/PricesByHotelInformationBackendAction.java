package es.ubiqua.compareme.backend.actions.info;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import es.ubiqua.compareme.backend.actions.BaseBackendAction;
import es.ubiqua.compareme.manager.OtaManager;
import es.ubiqua.compareme.manager.PriceManager;
import es.ubiqua.compareme.model.Ota;
import es.ubiqua.compareme.model.PricesByHotel;

public class PricesByHotelInformationBackendAction extends BaseBackendAction{

	private static final long serialVersionUID = 1L;

	private List<PricesByHotel> prices;
	
	public String execute(){
		setPrices(new PriceManager().getPricesByHotel());
		return SUCCESS;
	}

	public List<PricesByHotel> getPrices() {
		return prices;
	}

	public void setPrices(List<PricesByHotel> prices) {
		this.prices = prices;
	}

}
