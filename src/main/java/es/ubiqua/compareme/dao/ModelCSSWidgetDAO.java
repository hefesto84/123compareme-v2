package es.ubiqua.compareme.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import es.ubiqua.compareme.model.Customer;
import es.ubiqua.compareme.model.ModelCSSWidget;

public class ModelCSSWidgetDAO extends BaseDAO {
	
	public List<ModelCSSWidget> list(){
		List<ModelCSSWidget> modelCSSWidget = new ArrayList<ModelCSSWidget>();
		SqlSession session = sql.openSession();
		try{
			modelCSSWidget = session.selectList("SqlMapModelCSSWidget.list");
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}finally{
			session.close();
		}
		return modelCSSWidget;
	}
	
	public List<ModelCSSWidget> list(Customer c){
		List<ModelCSSWidget> modelCSSWidget = new ArrayList<ModelCSSWidget>();
		SqlSession session = sql.openSession();
		try{
			if(c.getAdmin()==1){
				modelCSSWidget = session.selectList("SqlMapModelCSSWidget.list");
			}else{
				modelCSSWidget = session.selectList("SqlMapModelCSSWidget.listByCustomer",c);
			}
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}finally{
			session.close();
		}
		return modelCSSWidget;
	}
	
	public ModelCSSWidget get(ModelCSSWidget modelCSSWidget){
		SqlSession session = sql.openSession();
		try{
			modelCSSWidget = session.selectOne("SqlMapModelCSSWidget.get",modelCSSWidget);
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}finally{
			session.close();
		}
		return modelCSSWidget;
	}
	
	public ModelCSSWidget add(ModelCSSWidget modelCSSWidget){
		SqlSession session = sql.openSession();
		try{
			session.insert("SqlMapModelCSSWidget.add",modelCSSWidget);
			session.commit();
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}finally{
			session.close();
		}
		return modelCSSWidget;
	}
	
	public void update(ModelCSSWidget modelCSSWidget){
		SqlSession session = sql.openSession();
		try{
			session.update("SqlMapModelCSSWidget.update",modelCSSWidget);
			session.commit();
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}finally{
			session.close();
		}
	}
	
	public void delete(ModelCSSWidget modelWidget){
		SqlSession session = sql.openSession();
		try{
			session.delete("SqlMapModelCSSWidget.delete",modelWidget);
			session.commit();
		}catch(Exception e){
			Logger.getLogger(this.getClass()).error(e.getMessage());
		}finally{
			session.close();
		}
	}
	
}
