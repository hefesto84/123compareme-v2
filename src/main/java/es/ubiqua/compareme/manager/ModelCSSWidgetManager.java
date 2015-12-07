package es.ubiqua.compareme.manager;

import java.util.List;

import com.google.gson.Gson;

import es.ubiqua.compareme.dao.ModelCSSWidgetDAO;
import es.ubiqua.compareme.model.Customer;
import es.ubiqua.compareme.model.ModelCSSWidget;

public class ModelCSSWidgetManager {
	
	private ModelCSSWidgetDAO modelCSSWidgetDao = new ModelCSSWidgetDAO();
	
	public ModelCSSWidgetManager(){
		
	}
	
	public List<ModelCSSWidget> list(){
		return modelCSSWidgetDao.list();
	}

	public List<ModelCSSWidget> list(Customer c){
		return modelCSSWidgetDao.list(c);
	}
	
	public ModelCSSWidget get(ModelCSSWidget modelCSSWidget){
		return modelCSSWidgetDao.get(modelCSSWidget);
	}
	
	public ModelCSSWidget add(ModelCSSWidget modelCSSWidget){
		return modelCSSWidgetDao.add(modelCSSWidget);
	}
	
	public void update(ModelCSSWidget modelCSSWidget){
		modelCSSWidgetDao.update(modelCSSWidget);
	}
	
	public void delete(ModelCSSWidget modelCSSWidget){
		modelCSSWidgetDao.delete(modelCSSWidget);
	}
	
	/*public Hotel getHotelByHotelName(Hotel hotel){
		return hotelDao.getHotelByHotelName(hotel);
	}
	
	public Hotel add(Hotel hotel){
		return hotelDao.add(hotel);
	}*/
	
}
