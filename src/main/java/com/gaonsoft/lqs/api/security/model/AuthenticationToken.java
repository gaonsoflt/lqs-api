package com.gaonsoft.lqs.api.security.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
public class AuthenticationToken {
	private String userId;
	
	private String userNm;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	private String token;

	public AuthenticationToken(String userId, String userNm, Collection<? extends GrantedAuthority> authorities,
			String token) {
		super();
		this.userId = userId;
		this.userNm = userNm;
		this.authorities = authorities;
		this.token = token;
	}
	
	public AuthenticationToken(String userId, String token) {
		super();
		this.userId = userId;
		this.token = token;
	}
}
