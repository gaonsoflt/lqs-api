package com.gaonsoft.lqs.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaonsoft.lqs.api.model.config.Config;
import com.gaonsoft.lqs.api.model.config.ConfigKey;
import com.gaonsoft.lqs.api.repository.ConfigRepository;

/***
 * 
 * @author woonsungbaek
 *
 */
@Service
public class OpenServiceImpl implements OpenService {

	
	@Autowired
	private ConfigRepository configRepository;
	
	@Override
	public Config findAppAdminTel() {
		return findConfig("SYSTEM", "APP_ADM_TEL");
	}

	@Override
	public Config findConfig(String cfgGroup, String cfgId) {
		return configRepository.findOne(new ConfigKey(cfgGroup, cfgId));
	}	
}
