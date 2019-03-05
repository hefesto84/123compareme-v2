package es.ubiqua.compareme.model;

public class Exchange {
	
	private int id;
	private String currency;
	private float value;
	private String name;
	
	public Exchange(){
		
	}

	public Exchange(String currency, float value, String name){
		this.currency = currency;
		this.value = value;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}

	public String getCurrency() {
		return currency;
	}

	public float getValue() {
		return value;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
