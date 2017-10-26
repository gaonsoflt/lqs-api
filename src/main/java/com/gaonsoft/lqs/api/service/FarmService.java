package com.gaonsoft.lqs.api.service;

import java.text.ParseException;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gaonsoft.lqs.api.model.FarmAccessVehicleSummary;
import com.gaonsoft.lqs.api.model.farm.Farm;
import com.gaonsoft.lqs.api.model.farm.FarmAccessVehicle;
import com.gaonsoft.lqs.api.model.farm.request.MyinfoVo;

public interface FarmService {
	
	public Farm findFarmById(long id) throws Exception;
	
	public Page<FarmAccessVehicle> findFarmAccessVehicles(String id, Pageable pageable) throws Exception;
	
	public Page<FarmAccessVehicle> findFarmAccessVehicles(String id, Date from, Date to, Pageable pageable) throws Exception;
	
	public FarmAccessVehicleSummary findFarmAccessVehiclesSummary(String id, Date from, Date to) throws ParseException;
	
	public void updatePassword(String id, String password) throws Exception;
	
	public Farm updateMyinfo(MyinfoVo vo) throws Exception;
	
	public boolean closeGate(String id);
	
	public boolean openGate(String id);
}
