package es.ubiqua.compareme.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import es.ubiqua.compareme.model.Booking;
import es.ubiqua.compareme.model.Log;
import es.ubiqua.compareme.model.Ota;

public class BookingDAO extends BaseDAO {

	public Booking add(Booking booking){
		SqlSession session = sql.openSession();
		try{
			session.insert("SqlMapBooking.add",booking);
			session.commit();
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}finally{
			session.close();
		}
		return booking;
	}
	
}
