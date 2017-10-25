package com.gaonsoft.lqs.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gaonsoft.lqs.api.model.SearchDisfCarVo;
import com.gaonsoft.lqs.api.model.SearchFarmVo;
import com.gaonsoft.lqs.api.model.car.DisfCar;
import com.gaonsoft.lqs.api.model.car.Driver;
import com.gaonsoft.lqs.api.model.farm.Farm;
import com.gaonsoft.lqs.api.model.request.FarmVisitPlanVo;
import com.gaonsoft.lqs.api.model.request.FpAuthVo;

public interface KioskService {
	public Page<Farm> findFarm(SearchFarmVo searchVo, Pageable pageable) throws Exception;
	
	public Page<DisfCar> findDisfCar(SearchDisfCarVo searchVo, Pageable pageable) throws Exception;
	
	public void saveFarmVisitPlan(FarmVisitPlanVo vo) throws Exception; 
	
	public Driver loginDriver(FpAuthVo vo) throws Exception; 
}
