package com.gaonsoft.lqs.api.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.gaonsoft.lqs.api.model.request.LoginVo;
import com.gaonsoft.lqs.api.model.user.AccessToken;

public interface LoginService extends UserDetailsService{
	public PasswordEncoder getPasswordEncoder();
	
	public ResponseEntity<AccessToken> doLogin(LoginVo vo) throws Exception;
}
