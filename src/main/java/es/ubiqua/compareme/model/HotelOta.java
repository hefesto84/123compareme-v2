package es.ubiqua.compareme.model;

public class HotelOta {
	
	private int id;
	private int idHotel;
	private int idOta;
	private String name;
	
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

	
}
