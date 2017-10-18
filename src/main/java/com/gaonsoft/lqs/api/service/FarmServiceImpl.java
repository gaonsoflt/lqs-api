package com.gaonsoft.lqs.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaonsoft.lqs.api.common.PwdEncryptor;
import com.gaonsoft.lqs.api.model.farm.Farm;
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

	@Override
	public Farm findFarmById(long id) {
		return Optional.ofNullable(farmRepository.findOne(id)).orElseGet(Farm::new);
	}

	/**
	 * change app login password(id=farm_seq)
	 */
	@Override
	public boolean updatePassword(long id, String password) {
		Farm farm = farmRepository.findOne(id);
		farm.setPassword(new PwdEncryptor().encode(password));
		farmRepository.save(farm);
		return true;
	}	
}
