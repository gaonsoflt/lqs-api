package com.gaonsoft.lqs.api.farm.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaonsoft.lqs.api.common.PwdEncryptor;
import com.gaonsoft.lqs.api.farm.repository.FarmRepository;
import com.gaonsoft.lqs.api.farm.model.Farm;

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
		farm.setPassword(PwdEncryptor.getEncrypt(password));
		farmRepository.save(farm);
		return true;
	}	
}
