package es.ubiqua.compareme.manager;

import java.util.List;

import com.google.gson.Gson;

import es.ubiqua.compareme.dao.DomainDAO;
import es.ubiqua.compareme.dao.OtaDAO;
import es.ubiqua.compareme.exceptions.OtaException;
import es.ubiqua.compareme.model.Customer;
import es.ubiqua.compareme.model.Domain;
import es.ubiqua.compareme.model.Ota;

public class DomainManager {
	
	private DomainDAO domainDao = new DomainDAO();
	
	public DomainManager(){
		
	}
	
	public Domain get(Domain domain){
		return domainDao.get(domain);
	}
	
}
