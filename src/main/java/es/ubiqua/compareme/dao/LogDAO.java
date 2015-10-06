package es.ubiqua.compareme.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import es.ubiqua.compareme.model.Log;
import es.ubiqua.compareme.model.Ota;

public class LogDAO extends BaseDAO {
	
	public List<Log> list(){
		List<Log> logs = new ArrayList<Log>();
		SqlSession session = sql.openSession();
		try{
			logs = session.selectList("SqlMapOta.list");
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}finally{
			session.close();
		}
		return logs;
	}
	
	public Ota get(Ota ota){
		SqlSession session = sql.openSession();
		try{
			ota = session.selectOne("SqlMapOta.get",ota);
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}finally{
			session.close();
		}
		return ota;
	}
	
	public Ota add(Ota ota){
		SqlSession session = sql.openSession();
		try{
			session.insert("SqlMapOta.add",ota);
			session.commit();
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}finally{
			session.close();
		}
		return ota;
	}
	
}
