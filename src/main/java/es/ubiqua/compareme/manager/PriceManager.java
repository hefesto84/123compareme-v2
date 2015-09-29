package es.ubiqua.compareme.manager;

import java.util.List;

import es.ubiqua.compareme.dao.PriceDAO;
import es.ubiqua.compareme.model.Ota;
import es.ubiqua.compareme.model.Price;

public class PriceManager {
	
	private PriceDAO priceDao = new PriceDAO();
	
	public PriceManager(){
		
	}
	
	public List<Price> list(){
		return priceDao.list();
	}

	public Price get(Price price){
		return priceDao.get(price);
	}
	
	public Price getByHash(Price price){
		return priceDao.getByHash(price);
	}
	
	public Price add(Price price){
		return priceDao.add(price);
	}
	
}
