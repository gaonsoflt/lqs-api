package com.gaonsoft.lqs.api.service;

import com.gaonsoft.lqs.api.model.farm.Farm;

public interface FarmService {
	
	public Farm findFarmById(long id);
	
	public boolean updatePassword(long id, String password);
}
