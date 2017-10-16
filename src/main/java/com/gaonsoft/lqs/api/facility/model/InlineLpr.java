package com.gaonsoft.lqs.api.facility.model;

import java.util.List;

import org.springframework.data.rest.core.config.Projection;

import com.gaonsoft.lqs.api.lpr.model.Lpr;

@Projection(name = "inlineLpr", types = { Facility.class }) 
public interface InlineLpr {

	long getFacilitySeq();
	
	String getFacilityName();
	
	List<Lpr> getLpr(); 
}
