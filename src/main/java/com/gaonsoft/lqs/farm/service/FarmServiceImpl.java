package com.gaonsoft.lqs.farm.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaonsoft.lqs.common.PwdEncryptor;
import com.gaonsoft.lqs.farm.model.Farm;
import com.gaonsoft.lqs.farm.repository.FarmRepository;

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
		Farm farm = new Farm();
		farm.setFarmSeq(id);
		farm.setPassword(PwdEncryptor.getEncrypt(password));
		farmRepository.save(farm);
		return true;
	}	
}
