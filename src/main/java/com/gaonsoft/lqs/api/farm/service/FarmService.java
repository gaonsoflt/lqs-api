package com.gaonsoft.lqs.api.farm.service;

import com.gaonsoft.lqs.api.farm.model.Farm;

public interface FarmService {
	
	public Farm findFarmById(long id);
	
	public boolean updatePassword(long id, String password);
}
