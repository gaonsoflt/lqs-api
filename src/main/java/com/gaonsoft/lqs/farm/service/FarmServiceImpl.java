package com.gaonsoft.lqs.farm.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaonsoft.lqs.farm.model.Farm;
import com.gaonsoft.lqs.farm.repository.FarmRepository;

@Service
public class FarmServiceImpl implements FarmService {

	@Autowired
	private FarmRepository farmRepository;

	@Override
	public Farm findFarmById(long id) {
		return Optional.ofNullable(farmRepository.findOne(id)).orElseGet(Farm::new);
	}	
}
