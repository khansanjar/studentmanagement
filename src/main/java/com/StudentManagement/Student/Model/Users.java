package com.StudentManagement.Student.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Users {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(nullable=false,unique=true)
	private String username;
	 
	@Column(nullable=false)
	private String password;

	private boolean isastive;
	
	//Gatter and satter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public boolean isIsastive() {
		return isastive;
	}

	public void setIsastive(boolean isastive) {
		this.isastive = isastive;
	}

}
