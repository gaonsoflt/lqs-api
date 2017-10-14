package com.gaonsoft.lqs.farm.model;

import java.util.List;

import org.springframework.data.rest.core.config.Projection;

import com.gaonsoft.lqs.farm.lpr.model.Lpr;

@Projection(name = "inlineLpr", types = { Farm.class }) 
public interface InlineLpr {

	long getFarmSeq();
	
	String getFarmName();
	
	List<Lpr> getLpr(); 
}
