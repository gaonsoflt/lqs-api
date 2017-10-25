package com.gaonsoft.lqs.api.controller.app;

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
import com.gaonsoft.lqs.api.model.FarmAccessVehicleSummary;
import com.gaonsoft.lqs.api.model.farm.FarmAccessVehicle;
import com.gaonsoft.lqs.api.model.request.LprControlVo;
import com.gaonsoft.lqs.api.service.FarmService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;

@RestController
@Api(authorizations={ @Authorization("ROLE_FARM") }, description="앱(농장)용 api")
@RequestMapping("/api/app")
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
		@ApiImplicitParam(name="Authorization", value="authorization header", required=true, dataType="string", paramType="header"),
		@ApiImplicitParam(name="id", value="농장ID(로그인ID)", required=true, dataType="string", paramType="path"),
		@ApiImplicitParam(name="body", value="body", required=true, paramType="body", dataType="string", defaultValue="{\"password\":{password}}")
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
		@ApiImplicitParam(name="Authorization", value="authorization header", required=true, dataType="string", paramType="header"),
		@ApiImplicitParam(name="id", value="농장ID(로그인ID)", required=true, dataType="string", paramType="path")
	})
	@RequestMapping(value="/farms/{id}/gate", method=RequestMethod.POST)
	public ResponseEntity<?> controlGate(
			@PathVariable String id, 
			@ApiParam(required = true) @RequestBody LprControlVo body) {
		if(body.getAction() != null && body.getAction().toUpperCase().equals("OPEN")) {
			if(farmService.openGate(id)) {
				return new ResponseEntity<>("open", HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} else if(body.getAction() != null && body.getAction().toUpperCase().equals("CLOSE")) {
			if(farmService.closeGate(id)) {
				return new ResponseEntity<>("close", HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(
		value = "findAccessVehicle",
		notes = "농장 출입내역 조회",
		httpMethod = "GET",
		produces = "application/json",
		consumes = "application/json",
		protocols = "http",
		response = FarmAccessVehicle.class,
		hidden = false
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name="Authorization", value="authorization header", required=true, dataType="string", paramType="header"),
		@ApiImplicitParam(name = "id", value = "농장ID(로그인ID)", required = true, dataType = "string", paramType = "path"),
		@ApiImplicitParam(name = "from", value = "검색조건(시작)", required = false, dataType = "long", paramType = "query"),
		@ApiImplicitParam(name = "to", value = "검색조건(종료)", required = false, dataType = "long", paramType = "query"),
		@ApiImplicitParam(name = "page", required = false, dataType = "long", paramType = "query", value = "Results page you want to retrieve (0..N)"),
		@ApiImplicitParam(name = "size", required = false, dataType = "long", paramType = "query", value = "Number of records per page."),
		@ApiImplicitParam(name = "sort", required = false, dataType = "string", paramType = "query", allowMultiple = true, value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.")
	})
	@RequestMapping(value="/accessvehicles/search/farm/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> findAccessVehicle(
			@PathVariable String id,
			@Param(value="from") String from,
			@Param(value="to") String to,
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
			try {
				return new ResponseEntity<>(farmService.findFarmAccessVehicles(id, _from, _to, pageable), HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			try {
				return new ResponseEntity<>(farmService.findFarmAccessVehicles(id, pageable), HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
	
	@ApiOperation(
		value = "findAccessVehicleSummary",
		notes = "농장 출입내역요약 조회 (from/to가 없을 경우 당일만 조회)",
		httpMethod = "GET",
		produces = "application/json",
		consumes = "application/json",
		protocols = "http",
		response = FarmAccessVehicleSummary.class,
		hidden = false
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name="Authorization", value="authorization header", required=true, dataType="string", paramType="header"),
		@ApiImplicitParam(name = "id", value = "농장ID(로그인ID)", required = true, dataType = "string", paramType = "path"),
		@ApiImplicitParam(name = "from", value = "검색조건(시작)", required = false, dataType = "long", paramType = "query"),
		@ApiImplicitParam(name = "to", value = "검색조건(종료)", required = false, dataType = "long", paramType = "query")
	})
	@RequestMapping(value="/accessvehicles-summary/search/farm/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> findAccessVehicleSummary (
			@PathVariable String id,
			@Param(value="from") String from,
			@Param(value="to") String to) {
		
		try {
			if(from != null && to != null) {
				Date _from;
				Date _to;
				_from = DateUtil.calendarToDate(Long.valueOf(from));
				_to = DateUtil.calendarToDate(Long.valueOf(to));
				return new ResponseEntity<>(farmService.findFarmAccessVehiclesSummary(id, _from, _to), HttpStatus.OK);
			}
			Date now = new Date();
			return new ResponseEntity<>(farmService.findFarmAccessVehiclesSummary(id, now, now), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch(NumberFormatException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
