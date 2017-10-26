package com.gaonsoft.lqs.api.controller.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gaonsoft.lqs.api.model.request.LoginVo;
import com.gaonsoft.lqs.api.model.user.AccessToken;
import com.gaonsoft.lqs.api.service.LoginService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(description="앱(농장)용 api")
@RequestMapping("/api/app")
public class LoginApiController {

	@Autowired
	private LoginService loginService;
	
	@ApiOperation(
		value = "login",
		notes = "앱 로그인",
		httpMethod = "POST",
		produces = "application/json",
		consumes = "application/json",
		response = AccessToken.class,
		protocols = "http",
		hidden = false
	)
	@ApiResponses({
		@ApiResponse(code = 401, message = "Unauthorized")
	})
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseEntity<?> controlGate(@ApiParam(required = true) @RequestBody LoginVo body) {
		if(body.getId() != null && body.getPassword() != null && body.getMobile_token() != null) {
			try {
				return loginService.doLogin(body);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
