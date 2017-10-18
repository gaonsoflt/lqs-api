package com.gaonsoft.lqs.api.model.facility;

import java.util.List;

import org.springframework.data.rest.core.config.Projection;

import com.gaonsoft.lqs.api.model.lpr.Lpr;

@Projection(name = "inlineLpr", types = { Facility.class }) 
public interface InlineLpr {

	long getFacilitySeq();
	
	String getFacilityName();
	
	List<Lpr> getLpr(); 
}
