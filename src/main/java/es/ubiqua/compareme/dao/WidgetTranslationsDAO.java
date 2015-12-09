package es.ubiqua.compareme.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import es.ubiqua.compareme.model.WidgetTranslations;

public class WidgetTranslationsDAO extends BaseDAO {
	
	public List<WidgetTranslations> list(){
		List<WidgetTranslations> widgetTranslations = new ArrayList<WidgetTranslations>();
		SqlSession session = sql.openSession();
		try{
			widgetTranslations = session.selectList("SqlMapWidgetTranslations.list");
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}finally{
			session.close();
		}
		return widgetTranslations;
	}
	
	public List<WidgetTranslations> listOnlyByCustomer(int id){
		List<WidgetTranslations> widgetTranslations = new ArrayList<WidgetTranslations>();
		SqlSession session = sql.openSession();
		try{
			widgetTranslations = session.selectList("SqlMapWidgetTranslations.listOnlyByCustomer",id);
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}finally{
			session.close();
		}
		return widgetTranslations;
	}
	
	public List<WidgetTranslations> listByCustomer(int id){
		List<WidgetTranslations> widgetTranslations = new ArrayList<WidgetTranslations>();
		SqlSession session = sql.openSession();
		try{
			widgetTranslations = session.selectList("SqlMapWidgetTranslations.listByCustomer",id);
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}finally{
			session.close();
		}
		return widgetTranslations;
	}
	
	public List<WidgetTranslations> listLangByCustomer(int id){
		List<WidgetTranslations> langTranslations = new ArrayList<WidgetTranslations>();
		SqlSession session = sql.openSession();
		try{
			langTranslations = session.selectList("SqlMapWidgetTranslations.listLangByCustomer",id);
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}finally{
			session.close();
		}
		return langTranslations;
	}
	
	public List<WidgetTranslations> listByCustomerAndLang(int id, String lang){
		List<WidgetTranslations> langTranslations = new ArrayList<WidgetTranslations>();
		SqlSession session = sql.openSession();
		try{
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id", id);
			map.put("lang", lang);
			langTranslations = session.selectList("SqlMapWidgetTranslations.listByCustomerAndLang",map);
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}finally{
			session.close();
		}
		return langTranslations;
	}
	
	public WidgetTranslations get(WidgetTranslations widgetTranslations){
		SqlSession session = sql.openSession();
		try{
			widgetTranslations = session.selectOne("SqlMapWidgetTranslations.get",widgetTranslations);
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}finally{
			session.close();
		}
		return widgetTranslations;
	}
	
	public WidgetTranslations add(WidgetTranslations widgetTranslations){
		SqlSession session = sql.openSession();
		try{
			session.insert("SqlMapWidgetTranslations.add",widgetTranslations);
			session.commit();
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}finally{
			session.close();
		}
		return widgetTranslations;
	}
	
	public void update(WidgetTranslations widgetTranslations){
		SqlSession session = sql.openSession();
		try{
			session.update("SqlMapWidgetTranslations.update",widgetTranslations);
			session.commit();
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}finally{
			session.close();
		}
	}
	
	public void delete(WidgetTranslations widgetTranslations){
		SqlSession session = sql.openSession();
		try{
			session.delete("SqlMapWidgetTranslations.delete",widgetTranslations);
			session.commit();
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}finally{
			session.close();
		}
	}
	
}
