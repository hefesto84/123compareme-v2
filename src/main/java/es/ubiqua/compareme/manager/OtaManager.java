package es.ubiqua.compareme.manager;

import java.util.List;

import com.google.gson.Gson;

import es.ubiqua.compareme.dao.OtaDAO;
import es.ubiqua.compareme.exceptions.OtaException;
import es.ubiqua.compareme.model.Customer;
import es.ubiqua.compareme.model.Ota;

public class OtaManager {
	
	private OtaDAO otaDao = new OtaDAO();
	
	public OtaManager(){
		
	}
	
	public List<Ota> list(){
		return otaDao.list();
	}
	
	public List<Ota> list(Customer c){
		return otaDao.list(c);
	}

	public void update(Ota ota){
		otaDao.update(ota);
	}
	
	public Ota get(Ota ota){
		return otaDao.get(ota);
	}
	
	public Ota add(Ota ota){
		return otaDao.add(ota);
	}
	
}
