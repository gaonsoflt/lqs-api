package com.gaonsoft.lqs.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gaonsoft.lqs.api.model.farm.FarmDisease;

public interface FarmDiseaseRepositoryCustom {
	
	Page<FarmDisease> findFarmDisease(String id, Pageable pageable);
	
	Page<FarmDisease> findFarmDiseaseByDiseased(String id, Pageable pageable);
}
