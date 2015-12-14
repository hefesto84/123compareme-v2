package es.ubiqua.compareme.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import es.ubiqua.compareme.model.Customer;
import es.ubiqua.compareme.model.ModelHTMLWidget;

public class ModelHTMLWidgetDAO extends BaseDAO {
	
	public List<ModelHTMLWidget> list(){
		List<ModelHTMLWidget> modelWidget = new ArrayList<ModelHTMLWidget>();
		SqlSession session = sql.openSession();
		try{
			modelWidget = session.selectList("SqlMapModelHTMLWidget.list");
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}finally{
			session.close();
		}
		return modelWidget;
	}
	
	public List<ModelHTMLWidget> list(Customer c){
		List<ModelHTMLWidget> modelWidget = new ArrayList<ModelHTMLWidget>();
		SqlSession session = sql.openSession();
		try{
			if(c.getAdmin()==1){
				modelWidget = session.selectList("SqlMapModelHTMLWidget.list");
			}else{
				modelWidget = session.selectList("SqlMapModelHTMLWidget.listByCustomer",c);
			}
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}finally{
			session.close();
		}
		return modelWidget;
	}
	
	public ModelHTMLWidget get(ModelHTMLWidget modelWidget){
		SqlSession session = sql.openSession();
		try{
			modelWidget = session.selectOne("SqlMapModelHTMLWidget.get",modelWidget);
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}finally{
			session.close();
		}
		return modelWidget;
	}
	
	public ModelHTMLWidget add(ModelHTMLWidget modelWidget){
		SqlSession session = sql.openSession();
		try{
			session.insert("SqlMapModelHTMLWidget.add",modelWidget);
			session.commit();
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}finally{
			session.close();
		}
		return modelWidget;
	}
	
	public void update(ModelHTMLWidget modelWidget){
		SqlSession session = sql.openSession();
		try{
			session.update("SqlMapModelHTMLWidget.update",modelWidget);
			session.commit();
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}finally{
			session.close();
		}
	}
	
	public void delete(ModelHTMLWidget modelWidget){
		SqlSession session = sql.openSession();
		try{
			session.delete("SqlMapModelHTMLWidget.delete",modelWidget);
			session.commit();
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}finally{
			session.close();
		}
	}
	
}
