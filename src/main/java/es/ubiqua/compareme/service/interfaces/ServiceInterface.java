package es.ubiqua.compareme.service.interfaces;

import es.ubiqua.compareme.model.Price;
import es.ubiqua.compareme.service.Service;
import es.ubiqua.compareme.service.expedia.ExpediaService;

public interface ServiceInterface {
	public Service setServiceParameters(String language, String hotelId, int guests, int rooms, String dateIn, String dateOut);
	public Price trackPrice();
}
