package es.ubiqua.compareme.model;

public class Ota {
	
	private int id;
	private String name;
	private String icon;
	private int quality = 100;
	
	public Ota(){
		
	}

	public Ota(String name){
		this.name = name;
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getIcon() {
		return icon;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public String toDBLogger(){
		return this.name+"|"+this.icon+"|"+this.id;
	}
}
