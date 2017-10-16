package com.gaonsoft.lqs.api.farm.lpr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gaonsoft.lqs.api.farm.lpr.service.LprService;

@RestController
@RequestMapping("lpr")
public class LprApiController {
	
	@Autowired
	private LprService lprService;
	
	@RequestMapping
	public ResponseEntity<?> open() {
		return new ResponseEntity<>(lprService.openBarrier(20170002L), HttpStatus.OK);
	}
}
