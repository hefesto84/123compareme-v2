package es.ubiqua.compareme.manager;

import es.ubiqua.compareme.dao.PriceConvertedDAO;
import es.ubiqua.compareme.model.PriceConverted;

public class PriceConvertedManager {
	
	private PriceConvertedDAO priceConvertedDao = new PriceConvertedDAO();
	
	public PriceConvertedManager(){
		
	}
	
	/*public List<Price> list(){
		return priceDao.list();
	}
	
	public List<PricesByHotel> getPricesByHotel(){
		return priceDao.getPricesByHotel();
	}


	public Price get(Price price){
		return priceDao.get(price);
	}
	
	public Price getByHash(Price price){
		return priceDao.getByHash(price);
	}*/
	
	public PriceConverted add(PriceConverted price){
		return priceConvertedDao.add(price);
	}

    /*public List<Price> getMediumPrices(Price price) {
        return priceDao.getMediumPrices();
    }*/
	
}
