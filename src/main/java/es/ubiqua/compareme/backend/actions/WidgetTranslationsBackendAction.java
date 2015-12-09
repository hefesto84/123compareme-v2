package es.ubiqua.compareme.backend.actions;

import java.util.List;

import es.ubiqua.compareme.manager.CustomerManager;
import es.ubiqua.compareme.manager.WidgetTranslationsManager;
import es.ubiqua.compareme.model.Customer;
import es.ubiqua.compareme.model.WidgetTranslations;

public class WidgetTranslationsBackendAction extends BaseBackendAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<WidgetTranslations> traducciones;
	private WidgetTranslationsManager traduccionesManager = new WidgetTranslationsManager();
	
	private int id;

	private WidgetTranslations traduccion;
	
	private int form_id;
	private String status;
	
	private List<Customer> customers;
	private int form_customer;
	private String form_lang;
	private String form_label;
	private String form_translation;

	public String execute(){
		
		if(!isLogged()){ return ERROR; }
		
		int identifier = Integer.parseInt(getLoggedCustomer().getIdentifier());
				
		if (getLoggedCustomer().getAdmin() == 1){
			traducciones = traduccionesManager.list();
		} else {
			traducciones = traduccionesManager.listOnlyByCustomer(identifier);
		}
		
		return SUCCESS;
	}
	
	public String newAction(){
		
		CustomerManager customerManager = new CustomerManager();
		
		customers = customerManager.listAllCustomers(getLoggedCustomer());
		
		return SUCCESS;
		
	}
	
	public String addAction(){
		
		traduccion = new WidgetTranslations();
		traduccion.setCustomer(form_customer);
		traduccion.setLang(form_lang);
		traduccion.setLabel(form_label);
		traduccion.setTranslation(form_translation);
		
		traduccionesManager.add(traduccion);
		
		status = "ok";
		
		return SUCCESS;
	}
	
	public String editAction(){
		
		if(!isLogged()){ return ERROR; }
		
		traduccion = new WidgetTranslations();
		traduccion.setId(id);
		traduccion = traduccionesManager.get(traduccion);
		
		if(!isCorrectCustomer(String.valueOf(traduccion.getCustomer()))){ return ERROR; }
		
		return SUCCESS;
	}
	
	public String modifyAction(){
		
		if(!isLogged()){ return ERROR; }
		
		traduccion = new WidgetTranslations();
		traduccion.setId(form_id);
		traduccion = traduccionesManager.get(traduccion);
		
		if(!isCorrectCustomer(String.valueOf(traduccion.getCustomer()))){ return ERROR; }
		
		traduccion.setLang(form_lang);
		traduccion.setLabel(form_label);
		traduccion.setTranslation(form_translation);
		traduccionesManager.update(traduccion);
		
		status = "ok";
		
		return SUCCESS;
	}
	
	public String deleteAction (){
		
		if(!isLogged()){ return ERROR; }
		
		traduccion = new WidgetTranslations();
		traduccion.setId(id);
		traduccion = traduccionesManager.get(traduccion);
		
		if(!isCorrectCustomer(String.valueOf(traduccion.getCustomer()))){ return ERROR; }
		
		traduccionesManager.delete(traduccion);
		
		status = "ok";
		
		return SUCCESS;
	}

	public List<WidgetTranslations> getTraducciones() {
		return traducciones;
	}

	public void setTraducciones(List<WidgetTranslations> traducciones) {
		this.traducciones = traducciones;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public WidgetTranslations getTraduccion() {
		return traduccion;
	}

	public void setTraduccion(WidgetTranslations traduccion) {
		this.traduccion = traduccion;
	}

	public int getForm_id() {
		return form_id;
	}

	public void setForm_id(int form_id) {
		this.form_id = form_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public int getForm_customer() {
		return form_customer;
	}

	public void setForm_customer(int form_customer) {
		this.form_customer = form_customer;
	}

	public String getForm_lang() {
		return form_lang;
	}

	public void setForm_lang(String form_lang) {
		this.form_lang = form_lang;
	}

	public String getForm_label() {
		return form_label;
	}

	public void setForm_label(String form_label) {
		this.form_label = form_label;
	}

	public String getForm_translation() {
		return form_translation;
	}

	public void setForm_translation(String form_translation) {
		this.form_translation = form_translation;
	}

}
