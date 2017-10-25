package com.gaonsoft.lqs.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gaonsoft.lqs.api.model.car.DisfCar;
import com.gaonsoft.lqs.api.model.request.SearchDisfCarVo;

public interface DisfCarRepositoryCustom {
	Page<DisfCar> findDisfCarsByFacilitySeqAndCarNo(SearchDisfCarVo searchVo, Pageable pageable);
	
	Page<DisfCar> findDisfCarsByFacilitySeqAndCarNoAndBeforeAdminDt(SearchDisfCarVo searchDisfCarVo, Pageable pageable);
}
