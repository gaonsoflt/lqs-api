package com.gaonsoft.lqs.api.service;

import com.gaonsoft.lqs.api.model.lpr.LprStatus;
import com.gaonsoft.lqs.api.model.request.AccessVehicleVo;

public interface LprService {
	public LprStatus saveLprStatus(LprStatus lprStatus) throws Exception;

	public void saveAccessVehicle(AccessVehicleVo requestVo) throws Exception;
	
	public void updateAccessVehicle(AccessVehicleVo requestVo) throws Exception;
}
