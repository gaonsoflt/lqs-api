package com.gaonsoft.lqs.api.service;

import com.gaonsoft.lqs.api.model.config.Config;

public interface OpenService {
	
	public Config findAppAdminTel();
	
	Config findConfig(String cfgGroup, String cfgId);
}