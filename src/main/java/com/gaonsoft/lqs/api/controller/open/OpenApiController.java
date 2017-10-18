package com.gaonsoft.lqs.api.controller.open;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gaonsoft.lqs.api.service.OpenService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/open")
public class OpenApiController {

	@Autowired
	private OpenService openService;
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public ResponseEntity<?> test() {
		return new ResponseEntity<>("test", HttpStatus.OK);
	}
	
	@ApiOperation(
		value = "findAppAdminTel",
		notes = "앱 담당자 연락처 조회",
		httpMethod = "GET",
		produces = "application/json",
		consumes = "application/json",
		protocols = "http",
		hidden = false
	)
	
	@RequestMapping(value="/configs/search/apptel", method=RequestMethod.GET)
	public ResponseEntity<?> findAppAdminTel() {
		return new ResponseEntity<>(openService.findAppAdminTel(), HttpStatus.OK);
	}
}
