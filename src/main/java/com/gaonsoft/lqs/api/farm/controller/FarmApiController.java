package com.gaonsoft.lqs.api.farm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gaonsoft.lqs.api.farm.service.FarmService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Farm for API")
@RepositoryRestController
@RequestMapping("/farms")
public class FarmApiController {

	@Autowired
	private FarmService farmService; 
	
	@ApiOperation(
		value = "updatePassword",
		notes = "app 접속 패스워드 변경",
		httpMethod = "PATCH",
		produces = "application/json",
		consumes = "application/json",
		protocols = "http",
		hidden = false
	)
//	@ApiResponses({
//		@ApiResponse(code = 200, message = "OK"),
//		@ApiResponse(code = 400, message = "Bad Request"),
//		@ApiResponse(code = 404, message = "Not Found")
//	})
	@ApiImplicitParams({
		@ApiImplicitParam(name="id", value="App 로그인아이디(farm_seq)", required=true, dataType="long", paramType="path"),
		@ApiImplicitParam(name="body", value="body", required=true, paramType="path", defaultValue="{\"password\": {password}}")
	})
	@RequestMapping(value="/{id}/search/password", method=RequestMethod.PATCH)
	public ResponseEntity<?> updatePassword(
			@PathVariable long id, 
			@RequestBody Map<String, Object> body) {
		return new ResponseEntity<>(farmService.updatePassword(id, body.get("password").toString()), HttpStatus.OK);
	}
}
