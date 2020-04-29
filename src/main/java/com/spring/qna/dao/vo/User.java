package com.spring.qna.dao.vo;

public class User {
	private long id;
	private String email;
	private String password;
	private String fullname;
	private String role;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String email, String password, String fullname, String role) {
		super();
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.role = role;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}
