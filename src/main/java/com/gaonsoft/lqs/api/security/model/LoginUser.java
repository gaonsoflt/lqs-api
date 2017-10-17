package com.gaonsoft.lqs.api.security.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class LoginUser implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long usernname;
	
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;
	
//	private boolean enabled;
	
	public LoginUser(Long username, String password, Collection<? extends GrantedAuthority> authorities) {
		this.usernname = username;
		this.password = password;
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getUsername() {
		return usernname.toString();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
//		return this.enabled;
	} 
}
