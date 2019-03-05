package es.ubiqua.compareme.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import es.ubiqua.compareme.model.Hotel;
import es.ubiqua.compareme.model.HotelOta;
import es.ubiqua.compareme.model.Ota;

public class HotelOtaDAO extends BaseDAO {
	
	public List<HotelOta> list(){
		List<HotelOta> hotelotas = new ArrayList<HotelOta>();
		SqlSession session = sql.openSession();
		try{
			hotelotas = session.selectList("SqlMapHotelOta.list");
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}finally{
			session.close();
		}
		return hotelotas;
	}
	
	public List<HotelOta> listByHotelId(int id){
		List<HotelOta> hotelotas = new ArrayList<HotelOta>();
		SqlSession session = sql.openSession();
		try{
			hotelotas = session.selectList("SqlMapHotelOta.listByHotelId",id);
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}finally{
			session.close();
		}
		return hotelotas;
	}
	
	public HotelOta get(HotelOta hotelota){
		SqlSession session = sql.openSession();
		try{
			hotelota = session.selectOne("SqlMapHotelOta.get",hotelota);
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}finally{
			session.close();
		}
		return hotelota;
	}
	
	public HotelOta get(Hotel hotel, Ota ota){
		HotelOta ho = new HotelOta();
		/*
		List<HotelOta> hotelotas = new ArrayList<HotelOta>();
		SqlSession session = sql.openSession();
		try{
			hotelotas = session.selectList("SqlMapHotelOta.get",hotel);
			for(HotelOta hotelOta : hotelotas){
				if(hotelOta.getId()==ota.getId() || hotelOta.getName().equals(ota.getName())){
					return hotelOta;
				}
			}
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}finally{
			session.close();
		}
		return new HotelOta();
		*/
		SqlSession session = sql.openSession();
		try{
			ho = session.selectOne("SqlMapHotelOta.get",hotel);
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}finally{
			session.close();
		}
		
		return ho;
	}
	
	public HotelOta add(HotelOta hotelota){
		SqlSession session = sql.openSession();
		try{
			session.insert("SqlMapHotelOta.add",hotelota);
			session.commit();
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}finally{
			session.close();
		}
		return hotelota;
	}
	
	public void update(HotelOta hotelota){
		SqlSession session = sql.openSession();
		try{
			session.update("SqlMapHotelOta.update",hotelota);
			session.commit();
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}finally{
			session.close();
		}
	}
	
}
