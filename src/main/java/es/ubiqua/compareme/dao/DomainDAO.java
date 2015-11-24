package es.ubiqua.compareme.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import es.ubiqua.compareme.model.Customer;
import es.ubiqua.compareme.model.Domain;
import es.ubiqua.compareme.model.Ota;
import es.ubiqua.compareme.utils.DBLogger;

public class DomainDAO extends BaseDAO {
	
	public Domain get(Domain domain){
		SqlSession session = sql.openSession();
		try{
			domain = session.selectOne("SqlMapDomain.get",domain);
		}catch(Exception e){
			domain.setDomain("www.expedia.es");
			System.out.println(e.toString());
		}finally{
			session.close();
		}
		return domain;
	}
	
}
