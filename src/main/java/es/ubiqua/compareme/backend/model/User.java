package es.ubiqua.compareme.backend.model;

public class User {
	
	private int id;
	private int customerId;
	private String email;
	
	public User(){
		
	}

	public int getId() {
		return id;
	}

	public int getCustomerId() {
		return customerId;
	}

	public String getEmail() {
		return email;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
