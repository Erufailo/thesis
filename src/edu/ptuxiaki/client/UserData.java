package edu.ptuxiaki.client;

import java.io.Serializable;

public class UserData implements Serializable{

	/**
	 * Class that holds the Data of the user
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	

	private String name; 
	private String surname;
	private String email;
    private String password;
	private String tel;
	private String token;
	private String sID;
	private String role;
	
	private  UserData _instance = null;;
	
	
	public UserData() {
		this.id=0;
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
	
	
	
	public UserData(String name,String surname, String email,String tel,String role) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.tel = tel;
		this.role= role;
	}
	
	public UserData(int id,String name,String surname, String email,String tel,String role) {
		super();
		this.id= id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.tel = tel;
		this.role= role;
	}

	public void showAll(){
		System.out.println(name +" "+ surname +" "+ email +" "+ tel +" "+ token +" "+ sID +" "+ role);
	}
	
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
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
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
