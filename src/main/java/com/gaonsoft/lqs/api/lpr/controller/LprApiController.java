package com.gaonsoft.lqs.api.lpr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gaonsoft.lqs.api.lpr.service.LprService;

@RepositoryRestController
@RequestMapping("lpr")
public class LprApiController {
	
	@Autowired
	private LprService lprService;
	
	@RequestMapping
	public ResponseEntity<?> open() {
		return new ResponseEntity<>(lprService.openBarrier(20170002L), HttpStatus.OK);
	}
}
