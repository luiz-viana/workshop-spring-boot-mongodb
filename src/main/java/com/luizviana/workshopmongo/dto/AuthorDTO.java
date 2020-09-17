package com.luizviana.workshopmongo.dto;

import java.io.Serializable;

import com.luizviana.workshopmongo.entities.User;


public class AuthorDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	
	
	public AuthorDTO() {
		
	}
	
	public AuthorDTO(User obj) {
		this.name = obj.getName();
		this.id = obj.getId();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
