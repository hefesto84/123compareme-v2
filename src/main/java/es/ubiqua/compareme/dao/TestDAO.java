package es.ubiqua.compareme.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import es.ubiqua.compareme.model.Customer;
import es.ubiqua.compareme.model.Ota;
import es.ubiqua.compareme.model.Test;

public class TestDAO extends BaseDAO {
	
	public List<Test> list(){
		List<Test> tests = new ArrayList<Test>();
		SqlSession session = sql.openSession();
		try{
			tests = session.selectList("SqlMapTest.list");
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(/*e.getMessage()*/e);
		}finally{
			session.close();
		}
		return tests;
	}
	
	public Test add(Test test){
		SqlSession session = sql.openSession();
		try{
			session.insert("SqlMapTest.add",test);
			session.commit();
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(/*e.getMessage()*/e);
		}finally{
			session.close();
		}
		return test;
	}
	
}
