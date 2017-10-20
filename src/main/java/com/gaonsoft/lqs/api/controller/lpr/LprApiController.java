package com.gaonsoft.lqs.api.controller.lpr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gaonsoft.lqs.api.model.lpr.LprStatus;
import com.gaonsoft.lqs.api.service.LprService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
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
		@ApiImplicitParam(name="Authorization", value="authorization header", required=true, dataType="string", paramType="header")
	})
	@RequestMapping(value="/lprs", method=RequestMethod.POST)
	public ResponseEntity<?> saveLprStatus(@ApiParam(required=true) @RequestBody LprStatus body) {
		try {
			return new ResponseEntity<>(lprService.save(body), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
