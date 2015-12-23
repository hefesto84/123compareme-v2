package es.ubiqua.compareme.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import es.ubiqua.compareme.model.Currency;
import es.ubiqua.compareme.model.Exchange;
import es.ubiqua.compareme.model.Ota;

public class ExchangeDAO extends BaseDAO {

	public List<Exchange> list() {
		List<Exchange> exchanges = new ArrayList<Exchange>();
		SqlSession session = sql.openSession();
		try {
			exchanges = session.selectList("SqlMapExchange.list");
		} catch (Exception e) {
			Logger.getLogger(this.getClass()).error(/* e.getMessage() */e);
		} finally {
			session.close();
		}
		return exchanges;
	}

	public Exchange get(Exchange exchange) {
		SqlSession session = sql.openSession();
		try {
			exchange = session.selectOne("SqlMapExchange.get", exchange);
		} catch (Exception e) {
			Logger.getLogger(this.getClass()).error(/* e.getMessage() */e);
		} finally {
			session.close();
		}
		return exchange;
	}
	
	public Exchange restrictive(Exchange exchange) {
		SqlSession session = sql.openSession();
		try {
			exchange = session.selectOne("SqlMapExchange.restrictive", exchange);
		} catch (Exception e) {
			Logger.getLogger(this.getClass()).error(/* e.getMessage() */e);
		} finally {
			session.close();
		}
		return exchange;
	}

	public Exchange add(Exchange exchange) {
		SqlSession session = sql.openSession();
		try {
			session.insert("SqlMapExchange.add", exchange);
			session.commit();
		} catch (Exception e) {
			Logger.getLogger(this.getClass()).error(/* e.getMessage() */e);
		} finally {
			session.close();
		}
		return exchange;
	}

	public void del(Exchange exchange) {
		SqlSession session = sql.openSession();
		try {
			session.delete("SqlMapExchange.del", exchange);
			session.commit();
		} catch (Exception e) {
			Logger.getLogger(this.getClass()).error(/* e.getMessage() */e);
		} finally {
			session.close();
		}
	}
}
