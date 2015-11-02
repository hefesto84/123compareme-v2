package es.ubiqua.compareme.manager;

import java.util.List;

import es.ubiqua.compareme.dao.CustomerDAO;
import es.ubiqua.compareme.dao.OtaDAO;
import es.ubiqua.compareme.dao.TestDAO;
import es.ubiqua.compareme.exceptions.CustomerException;
import es.ubiqua.compareme.exceptions.OtaException;
import es.ubiqua.compareme.model.Customer;
import es.ubiqua.compareme.model.Ota;
import es.ubiqua.compareme.model.Test;

public class TestManager {
	
	private TestDAO testDao = new TestDAO();
	
	public TestManager(){
		
	}
	
	public List<Test> list() throws CustomerException{
		return testDao.list();
	}

	public Test add(Test test) throws CustomerException{
		return testDao.add(test);
	}
	
}
