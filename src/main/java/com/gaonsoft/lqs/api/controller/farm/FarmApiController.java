package com.gaonsoft.lqs.api.controller.farm;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gaonsoft.lqs.api.service.FarmService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/farm")
public class FarmApiController {

	@Autowired
	private FarmService farmService; 
	
	@ApiOperation(
		value = "updateMyinfo",
		notes = "app 접속 패스워드 변경",
		httpMethod = "PATCH",
		produces = "application/json",
		consumes = "application/json",
		protocols = "http",
		hidden = false
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name="id", value="농장ID(로그인ID)", required=true, dataType="String", paramType="path"),
		@ApiImplicitParam(name="body", value="body", required=true, paramType="path", defaultValue="{\"password\": {password}}")
	})
	@RequestMapping(value="/farms/{id}/myinfo", method=RequestMethod.PATCH)
	public ResponseEntity<?> updateMyinfo(
			@PathVariable String id, 
			@RequestBody Map<String, Object> body) {
		if(farmService.updatePassword(id, body.get("password").toString())) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(
		value = "controlGate",
		notes = "농장 출입차단기 동작요청",
		httpMethod = "POST",
		produces = "application/json",
		consumes = "application/json",
		protocols = "http",
		hidden = false
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name="id", value="농장ID(로그인ID)", required=true, dataType="String", paramType="path"),
		@ApiImplicitParam(name="body", value="body", required=true, paramType="path", defaultValue="{\"action\": \"[open/close]\"}")
	})
	@RequestMapping(value="/farms/{id}/search/gate", method=RequestMethod.POST)
	public ResponseEntity<?> controlGate(
			@PathVariable String id, 
			@RequestBody Map<String, Object> body) {
		if(body.containsKey("action") && body.get("action").toString().toUpperCase().equals("OPEN")) {
			if(farmService.openGate(id)) {
				return new ResponseEntity<>("open", HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} else if(body.containsKey("action") && body.get("action").toString().toUpperCase().equals("CLOSE")) {
			if(farmService.closeGate(id)) {
				return new ResponseEntity<>("close", HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<>("Wrong parameter", HttpStatus.BAD_REQUEST);
		}
	}
}
