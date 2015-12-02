package es.ubiqua.compareme.model;

public class Currency {
	
	private int id;
	private String currency;
	private int idOta;
	
	public Currency(){
		
	}

	public int getId() {
		return id;
	}

	public String getCurrency() {
		return currency;
	}

	public int getIdOta() {
		return idOta;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setIdOta(int idOta) {
		this.idOta = idOta;
	}
	
	
}
