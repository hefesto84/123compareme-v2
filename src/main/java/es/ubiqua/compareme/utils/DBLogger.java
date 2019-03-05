package es.ubiqua.compareme.utils;

import es.ubiqua.compareme.manager.LogManager;
import es.ubiqua.compareme.manager.MailManager;
import es.ubiqua.compareme.model.Log;

public class DBLogger {
	
	private static DBLogger INSTANCE = null;
	private LogManager logManager;
	
	private DBLogger(){
		logManager = new LogManager();
	}
	
	public static DBLogger  getLogger(){
		if(INSTANCE == null){
			synchronized(DBLogger.class){
				if(INSTANCE==null){
					INSTANCE = new DBLogger();
				}
			}
		}
		return INSTANCE;
	}
	
	@Override
	public DBLogger clone() throws CloneNotSupportedException {
    	throw new CloneNotSupportedException(); 
	}
	
	public void Info(String str){
		Log l = new Log(str,Log.INFO);
		logManager.add(l);
	}
	
	public void Error(String str){
		Log l = new Log(str,Log.ERROR);
		logManager.add(l);
	}
	
	public void Warning(String str){
		Log l = new Log(str,Log.WARNING);
		logManager.add(l);
	}
	
	public void Critical(String customerId, String str){
		MailManager mm = new MailManager(customerId);
		mm.sendMail(str);
		mm = null;
	}
}
