package com.gaonsoft.lqs.api.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gaonsoft.lqs.api.security.model.AccessToken;
import com.gaonsoft.lqs.api.farm.model.Farm;

@RestController
public class LoginController {
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String test() {
		return "test";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseEntity<AccessToken> login(@RequestBody Farm loginInfo) {
		AccessToken token = null;
		return new ResponseEntity<>(token, HttpStatus.OK);
	}
	
}
