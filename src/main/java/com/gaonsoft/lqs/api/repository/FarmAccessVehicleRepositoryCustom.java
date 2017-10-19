package com.gaonsoft.lqs.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gaonsoft.lqs.api.model.farm.FarmAccessVehicle;
import com.gaonsoft.lqs.api.vo.SearchFarmAccessVehicle;

public interface FarmAccessVehicleRepositoryCustom {
	Page<FarmAccessVehicle> findAccessVehicleByFarmSeq(SearchFarmAccessVehicle searchFarmAccessVehicle, Pageable pageable);
}
