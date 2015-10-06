package com.nicodewet.oauth2.domain;

import org.hibernate.validator.constraints.NotEmpty;

public class Credentials {
	
	private String userName;
	private String password;
	
	public Credentials() {
		super();
	}

	@NotEmpty
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@NotEmpty
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
