package com.gaonsoft.lqs.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaonsoft.lqs.api.common.PwdEncryptor;
import com.gaonsoft.lqs.api.model.farm.Farm;
import com.gaonsoft.lqs.api.model.user.ApiUser;
import com.gaonsoft.lqs.api.repository.ApiUserRepository;
import com.gaonsoft.lqs.api.repository.FarmRepository;

/***
 * 
 * @author woonsungbaek
 *
 */
@Service
public class FarmServiceImpl implements FarmService {
	
	@Autowired
	private FarmRepository farmRepository;
	
	@Autowired
	private ApiUserRepository apiUserRepository;

	@Override
	public Farm findFarmById(long id) {
		return Optional.ofNullable(farmRepository.findOne(id)).orElseGet(Farm::new);
	}

	/**
	 * change app login password(id=farm_seq)
	 */
	@Override
	public boolean updatePassword(String id, String password) {
		ApiUser user = apiUserRepository.findOne(id);
		user.setPassword(new PwdEncryptor().encode(password));
		apiUserRepository.save(user);
		return true;
	}

	@Override
	public boolean closeGate(String id) {
		// TODO: call api(close gate) to lpr server
		return true;
	}

	@Override
	public boolean openGate(String id) {
		// TODO: call api(open gate) to lpr server
		return true;
	}	
}
