package es.ubiqua.compareme.utils;

public class DBLogger {
	
	private static DBLogger INSTANCE = null;
	
	private DBLogger(){}
	
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
	
	public void Log(String str){
		System.out.println(str);
	}
	
	public void Error(String str){
		
	}
	
	public void Warning(String str){
		
	}
}
