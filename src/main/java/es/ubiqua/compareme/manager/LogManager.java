package es.ubiqua.compareme.manager;

import java.util.List;

import es.ubiqua.compareme.dao.OtaDAO;
import es.ubiqua.compareme.exceptions.OtaException;
import es.ubiqua.compareme.model.Ota;

public class LogManager {
	
	private OtaDAO otaDao = new OtaDAO();
	
	public LogManager(){
		
	}
	
	public List<Ota> list(){
		return otaDao.list();
	}

	public Ota get(Ota ota){
		return otaDao.get(ota);
	}
	
	public Ota add(Ota ota){
		return otaDao.add(ota);
	}
	
}
