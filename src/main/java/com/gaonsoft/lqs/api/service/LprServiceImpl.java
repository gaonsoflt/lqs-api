package com.gaonsoft.lqs.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaonsoft.lqs.api.model.lpr.LprStatus;
import com.gaonsoft.lqs.api.repository.LprStatusRepository;

@Service
public class LprServiceImpl implements LprService {

	@Autowired
	private LprStatusRepository lprStatusRepository;

	@Override
	public LprStatus save(LprStatus lprStatus) throws Exception {
		return lprStatusRepository.save(lprStatus);
	}
}
