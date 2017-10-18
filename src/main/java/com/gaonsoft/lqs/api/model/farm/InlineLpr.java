package com.gaonsoft.lqs.api.model.farm;

import java.util.List;

import org.springframework.data.rest.core.config.Projection;

import com.gaonsoft.lqs.api.model.lpr.Lpr;

@Projection(name = "inlineLpr", types = { Farm.class }) 
public interface InlineLpr {

	long getFarmSeq();
	
	String getFarmName();
	
	List<Lpr> getLpr(); 
}
