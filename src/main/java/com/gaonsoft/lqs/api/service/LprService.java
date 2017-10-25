package com.gaonsoft.lqs.api.service;

import com.gaonsoft.lqs.api.model.lpr.LprStatus;
import com.gaonsoft.lqs.api.model.request.AccessVehicleVo;

public interface LprService {
	public LprStatus saveLprStatus(LprStatus lprStatus) throws Exception;

	public AccessVehicleVo saveAccessVehicle(AccessVehicleVo requestVo) throws Exception;
	
	public AccessVehicleVo updateAccessVehicle(AccessVehicleVo requestVo) throws Exception;
}
