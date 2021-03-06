package es.ubiqua.compareme.manager;

import java.util.List;

import es.ubiqua.compareme.dao.WidgetTranslationsDAO;
import es.ubiqua.compareme.model.WidgetTranslations;

public class WidgetTranslationsManager {
	
	private WidgetTranslationsDAO widgetTranslationsDao = new WidgetTranslationsDAO();
	
	public WidgetTranslationsManager(){
		
	}
	
	public List<WidgetTranslations> list(){
		return widgetTranslationsDao.list();
	}

	public List<WidgetTranslations> listOnlyByCustomer(int id){
		return widgetTranslationsDao.listOnlyByCustomer(id);
	}

	public List<WidgetTranslations> listByCustomer(int id){
		return widgetTranslationsDao.listByCustomer(id);
	}
	
	public List<WidgetTranslations> listLangByCustomer(int id){
		return widgetTranslationsDao.listLangByCustomer(id);
	}
	
	public List<WidgetTranslations> listByCustomerAndLang(int id, String lang){
		return widgetTranslationsDao.listByCustomerAndLang(id, lang);
	}
	
	public WidgetTranslations get(WidgetTranslations widgetTranslation){
		return widgetTranslationsDao.get(widgetTranslation);
	}
	
	public WidgetTranslations add(WidgetTranslations widgetTranslation){
		return widgetTranslationsDao.add(widgetTranslation);
	}
	
	public void update(WidgetTranslations widgetTranslation){
		widgetTranslationsDao.update(widgetTranslation);
	}
	
	public void delete(WidgetTranslations widgetTranslation){
		widgetTranslationsDao.delete(widgetTranslation);
	}
	
}
