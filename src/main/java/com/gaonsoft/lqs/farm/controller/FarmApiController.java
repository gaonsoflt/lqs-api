//package com.gaonsoft.lqs.farm.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.gaonsoft.lqs.farm.model.Farm;
//import com.gaonsoft.lqs.farm.service.FarmService;
//
//@RestController
//@RequestMapping(value="farm")
//public class FarmApiController {
//
//	@Autowired
//	private FarmService farmService; 
//	
//	@RequestMapping(method=RequestMethod.GET)
//	public ResponseEntity<String> get() {
//		return new ResponseEntity<>("list", HttpStatus.OK);
//	}
//	
//	@RequestMapping(value="{id}", method=RequestMethod.GET)
//	public ResponseEntity<Farm> get(@PathVariable("id") Long id) {
//		return new ResponseEntity<>(farmService.findFarmById(id), HttpStatus.OK);
//	}
//}
