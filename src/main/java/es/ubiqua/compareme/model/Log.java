package es.ubiqua.compareme.model;

import java.io.Serializable;

public class Log implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5920900913349526987L;
	public static final int ERROR = -1;
	public static final int INFO = 0;
	public static final int WARNING = 1;
	
	private int id;
	private int type;
	private String data;
	private long date;
	
	public Log(String data, int type){
		this.data = data;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public int getType() {
		return type;
	}

	public String getData() {
		return data;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setData(String data) {
		this.data = data;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}
}
