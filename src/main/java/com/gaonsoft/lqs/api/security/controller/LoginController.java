package com.gaonsoft.lqs.api.security.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gaonsoft.lqs.api.security.model.AuthenticationToken;
import com.gaonsoft.lqs.api.security.model.LoginRequest;

@RestController
public class LoginController {
	@Autowired 
	AuthenticationManager authenticationManager;

	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String test() {
		return "test";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseEntity<AuthenticationToken> login(@RequestBody LoginRequest loginRequest, HttpSession session ) { 
		String username = loginRequest.getUsername(); 
		String password = loginRequest.getPassword(); 
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
		Authentication authentication = authenticationManager.authenticate(token); 
		SecurityContextHolder.getContext().setAuthentication(authentication); 
		session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext()); 
		AuthenticationToken t = new AuthenticationToken(username, session.getId());
		return new ResponseEntity<>(t, HttpStatus.OK);
	}
	
}
