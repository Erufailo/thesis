package edu.ptuxiaki.client;

import java.io.Serializable;

public class UserData implements Serializable{

	/**
	 * Class that holds the Data of the user
	 */
	private static final long serialVersionUID = 1L;
	private String name; 
	private String surname;
	private String email;
	private String password;
	private String tel;
	private String token;
	private String sID;
	private  UserData _instance = null;;
	
	
	public UserData() {
		this.name = null;
		this.surname = null;
		this.email = null;
		this.password = null;
		this.tel = null;
		this.token = null;
		this.sID = null;
	}	
	
	public UserData getInstance(){
		if(_instance == null){
			_instance = new UserData();
		}
		return _instance;
	}
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getsID() {
		return sID;
	}

	public void setsID(String sID) {
		this.sID = sID;
	}
	
	
}
