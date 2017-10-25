package com.gaonsoft.lqs.api.controller.kiosk;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gaonsoft.lqs.api.model.car.DisfCar;
import com.gaonsoft.lqs.api.model.car.Driver;
import com.gaonsoft.lqs.api.model.farm.Farm;
import com.gaonsoft.lqs.api.model.request.FarmVisitPlanVo;
import com.gaonsoft.lqs.api.model.request.FpAuthVo;
import com.gaonsoft.lqs.api.model.request.SearchDisfCarVo;
import com.gaonsoft.lqs.api.model.request.SearchFarmVo;
import com.gaonsoft.lqs.api.model.system.Address;
import com.gaonsoft.lqs.api.repository.AddressRepository;
import com.gaonsoft.lqs.api.service.KioskService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@RestController
@Api(authorizations={ @Authorization("ROLE_KIOSK") }, description="키오스크용 api")
@RequestMapping("/api/kiosk")
public class KioskApiController {

	@Autowired
	private KioskService kioskService; 
	
	@Autowired
	private AddressRepository addressRepository; 
	
	@ApiOperation(
			value = "loginDriver",
			notes = "운전자 신원 확인",
			httpMethod = "POST",
			produces = "application/json",
			consumes = "application/json",
			protocols = "http",
			response = Driver.class,
			hidden = false
		)
	@ApiResponses({
		@ApiResponse(code=500, message="Not matched fingerprint or internal server error")
	})
	@ApiImplicitParams({
		@ApiImplicitParam(name="Authorization", value="authorization header", required=true, dataType="string", paramType="header"),
	})
	@RequestMapping(value="/drivers/login", method=RequestMethod.POST)
	public ResponseEntity<?> loginDriver(@ApiParam(required = true) @RequestBody FpAuthVo body) {
		try {
			Driver driver = kioskService.loginDriver(body);
			if(driver != null && driver.getDriverSeq() != null) {
				return new ResponseEntity<>(driver, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(
		value = "findDisfCarByFacility",
		notes = "소독차량 조회",
		httpMethod = "GET",
		produces = "application/json",
		consumes = "application/json",
		protocols = "http",
		response = DisfCar.class,
		hidden = false
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name="Authorization", value="authorization header", required=true, dataType="string", paramType="header"),
		@ApiImplicitParam(name = "id", value = "소독시설ID", required = true, dataType = "string", paramType = "path"),
		@ApiImplicitParam(name = "no", value = "차량번호", required = false, dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "page", required = false, dataType = "long", paramType = "query", value = "Results page you want to retrieve (0..N)"),
		@ApiImplicitParam(name = "size", required = false, dataType = "long", paramType = "query", value = "Number of records per page."),
		@ApiImplicitParam(name = "sort", required = false, dataType = "string", paramType = "query", allowMultiple = true, value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.")
	})
	@RequestMapping(value="/disfcars/search/facility/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> findDisfCarByFacility(
			@PathVariable String id,
			@Param(value="no") String no,
			Pageable pageable) {
		
		try {
			Page<DisfCar> result = kioskService.findDisfCar(new SearchDisfCarVo(Long.valueOf(id), no, new Date()), pageable);
			if(result.getTotalElements() > 0) {
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(
			value = "findFarmByLocation",
			notes = "농장정보 조회",
			httpMethod = "GET",
			produces = "application/json",
			consumes = "application/json",
			protocols = "http",
			response = Farm.class,
			hidden = false
			)
	@ApiImplicitParams({
		@ApiImplicitParam(name="Authorization", value="authorization header", required=true, dataType="string", paramType="header"),
		@ApiImplicitParam(name = "sigungu", value = "시군구코드", required = true, dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "bcode", value = "동코드", required = false, dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "page", required = false, dataType = "long", paramType = "query", value = "Results page you want to retrieve (0..N)"),
		@ApiImplicitParam(name = "size", required = false, dataType = "long", paramType = "query", value = "Number of records per page."),
		@ApiImplicitParam(name = "sort", required = false, dataType = "string", paramType = "query", allowMultiple = true, value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.")
	})
	@RequestMapping(value="/farms/search/location", method=RequestMethod.GET)
	public ResponseEntity<?> findFarmByLocation(
			@Param(value="sigungu") String sigungu,
			@Param(value="bcode") String bcode,
			Pageable pageable) {
		
		try {
			return new ResponseEntity<>(kioskService.findFarm(new SearchFarmVo(sigungu, bcode, null), pageable), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(
		value = "findLocationBySigungu",
		notes = "농장위치 조회(시군구)",
		httpMethod = "GET",
		produces = "application/json",
		consumes = "application/json",
		protocols = "http",
		response = Address.class,
		hidden = false
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name="Authorization", value="authorization header", required=true, dataType="string", paramType="header"),
		@ApiImplicitParam(name = "sigungu", value = "시군구코드", required = true, dataType = "string", paramType = "path"),
		@ApiImplicitParam(name = "page", required = false, dataType = "long", paramType = "query", value = "Results page you want to retrieve (0..N)"),
		@ApiImplicitParam(name = "size", required = false, dataType = "long", paramType = "query", value = "Number of records per page."),
		@ApiImplicitParam(name = "sort", required = false, dataType = "string", paramType = "query", allowMultiple = true, value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.")
	})
	@RequestMapping(value="/locations/search/sigungu/{sigungu}", method=RequestMethod.GET)
	public ResponseEntity<?> findLocationBySigungu(
			@PathVariable("sigungu") String sigungu,
			Pageable pageable) {
		
		try {
			return new ResponseEntity<>(addressRepository.findBySigunguCode(sigungu, pageable), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(
			value = "findLocationBybcode",
			notes = "농장위치 조회(동)",
			httpMethod = "GET",
			produces = "application/json",
			consumes = "application/json",
			protocols = "http",
			response = Address.class,
			hidden = false
		)
	@ApiImplicitParams({
		@ApiImplicitParam(name="Authorization", value="authorization header", required=true, dataType="string", paramType="header"),
		@ApiImplicitParam(name = "bcode", value = "동코드", required = false, dataType = "string", paramType = "path"),
		@ApiImplicitParam(name = "page", required = false, dataType = "long", paramType = "query", value = "Results page you want to retrieve (0..N)"),
		@ApiImplicitParam(name = "size", required = false, dataType = "long", paramType = "query", value = "Number of records per page."),
		@ApiImplicitParam(name = "sort", required = false, dataType = "string", paramType = "query", allowMultiple = true, value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.")
	})
	@RequestMapping(value="/locations/search/bcode/{bcode}", method=RequestMethod.GET)
	public ResponseEntity<?> findLocationByBcode(
			@PathVariable("bcode") String bcode,
			Pageable pageable) {
		
		try {
			return new ResponseEntity<>(addressRepository.findByBcode(bcode, pageable), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(
		value = "saveFarmVisitPlan",
		notes = "방문예정농가 저장",
		httpMethod = "POST",
		produces = "application/json",
		consumes = "application/json",
		protocols = "http",
		hidden = false
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name="Authorization", value="authorization header", required=true, dataType="string", paramType="header"),
	})
	@RequestMapping(value="/farmvisitplans", method=RequestMethod.POST)
	public ResponseEntity<?> saveFarmVisitPlan(@ApiParam(required = true) @RequestBody FarmVisitPlanVo body) {
		try {
			kioskService.saveFarmVisitPlan(body);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
