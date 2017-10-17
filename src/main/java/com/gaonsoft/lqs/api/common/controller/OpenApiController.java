package com.gaonsoft.lqs.api.common.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/open")
public class OpenApiController {

	@RequestMapping(value="/test", method=RequestMethod.GET)
	public ResponseEntity<?> test() {
		return new ResponseEntity<>("test", HttpStatus.OK);
	}
	
}
