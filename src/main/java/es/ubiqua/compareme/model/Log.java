package es.ubiqua.compareme.model;

import java.sql.Date;

public class Log {
	
	public static int ERROR = -1;
	public static int INFO = 0;
	public static int WARNING = 1;
	
	private int id;
	private int type;
	private String data;
	private Date date;
	
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
