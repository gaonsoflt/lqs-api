package com.gaonsoft.lqs.api.farm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.gaonsoft.lqs.api.farm.model.Farm;
import com.gaonsoft.lqs.api.farm.model.InlineLpr;

@RepositoryRestResource(excerptProjection = InlineLpr.class)
public interface FarmRepository extends CrudRepository<Farm, Long>{

	@Override
	@RestResource(exported = false)
	void delete(Farm arg0);
	
	@Override
	@RestResource(exported = false)
	void delete(Iterable<? extends Farm> arg0);

	@Override
	@RestResource(exported = false)
	void delete(Long arg0);

	@Override
	@RestResource(exported = false)
	void deleteAll();

	@Override
	@RestResource(exported = false)
	<S extends Farm> Iterable<S> save(Iterable<S> arg0);

	@Override
	@RestResource(exported = false)
	<S extends Farm> S save(S arg0);
}
