package com.gaonsoft.lqs.api.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gaonsoft.lqs.api.model.SearchFarmAccessVehicleVo;
import com.gaonsoft.lqs.api.model.farm.FarmAccessVehicle;

public interface FarmAccessVehicleRepositoryCustom {
	Page<FarmAccessVehicle> findAccessVehicleByFarmSeq(SearchFarmAccessVehicleVo searchFarmAccessVehicle, Pageable pageable);
	
	Page<FarmAccessVehicle> findAccessVehicleByFarmSeqAndPeriod(SearchFarmAccessVehicleVo searchFarmAccessVehicle, Pageable pageable);
	
	FarmAccessVehicle findAccessVehicleByFarmSeqAndCarNoAndMaxCapDt(Long farmSeq, String carNo);
	
	FarmAccessVehicle findAccessVehicleByFarmSeqAndCarNoAndVisitPlanDt(Long farmSeq, String carNo, Date visitPlanDt);
}
