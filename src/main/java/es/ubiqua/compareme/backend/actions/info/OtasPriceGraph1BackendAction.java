package es.ubiqua.compareme.backend.actions.info;

import es.ubiqua.compareme.backend.actions.BaseBackendAction;
import es.ubiqua.compareme.manager.PriceManager;
import es.ubiqua.compareme.model.Ota;
import es.ubiqua.compareme.model.Price;

import java.util.List;

public class OtasPriceGraph1BackendAction extends BaseBackendAction{
	
	private static final long serialVersionUID = 1L;

    private String  dateIni;
    private String  dateEnd;
    private Integer otaId;
    private Integer hotelId;

	List<Price> prices;

	public String execute(){
        Price price = new Price();
        price.setDateIn(dateIni);
        price.setDateOut(dateEnd);
        price.setOtaId(1);
        price.setHotelId(0);
        prices = new PriceManager().getMediumPrices(price);
		return SUCCESS;
	}

    public void setDateIni(String dateIni) {
        this.dateIni = dateIni;
    }

    public String getDateIni() {
        return dateIni;
    }

    public void setDateEnd(String dateOut) {
        this.dateEnd = dateEnd;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setOtaId(Integer otaId) {
        this.otaId = otaId;
    }

    public Integer getOtaId() {
        return otaId == null ? 0 : otaId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getHotelId() {
        return hotelId == null ? 0 : hotelId;
    }

	public List<Price> getPrices() {
		return this.prices;
	}

	public void setPrices(List<Price> prices) {
		this.prices = prices;
	}
}
