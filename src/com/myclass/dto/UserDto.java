package com.myclass.dto;

public class UserDto {
	private int id;
	private String email;
	private String fullname;
	private String description;
	public UserDto() {
		
	}
	public UserDto(int id, String email, String fullname, String description) {
		super();
		this.id = id;
		this.email = email;
		this.fullname = fullname;
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
