package com.gaonsoft.lqs.farm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gaonsoft.lqs.farm.service.FarmService;

@RepositoryRestController
public class FarmController {

	@Autowired
	private FarmService farmService; 
	
	@RequestMapping(value="/farms/{id}/search/password", method=RequestMethod.PATCH)
	public ResponseEntity<?> updatePassword(@PathVariable long id, @RequestBody Map<String, Object> body) {
		return new ResponseEntity<>(farmService.updatePassword(id, body.get("password").toString()), HttpStatus.OK);
	}
}
