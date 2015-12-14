package es.ubiqua.compareme.backend.actions;

import java.util.List;

import es.ubiqua.compareme.manager.ModelHTMLWidgetManager;
import es.ubiqua.compareme.model.ModelHTMLWidget;

public class WidgetCustomHTMLBackendAction extends BaseBackendAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ModelHTMLWidget> modelos;
	private ModelHTMLWidgetManager htmlManager = new ModelHTMLWidgetManager();
	private int id;
	private ModelHTMLWidget widget;
	private int form_id;
	private String form_identifier;
	private String form_html;
	private String status;

	public String execute(){
		
		if(!isLogged()){ return ERROR; }
		
		modelos = htmlManager.list(getLoggedCustomer());
		
		return SUCCESS;
	}
	
	public String newAction(){
		
		return SUCCESS;
		
	}
	
	public String addAction(){
		
		widget = new ModelHTMLWidget();
		widget.setCustomerId(getLoggedCustomer().getIdentifier());
		widget.setIdentifier(form_identifier);
		widget.setModel(form_html);
		
		htmlManager.add(widget);
		
		status = "ok";
		
		return SUCCESS;
	}
	
	public String editAction(){
		
		if(!isLogged()){ return ERROR; }
		
		widget = new ModelHTMLWidget();
		widget.setId(id);
		widget = htmlManager.get(widget);
		
		if(!isCorrectCustomer(widget.getCustomerId())){ return ERROR; }
		
		return SUCCESS;
	}
	
	public String modifyAction(){
		
		if(!isLogged()){ return ERROR; }
		
		widget = new ModelHTMLWidget();
		widget.setId(form_id);
		widget = htmlManager.get(widget);
		
		if(!isCorrectCustomer(widget.getCustomerId())){ return ERROR; }
		
		widget.setIdentifier(form_identifier);
		widget.setModel(form_html);
		htmlManager.update(widget);
		
		status = "ok";
		
		return SUCCESS;
	}
	
	public String deleteAction (){
		
		if(!isLogged()){ return ERROR; }
		
		widget = new ModelHTMLWidget();
		widget.setId(id);
		widget = htmlManager.get(widget);
		
		if(!isCorrectCustomer(widget.getCustomerId())){ return ERROR; }
		
		htmlManager.delete(widget);
		
		status = "ok";
		
		return SUCCESS;
	}

	public List<ModelHTMLWidget> getModelos() {
		return modelos;
	}

	public void setModelos(List<ModelHTMLWidget> modelos) {
		this.modelos = modelos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ModelHTMLWidget getWidget() {
		return widget;
	}

	public void setWidget(ModelHTMLWidget widget) {
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

	public String getForm_html() {
		return form_html;
	}

	public void setForm_html(String form_html) {
		this.form_html = form_html;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
