package com.gaonsoft.lqs.api.service;

import java.text.ParseException;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gaonsoft.lqs.api.model.FarmAccessVehicleSummary;
import com.gaonsoft.lqs.api.model.farm.Farm;
import com.gaonsoft.lqs.api.model.farm.FarmAccessVehicle;

public interface FarmService {
	
	public Farm findFarmById(long id);
	
	public Page<FarmAccessVehicle> findFarmAccessVehicles(String id, Pageable pageable) throws Exception;
	
	public Page<FarmAccessVehicle> findFarmAccessVehicles(String id, Date from, Date to, Pageable pageable) throws Exception;
	
	public FarmAccessVehicleSummary findFarmAccessVehiclesSummary(String id, Date from, Date to) throws ParseException;
	
	public boolean updatePassword(String id, String password);
	
	public boolean closeGate(String id);
	
	public boolean openGate(String id);
}
