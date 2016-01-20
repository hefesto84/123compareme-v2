package es.ubiqua.compareme.manager;

import es.ubiqua.compareme.dao.FuturePriceDAO;
import es.ubiqua.compareme.model.FuturePrice;

public class FuturePriceManager {
	
	private FuturePriceDAO futurePriceDao = new FuturePriceDAO();
	
	public FuturePriceManager(){
		
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
	
	public FuturePrice add(FuturePrice price){
		return futurePriceDao.add(price);
	}

    /*public List<Price> getMediumPrices(Price price) {
        return priceDao.getMediumPrices();
    }*/
	
}
