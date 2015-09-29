package es.ubiqua.compareme.dao;

import org.apache.ibatis.session.SqlSessionFactory;

import es.ubiqua.compareme.database.ConnectionFactory;

public class BaseDAO {
	protected SqlSessionFactory sql = ConnectionFactory.getSession();
}
