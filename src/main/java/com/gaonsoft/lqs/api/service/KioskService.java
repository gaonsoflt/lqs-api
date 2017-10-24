package com.gaonsoft.lqs.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gaonsoft.lqs.api.model.car.DisfCar;
import com.gaonsoft.lqs.api.model.farm.Farm;
import com.gaonsoft.lqs.api.vo.SearchDisfCarVo;
import com.gaonsoft.lqs.api.vo.SearchFarmVo;
import com.gaonsoft.lqs.api.vo.request.SubmitVo;

public interface KioskService {
	public Page<Farm> findFarm(SearchFarmVo searchVo, Pageable pageable) throws Exception;
	
	public Page<DisfCar> findDisfCar(SearchDisfCarVo searchVo, Pageable pageable) throws Exception;
	
	public void saveForm(SubmitVo vo) throws Exception; 
}
