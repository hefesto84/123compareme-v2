package es.ubiqua.compareme.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import es.ubiqua.compareme.model.Customer;
import es.ubiqua.compareme.model.Ota;
import es.ubiqua.compareme.model.Price;

public class PriceDAO extends BaseDAO {
	
	public List<Price> list(){
		List<Price> prices = new ArrayList<Price>();
		SqlSession session = sql.openSession();
		try{
			prices = session.selectList("SqlMapPrice.list");
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}finally{
			session.close();
		}
		return prices;
	}
	
	public Price get(Price price){
		SqlSession session = sql.openSession();
		try{
			price = session.selectOne("SqlMapPrice.get",price);
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}finally{
			session.close();
		}
		return price;
	}
	
	public Price getByHash(Price price){
		SqlSession session = sql.openSession();
		try{
			price = session.selectOne("SqlMapPrice.getByHash",price);
			return price;
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}finally{
			session.close();
		}
		return null;
	}
	
	public Price add(Price price){
		SqlSession session = sql.openSession();
		try{
			session.insert("SqlMapPrice.add",price);
			session.commit();
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}finally{
			session.close();
		}
		return price;
	}
	
}
