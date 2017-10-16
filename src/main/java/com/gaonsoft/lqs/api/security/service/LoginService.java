package com.gaonsoft.lqs.api.security.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface LoginService extends UserDetailsService{
	public PasswordEncoder getPasswordEncoder();
}
