package es.ubiqua.compareme.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import es.ubiqua.compareme.model.Customer;
import es.ubiqua.compareme.model.Hotel;

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
	
	public List<Hotel> listOrdered(Customer c){
		List<Hotel> hotels = new ArrayList<Hotel>();
		SqlSession session = sql.openSession();
		try{
			if(c.getAdmin()==1){
				hotels = session.selectList("SqlMapHotel.listOrdered");
			}else{
				hotels = session.selectList("SqlMapHotel.listOrderedByCustomer",c);
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
	
	public List<Hotel> getHotelAutocompletar(String term){
		List<Hotel> hotels = new ArrayList<Hotel>();
		String[] array = term.split(" ");
		String cadena = "";
		String text;
		for(int i = 0; i < array.length; i++){
            if(array[i]!=""){
                if (i < (array.length - 1)){
                    text = " +"+array[i];
                } else {
                    text = " +"+array[i]+"*";
                }
                cadena += text;
            }
        }
		SqlSession session = sql.openSession();
		try{
			hotels = session.selectList("SqlMapHotel.getHotelAutocompletar",cadena);
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}finally{
			session.close();
		}
		return hotels;
	}
	
	public List<Hotel> getHotelAutocompletarSimple(String term, Customer c){
		List<Hotel> hotels = new ArrayList<Hotel>();
		SqlSession session = sql.openSession();
		try{
			if(c.getAdmin()==1){
				hotels = session.selectList("SqlMapHotel.getHotelAutocompletarSimple",term);
			}else{
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("term", term);
				map.put("customer", c);
				hotels = session.selectList("SqlMapHotel.getHotelAutocompletarSimpleByCustomer",map);
			}
			
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}finally{
			session.close();
		}
		return hotels;
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
	

	
	public void update(Hotel hotel){
		SqlSession session = sql.openSession();
		try{
			session.update("SqlMapHotel.update",hotel);
			session.commit();
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}finally{
			session.close();
		}
	}
	
}
