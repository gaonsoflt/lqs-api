package com.gaonsoft.lqs.api.controller.lpr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gaonsoft.lqs.api.model.lpr.LprStatus;
import com.gaonsoft.lqs.api.model.request.AccessVehicleVo;
import com.gaonsoft.lqs.api.service.LprService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;

@RestController
@Api(authorizations={ @Authorization("ROLE_LPR") }, description="번호인식기서버용 api")
@RequestMapping("/api/lpr")
public class LprApiController {

	@Autowired
	private LprService lprService;

	@ApiOperation(
		value = "saveLprStatus",
		notes = "차단기 상태 저장",
		httpMethod = "POST",
		produces = "application/json",
		consumes = "application/json",
		protocols = "http",
		response = LprStatus.class,
		hidden = false
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name="Authorization", value="authorization header", required=true, dataType="string", paramType="header"),
		@ApiImplicitParam(name = "id", value = "인식기번호", required = true, dataType = "string", paramType = "path")
	})
	@RequestMapping(value="/lprs/{id}/status", method=RequestMethod.POST)
	public ResponseEntity<?> saveLprStatus(
			@PathVariable("id") String id,
			@ApiParam(required=true) @RequestBody LprStatus body) {
		try {
			body.setLprSeq(Long.valueOf(id));
			return new ResponseEntity<>(lprService.saveLprStatus(body), HttpStatus.OK);
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(
			value = "saveAccessVehicle",
			notes = "차량인식정보 저장",
			httpMethod = "POST",
			produces = "application/json",
			consumes = "application/json",
			protocols = "http",
			response = AccessVehicleVo.class,
			hidden = false
			)
	@ApiImplicitParams({
		@ApiImplicitParam(name="Authorization", value="authorization header", required=true, dataType="string", paramType="header"),
		@ApiImplicitParam(name = "id", value = "인식기번호", required = true, dataType = "string", paramType = "path")
	})
	@RequestMapping(value="/lprs/{id}/accessvehicle", method=RequestMethod.POST)
	public ResponseEntity<?> saveAccessVehicle(
			@PathVariable("id") String id,
			@ApiParam(required=true) @RequestBody AccessVehicleVo body) {
		try {
			body.setLprSeq(Long.valueOf(id));
			return new ResponseEntity<>(lprService.saveAccessVehicle(body), HttpStatus.OK);
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(
			value = "updateAccessVehicle",
			notes = "차량출입정보 저장",
			httpMethod = "PATCH",
			produces = "application/json",
			consumes = "application/json",
			protocols = "http",
			response = AccessVehicleVo.class,
			hidden = false
			)
	@ApiImplicitParams({
		@ApiImplicitParam(name="Authorization", value="authorization header", required=true, dataType="string", paramType="header"),
		@ApiImplicitParam(name = "id", value = "인식기번호", required = true, dataType = "string", paramType = "path")
	})
	@RequestMapping(value="/lprs/{id}/accessvehicle", method=RequestMethod.PATCH)
	public ResponseEntity<?> updateAccessVehicle(
			@PathVariable("id") String id,
			@ApiParam(required=true) @RequestBody AccessVehicleVo body) {
		try {
			body.setLprSeq(Long.valueOf(id));
			return new ResponseEntity<>(lprService.updateAccessVehicle(body), HttpStatus.OK);
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
