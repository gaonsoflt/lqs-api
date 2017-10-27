package com.gaonsoft.lqs.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaonsoft.lqs.api.model.config.Config;
import com.gaonsoft.lqs.api.model.config.ConfigKey;
import com.gaonsoft.lqs.api.model.disease.DiseaseWarnStage;
import com.gaonsoft.lqs.api.model.disease.MetaDisease;
import com.gaonsoft.lqs.api.repository.ConfigRepository;
import com.gaonsoft.lqs.api.repository.DiseaseWarnStageRepository;
import com.gaonsoft.lqs.api.repository.MetaDiseaseRepository;

/***
 * 
 * @author woonsungbaek
 *
 */
@Service
public class OpenServiceImpl implements OpenService {

	
	@Autowired
	private ConfigRepository configRepository;
	
	@Autowired
	private MetaDiseaseRepository metaDiseaseRepository;
	
	@Autowired
	private DiseaseWarnStageRepository diseaseWarnStageRepository;
	
	@Override
	public Config findAppAdminTel() throws Exception {
		return findConfig("SYSTEM", "APP_ADM_TEL");
	}

	@Override
	public Config findConfig(String cfgGroup, String cfgId) throws Exception {
		return configRepository.findOne(new ConfigKey(cfgGroup, cfgId));
	}

	@Override
	public List<MetaDisease> findDiseaseMeta() throws Exception {
		return (List<MetaDisease>) metaDiseaseRepository.findAll();
	}

	@Override
	public List<DiseaseWarnStage> findDiseaseWarnStage() throws Exception {
		return diseaseWarnStageRepository.findDiseaseWarnStagesByMaxCreDtGroupDiseaseCode();
	}	
}
