package es.ubiqua.compareme.model;

import java.io.Serializable;

public class Ota implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2073208584927446777L;
	private int id;
	private String name;
	private String icon;
	private float quality = 100;
	private int queryOk = 0;
	private int queryNum = 0;
	
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
	
	public float getQuality() {
		try{
			quality = Float.valueOf(String.format("%.2g%n", (new Float(queryOk).floatValue()/new Float(queryNum).floatValue())*100)).floatValue();
		}catch(Exception e){
			quality = 0f;
		}
		return quality;
	}

	public String toDBLogger(){
		return this.name+"|"+this.icon+"|"+this.id;
	}

	public int getQueryOk() {
		return queryOk;
	}

	public void setQueryOk(int queryOk) {
		this.queryOk = queryOk;
	}

	public int getQueryNum() {
		return queryNum;
	}

	public void setQueryNum(int queryNum) {
		this.queryNum = queryNum;
	}
}
