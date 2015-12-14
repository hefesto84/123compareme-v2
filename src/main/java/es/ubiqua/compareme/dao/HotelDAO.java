package es.ubiqua.compareme.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import es.ubiqua.compareme.model.Customer;
import es.ubiqua.compareme.model.Hotel;
import es.ubiqua.compareme.model.Ota;

public class HotelDAO extends BaseDAO {
	
	public List<Hotel> list(){
		List<Hotel> hotels = new ArrayList<Hotel>();
		SqlSession session = sql.openSession();
		try{
			hotels = session.selectList("SqlMapHotel.list");
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}finally{
			session.close();
		}
		return hotels;
	}
	
	public List<Hotel> list(Customer c){
		List<Hotel> hotels = new ArrayList<Hotel>();
		SqlSession session = sql.openSession();
		try{
			if(c.getAdmin()==1){
				hotels = session.selectList("SqlMapHotel.list");
			}else{
				hotels = session.selectList("SqlMapHotel.listByCustomer",c);
			}
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}finally{
			session.close();
		}
		return hotels;
	}
	
	public Hotel get(Hotel hotel){
		SqlSession session = sql.openSession();
		try{
			hotel = session.selectOne("SqlMapHotel.get",hotel);
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}finally{
			session.close();
		}
		return hotel;
	}
	
	public Hotel getHotelByHotelName(Hotel hotel){
		SqlSession session = sql.openSession();
		try{
			hotel = session.selectOne("SqlMapHotel.getHotelByHotelName",hotel);
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}finally{
			session.close();
		}
		return hotel;
	}
	
	public Hotel add(Hotel hotel){
		SqlSession session = sql.openSession();
		try{
			session.insert("SqlMapHotel.add",hotel);
			session.commit();
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}finally{
			session.close();
		}
		return hotel;
	}
	
}
