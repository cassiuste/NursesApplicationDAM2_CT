package com.example.nurses;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Nurse {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String surname;
	private String user;
	private String pass;
	private String email;	

	public Nurse(String name, String surname, String user, String pass, String email) {
		this.name = name;
		this.surname = surname;
		this.user = user;
		this.pass = pass;
		this.email = email;
	}
	
	public Nurse() {
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
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
	
	
	public String getUser() {
		return user;
	}

	
	public void setUser(String user) {
		this.user = user;
	}
	
	
	public String getPass() {
		return pass;
	}
	

	public void setPass(String pass) {
		this.pass = pass;
	}
	

}
