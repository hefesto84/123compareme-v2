package es.ubiqua.compareme.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import es.ubiqua.compareme.model.Customer;
import es.ubiqua.compareme.model.Ota;

public class CustomerDAO extends BaseDAO {
	
	public List<Customer> list(){
		List<Customer> customers = new ArrayList<Customer>();
		SqlSession session = sql.openSession();
		try{
			customers = session.selectList("SqlMapCustomer.list");
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(/*e.getMessage()*/e);
		}finally{
			session.close();
		}
		return customers;
	}
	
	public Customer get(Customer customer){
		SqlSession session = sql.openSession();
		try{
			customer = session.selectOne("SqlMapCustomer.get",customer);
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(/*e.getMessage()*/e);
		}finally{
			session.close();
		}
		return customer;
	}
	
	public Customer add(Customer customer){
		SqlSession session = sql.openSession();
		try{
			session.insert("SqlMapCustomer.add",customer);
			session.commit();
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(/*e.getMessage()*/e);
		}finally{
			session.close();
		}
		return customer;
	}
	
}
