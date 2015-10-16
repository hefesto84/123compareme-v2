package es.ubiqua.compareme.manager;

import java.util.List;

import es.ubiqua.compareme.dao.LogDAO;
import es.ubiqua.compareme.model.Log;

public class LogManager {
	
	private LogDAO logDao = new LogDAO();
	
	public LogManager(){
		
	}
	
	public Log add(Log log){
		return logDao.add(log);
	}
	
	public List<Log> list(Log log){
		return logDao.list();
	}
}
