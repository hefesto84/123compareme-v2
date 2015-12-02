package es.ubiqua.compareme.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import es.ubiqua.compareme.model.Currency;
import es.ubiqua.compareme.model.Ota;

public class CurrencyDAO extends BaseDAO {

	public List<Currency> list() {
		List<Currency> Currencys = new ArrayList<Currency>();
		SqlSession session = sql.openSession();
		try {
			Currencys = session.selectList("SqlMapCurrency.list");
		} catch (Exception e) {
			Logger.getLogger(this.getClass()).error(/* e.getMessage() */e);
		} finally {
			session.close();
		}
		return Currencys;
	}

	public List<Currency> list(Currency c) {
		List<Currency> Currencys = new ArrayList<Currency>();
		SqlSession session = sql.openSession();
		try {

			Currencys = session.selectList("SqlMapCurrency.list", c);

		} catch (Exception e) {
			Logger.getLogger(this.getClass()).error(/* e.getMessage() */e);
		} finally {
			session.close();
		}
		return Currencys;
	}

	public Currency get(Currency Currency) {
		SqlSession session = sql.openSession();
		try {
			Currency = session.selectOne("SqlMapCurrency.get", Currency);
		} catch (Exception e) {
			Logger.getLogger(this.getClass()).error(/* e.getMessage() */e);
		} finally {
			session.close();
		}
		return Currency;
	}

	public Currency add(Currency Currency) {
		SqlSession session = sql.openSession();
		try {
			session.insert("SqlMapCurrency.add", Currency);
			session.commit();
		} catch (Exception e) {
			Logger.getLogger(this.getClass()).error(/* e.getMessage() */e);
		} finally {
			session.close();
		}
		return Currency;
	}

	public void del(Currency Currency) {
		SqlSession session = sql.openSession();
		try {
			session.delete("SqlMapCurrency.del", Currency);
			session.commit();
		} catch (Exception e) {
			Logger.getLogger(this.getClass()).error(/* e.getMessage() */e);
		} finally {
			session.close();
		}
	}
}
