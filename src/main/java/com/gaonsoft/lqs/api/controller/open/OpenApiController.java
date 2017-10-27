package com.gaonsoft.lqs.api.controller.open;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gaonsoft.lqs.api.model.disease.DiseaseWarnStage;
import com.gaonsoft.lqs.api.model.disease.MetaDisease;
import com.gaonsoft.lqs.api.service.OpenService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(description="Open api")
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
		try {
			return new ResponseEntity<>(openService.findAppAdminTel(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(
		value = "findDiseaseMeta",
		notes = "질병 목록 조회",
		httpMethod = "GET",
		produces = "application/json",
		consumes = "application/json",
		protocols = "http",
		response = MetaDisease.class,
		hidden = false
	)
	@RequestMapping(value="/metas/search/disease", method=RequestMethod.GET)
	public ResponseEntity<?> findDiseaseMeta() {
		try {
			return new ResponseEntity<>(openService.findDiseaseMeta(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(
			value = "getDiseaseWarnStage",
			notes = "질병위기상태 조회",
			httpMethod = "GET",
			produces = "application/json",
			consumes = "application/json",
			protocols = "http",
			response = DiseaseWarnStage.class,
			hidden = false
	)
	@RequestMapping(value="/disease-warnstages", method=RequestMethod.GET)
	public ResponseEntity<?> getDiseaseWarnStage() { 
		try {
			return new ResponseEntity<>(openService.findDiseaseWarnStage(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
