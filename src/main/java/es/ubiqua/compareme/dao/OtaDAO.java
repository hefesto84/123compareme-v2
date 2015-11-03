package es.ubiqua.compareme.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import es.ubiqua.compareme.model.Customer;
import es.ubiqua.compareme.model.Ota;
import es.ubiqua.compareme.utils.DBLogger;

public class OtaDAO extends BaseDAO {
	
	public List<Ota> list(Customer c){
		List<Ota> otas = new ArrayList<Ota>();
		SqlSession session = sql.openSession();
		try{
			otas = session.selectList("SqlMapOta.listByOta",c);
		}catch(Exception e){
			DBLogger.getLogger().Error("ERROR: "+e.getMessage());
		}finally{
			session.close();
		}
		return otas;
	}
	
	public List<Ota> list(){
		List<Ota> otas = new ArrayList<Ota>();
		SqlSession session = sql.openSession();
		try{
			otas = session.selectList("SqlMapOta.list");
		}catch(Exception e){
			DBLogger.getLogger().Error("ERROR: "+e.getMessage());
		}finally{
			session.close();
		}
		return otas;
	}
	
	public void update(Ota ota){
		SqlSession session = sql.openSession();
		try{
			session.update("SqlMapOta.update",ota);
			session.commit();
		}catch(Exception e){
			DBLogger.getLogger().Error("ERROR: "+e.getMessage() + " DATA: "+ota.toDBLogger());
		}finally{
			session.close();
		}
	}
	
	public Ota get(Ota ota){
		SqlSession session = sql.openSession();
		try{
			ota = session.selectOne("SqlMapOta.get",ota);
		}catch(Exception e){
			//Logger.getLogger(this.getClass()).error(e.getMessage());
			DBLogger.getLogger().Error("ERROR: "+e.getMessage() + " DATA: "+ota.toDBLogger());
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
			//Logger.getLogger(this.getClass()).error(e.getMessage());
			DBLogger.getLogger().Error("ERROR: "+e.getMessage() + " DATA: "+ota.toDBLogger());
		}finally{
			session.close();
		}
		return ota;
	}
	
}
