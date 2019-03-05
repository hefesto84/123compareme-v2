package es.ubiqua.compareme.backend.actions;

import java.util.List;

import es.ubiqua.compareme.manager.ModelCSSWidgetManager;
import es.ubiqua.compareme.model.ModelCSSWidget;

public class WidgetCustomCSSBackendAction extends BaseBackendAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ModelCSSWidget> estilos;
	private ModelCSSWidgetManager cssManager = new ModelCSSWidgetManager();
	private int id;
	private ModelCSSWidget widget;
	private int form_id;
	private String form_identifier;
	private String form_css;
	private String status;

	public String execute(){
		
		if(!isLogged()){ return ERROR; }
		
		estilos = cssManager.list(getLoggedCustomer());
		
		return SUCCESS;
	}
	
	public String newAction(){
		
		return SUCCESS;
		
	}
	
	public String addAction(){
		
		widget = new ModelCSSWidget();
		widget.setCustomerId(getLoggedCustomer().getIdentifier());
		widget.setIdentifier(form_identifier);
		widget.setCss(form_css);
		
		cssManager.add(widget);
		
		status = "ok";
		
		return SUCCESS;
	}
	
	public String editAction(){
		
		if(!isLogged()){ return ERROR; }
		
		widget = new ModelCSSWidget();
		widget.setId(id);
		widget = cssManager.get(widget);
		
		if(!isCorrectCustomer(widget.getCustomerId())){ return ERROR; }
		
		return SUCCESS;
	}
	
	public String modifyAction(){
		
		if(!isLogged()){ return ERROR; }
		
		widget = new ModelCSSWidget();
		widget.setId(form_id);
		widget = cssManager.get(widget);
		
		if(!isCorrectCustomer(widget.getCustomerId())){ return ERROR; }
		
		widget.setIdentifier(form_identifier);
		widget.setCss(form_css);
		cssManager.update(widget);
		
		status = "ok";
		
		return SUCCESS;
	}
	
	public String deleteAction (){
		
		if(!isLogged()){ return ERROR; }
		
		widget = new ModelCSSWidget();
		widget.setId(id);
		widget = cssManager.get(widget);
		
		if(!isCorrectCustomer(widget.getCustomerId())){ return ERROR; }
		
		cssManager.delete(widget);
		
		status = "ok";
		
		return SUCCESS;
	}

	public List<ModelCSSWidget> getEstilos() {
		return estilos;
	}

	public void setEstilos(List<ModelCSSWidget> estilos) {
		this.estilos = estilos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ModelCSSWidget getWidget() {
		return widget;
	}

	public void setWidget(ModelCSSWidget widget) {
		this.widget = widget;
	}

	public int getForm_id() {
		return form_id;
	}

	public void setForm_id(int form_id) {
		this.form_id = form_id;
	}

	public String getForm_identifier() {
		return form_identifier;
	}

	public void setForm_identifier(String form_identifier) {
		this.form_identifier = form_identifier;
	}

	public String getForm_css() {
		return form_css;
	}

	public void setForm_css(String form_css) {
		this.form_css = form_css;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
