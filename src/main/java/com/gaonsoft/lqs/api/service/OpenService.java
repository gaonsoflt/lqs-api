package com.gaonsoft.lqs.api.service;

import java.util.List;

import com.gaonsoft.lqs.api.model.config.Config;
import com.gaonsoft.lqs.api.model.disease.DiseaseWarnStage;
import com.gaonsoft.lqs.api.model.disease.MetaDisease;

public interface OpenService {
	
	public Config findAppAdminTel() throws Exception;
	
	Config findConfig(String cfgGroup, String cfgId) throws Exception;
	
	List<MetaDisease> findDiseaseMeta() throws Exception;
	
	List<DiseaseWarnStage> findDiseaseWarnStage() throws Exception;
}