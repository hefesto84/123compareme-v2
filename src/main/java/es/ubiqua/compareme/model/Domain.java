package es.ubiqua.compareme.model;

public class Domain {
	
	public int getId() {
		return id;
	}

	public String getCurrency() {
		return currency;
	}

	public String getDomain() {
		return domain;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	private int id;
	private String currency;
	private String domain;
	private String format;
	
	public Domain(){
		
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
}
