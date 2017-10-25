package com.gaonsoft.lqs.api.service;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gaonsoft.lqs.api.model.farm.Farm;
import com.gaonsoft.lqs.api.model.farm.FarmAccessVehicle;

public interface FarmService {
	
	public Farm findFarmById(long id);
	
	public Page<FarmAccessVehicle> findFarmAccessVehicles(String id, Pageable pageable);
	
	public Page<FarmAccessVehicle> findFarmAccessVehicles(String id, Date from, Date to, Pageable pageable);
	
	public Page<FarmAccessVehicle> findFarmAccessVehicles2(Pageable pageable);
	
	public boolean updatePassword(String id, String password);
	
	public boolean closeGate(String id);
	
	public boolean openGate(String id);
}
