package es.ubiqua.compareme.backend.actions;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

public class FastPriceCheckBackendAction extends BaseBackendAction{

	private List<String> data = new ArrayList<String>();
	
	private static final long serialVersionUID = 1L;

	public String execute(){
		data.add("dasdad");
		data.add("DSADAD000");
		System.out.println("DSADASD");
		return SUCCESS;
	}

	public List<String> getData() {
		return data;
	}

	public void setData(List<String> data) {
		this.data = data;
	}
}
