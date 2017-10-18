package com.gaonsoft.lqs.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaonsoft.lqs.api.model.farm.Farm;
import com.gaonsoft.lqs.api.repository.FarmRepository;
import com.gaonsoft.lqs.api.repository.LprRepository;

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
