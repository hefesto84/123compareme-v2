package es.ubiqua.compareme.manager;

import java.util.List;

import es.ubiqua.compareme.dao.ModelHTMLWidgetDAO;
import es.ubiqua.compareme.model.Customer;
import es.ubiqua.compareme.model.ModelHTMLWidget;

public class ModelHTMLWidgetManager {
	
	private ModelHTMLWidgetDAO modelWidgetDao = new ModelHTMLWidgetDAO();
	
	public ModelHTMLWidgetManager(){
		
	}
	
	public List<ModelHTMLWidget> list(){
		return modelWidgetDao.list();
	}

	public List<ModelHTMLWidget> list(Customer c){
		return modelWidgetDao.list(c);
	}
	
	public ModelHTMLWidget get(ModelHTMLWidget modelWidget){
		return modelWidgetDao.get(modelWidget);
	}
	
	public ModelHTMLWidget add(ModelHTMLWidget modelWidget){
		return modelWidgetDao.add(modelWidget);
	}
	
	public void update(ModelHTMLWidget modelWidget){
		modelWidgetDao.update(modelWidget);
	}
	
	public void delete(ModelHTMLWidget modelWidget){
		modelWidgetDao.delete(modelWidget);
	}
	
}
