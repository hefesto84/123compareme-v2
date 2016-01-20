package es.ubiqua.compareme.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import es.ubiqua.compareme.model.FuturePrice;

public class FuturePriceDAO extends BaseDAO {
	
	/*public List<Price> list(){
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
			System.out.println("DAO: SELECT ONE: "+price);
			return price;
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}finally{
			session.close();
		}
		return null;
	}*/
	
	public FuturePrice add(FuturePrice price){
		SqlSession session = sql.openSession();
		try{
			session.insert("SqlMapFuturePrice.add",price);
			session.commit();
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}finally{
			session.close();
		}
		return price;
	}
	
	/*public List<PricesByHotel> getPricesByHotel(){
		List<PricesByHotel> prices = new ArrayList<PricesByHotel>();
		SqlSession session = sql.openSession();
		try{
			prices = session.selectList("SqlMapPrice.getPricesByHotel");
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}finally{
			session.close();
		}
		return prices;
	}

	public List<Price> getMediumPrices() {
		List<Price> prices = new ArrayList<Price>();
        SqlSession session = sql.openSession();
        try {
            prices = session.selectList("SqlMapPrice.graph1");
        } catch (Exception e) {
            Logger.getLogger(this.getClass()).error(e.getMessage());
        }finally{
            session.close();
        }
        return prices;
	}*/
}
