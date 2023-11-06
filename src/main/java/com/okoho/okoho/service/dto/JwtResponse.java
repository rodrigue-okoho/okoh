package com.okoho.okoho.service.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class JwtResponse  implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private String token;
	private String type = "Bearer";
	private String id;
	private String username;
	private String user_type;
	private String email;
	private Set<String> roles;

	public JwtResponse(String accessToken, String id, String username, String email, Set<String> roles,String user_type) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
		this.user_type=user_type;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<String> getRoles() {
		return roles;
	}
}
