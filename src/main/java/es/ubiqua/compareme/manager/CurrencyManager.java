package es.ubiqua.compareme.manager;

import java.util.List;

import es.ubiqua.compareme.dao.CurrencyDAO;
import es.ubiqua.compareme.dao.OtaDAO;
import es.ubiqua.compareme.exceptions.CurrencyException;
import es.ubiqua.compareme.exceptions.OtaException;
import es.ubiqua.compareme.model.Currency;
import es.ubiqua.compareme.model.Ota;

public class CurrencyManager {
	
	private CurrencyDAO CurrencyDao = new CurrencyDAO();
	
	public CurrencyManager(){
		
	}
	
	public List<Currency> list() throws CurrencyException{
		return CurrencyDao.list();
	}
	
	public List<Currency> list(Currency c) throws CurrencyException{
		return CurrencyDao.list(c);
	}

	public Currency get(Currency Currency) throws CurrencyException{
		return CurrencyDao.get(Currency);
	}

	public Currency add(Currency Currency) throws CurrencyException{
		return CurrencyDao.add(Currency);
	}
	
	public void del(Currency Currency) throws CurrencyException{
		CurrencyDao.del(Currency);
	}
}
