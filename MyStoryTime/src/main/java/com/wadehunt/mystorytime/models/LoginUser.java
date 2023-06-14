package com.wadehunt.mystorytime.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class LoginUser {
	
	@Email
	private String email;
	
	@Size(min=8, message="")
	private String password;
	
	public LoginUser() {}

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
	};	
}