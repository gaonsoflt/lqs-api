package com.gaonsoft.lqs.api.service;

import java.util.List;

import com.gaonsoft.lqs.api.model.config.Config;
import com.gaonsoft.lqs.api.model.meta.MetaDisease;

public interface OpenService {
	
	public Config findAppAdminTel();
	
	Config findConfig(String cfgGroup, String cfgId);
	
	List<MetaDisease> findDiseaseMeta();
}