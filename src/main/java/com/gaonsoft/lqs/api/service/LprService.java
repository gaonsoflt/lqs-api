package com.gaonsoft.lqs.api.service;

import com.gaonsoft.lqs.api.model.lpr.LprStatus;

public interface LprService {
	public LprStatus save(LprStatus lprStatus) throws Exception;
}
