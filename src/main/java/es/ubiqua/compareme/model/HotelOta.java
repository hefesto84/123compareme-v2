package es.ubiqua.compareme.model;

public class HotelOta {
	
	private int id;
	private int idHotel;
	private int idOta;
	private String name;
	private String otaName;
	private String currency;
	private Boolean actived;
	
	public HotelOta(){
		
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIdHotel() {
		return idHotel;
	}

	public int getIdOta() {
		return idOta;
	}

	public void setIdHotel(int idHotel) {
		this.idHotel = idHotel;
	}

	public void setIdOta(int idOta) {
		this.idOta = idOta;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Boolean getActived() {
		return actived;
	}

	public void setActived(Boolean actived) {
		this.actived = actived;
	}

	public String getOtaName() {
		return otaName;
	}

	public void setOtaName(String otaName) {
		this.otaName = otaName;
	}
	
}
