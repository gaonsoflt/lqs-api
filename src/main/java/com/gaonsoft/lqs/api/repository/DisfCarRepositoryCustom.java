package com.gaonsoft.lqs.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gaonsoft.lqs.api.model.SearchDisfCarVo;
import com.gaonsoft.lqs.api.model.car.DisfCar;

public interface DisfCarRepositoryCustom {
	Page<DisfCar> findDisfCarsByFacilitySeqAndCarNo(SearchDisfCarVo searchVo, Pageable pageable);
	
	Page<DisfCar> findDisfCarsByFacilitySeqAndCarNoAndBeforeAdminDt(SearchDisfCarVo searchDisfCarVo, Pageable pageable);
}
