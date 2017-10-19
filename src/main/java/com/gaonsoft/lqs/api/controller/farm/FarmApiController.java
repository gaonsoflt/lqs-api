package com.gaonsoft.lqs.api.controller.farm;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gaonsoft.lqs.api.common.util.DateUtil;
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
		@ApiImplicitParam(name="body", value="body", required=true, paramType="json", defaultValue="{\"action\": \"[open/close]\"}")
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
	
	@ApiOperation(
		value = "findAccessVehicle",
		notes = "농장 출입내역 조회",
		httpMethod = "GET",
		produces = "application/json",
		consumes = "application/json",
		protocols = "http",
		hidden = false
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "농장ID(로그인ID)", required = true, dataType = "String", paramType = "path"),
		@ApiImplicitParam(name = "from", value = "검색조건(시작)", required = false, dataType = "long", paramType = "query"),
		@ApiImplicitParam(name = "to", value = "검색조건(종료)", required = false, dataType = "long", paramType = "query"),
		@ApiImplicitParam(name = "page", required = false, dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N)"),
		@ApiImplicitParam(name = "size", required = false, dataType = "integer", paramType = "query", value = "Number of records per page."),
		@ApiImplicitParam(name = "sort", required = false, dataType = "string", paramType = "query", allowMultiple = true, value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.")
	})
	@RequestMapping(value="/farms/{id}/search/vehicles", method=RequestMethod.GET)
	public ResponseEntity<?> findAccessVehicle(
			@PathVariable String id,
			@Param(value="from") String from,
			@Param(value="to")String to,
			Pageable pageable) {
		
		if(from != null && to != null) {
			Date _from;
			Date _to;
			try {
				_from = DateUtil.calendarToDate(Long.valueOf(from));
				_to = DateUtil.calendarToDate(Long.valueOf(to));
			} catch(Exception e) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(farmService.findFarmAccessVehicles(id, _from, _to, pageable), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(farmService.findFarmAccessVehicles(id, pageable), HttpStatus.OK);
		}
	}
}
