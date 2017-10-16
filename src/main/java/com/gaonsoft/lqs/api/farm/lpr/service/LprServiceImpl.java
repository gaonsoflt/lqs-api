package com.gaonsoft.lqs.api.farm.lpr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaonsoft.lqs.api.farm.lpr.repository.LprRepository;
import com.gaonsoft.lqs.api.farm.repository.FarmRepository;
import com.gaonsoft.lqs.api.farm.model.Farm;

@Service
public class LprServiceImpl implements LprService {

	@Autowired
	private LprRepository lprRepository;
	
	@Autowired
	private FarmRepository farmRepository;
	
	@Override
	public boolean openBarrier(long id) {
		Farm f = farmRepository.findOne(id);
		System.out.println(f);
		// TODO: request action(open barrier), return result
		return true;
	}
}
