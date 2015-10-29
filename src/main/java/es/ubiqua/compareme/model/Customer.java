package es.ubiqua.compareme.model;

import java.io.Serializable;

public class Customer implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3903474166887088526L;
	private int id;
	private String name;
	private String identifier;
	private String username;
	private String password;
	private String musername;
	private String mpassword;
	private String mhost;
	private String contact;
	
	public Customer(){
		
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getIdentifier() {
		return identifier;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMusername() {
		return musername;
	}

	public String getMpassword() {
		return mpassword;
	}

	public String getMhost() {
		return mhost;
	}

	public void setMusername(String musername) {
		this.musername = musername;
	}

	public void setMpassword(String mpassword) {
		this.mpassword = mpassword;
	}

	public void setMhost(String mhost) {
		this.mhost = mhost;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	
}
