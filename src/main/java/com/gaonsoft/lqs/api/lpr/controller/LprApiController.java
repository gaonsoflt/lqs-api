package com.gaonsoft.lqs.api.lpr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gaonsoft.lqs.api.lpr.service.LprService;

@RestController
@RequestMapping("/api/lpr")
public class LprApiController {

	@Autowired
	private LprService lprService;

	@RequestMapping(path = "/test", method = RequestMethod.GET)
	public ResponseEntity<?> open() {
		// return new ResponseEntity<>(lprService.openBarrier(20170002L),
		// HttpStatus.OK);
		return new ResponseEntity<>("lpr get", HttpStatus.OK);
	}
}
