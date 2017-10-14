package com.gaonsoft.lqs.facility.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.gaonsoft.lqs.facility.model.Facility;
import com.gaonsoft.lqs.facility.model.InlineLpr;

import java.lang.Long;

@RepositoryRestResource(excerptProjection = InlineLpr.class)
public interface FacilityRepository extends CrudRepository<Facility, Long>{

	@Override
	@RestResource(exported = false)
	void delete(Facility arg0);
	
	@Override
	@RestResource(exported = false)
	void delete(Iterable<? extends Facility> arg0);

	@Override
	@RestResource(exported = false)
	void delete(Long arg0);

	@Override
	@RestResource(exported = false)
	void deleteAll();

	@Override
	@RestResource(exported = false)
	<S extends Facility> Iterable<S> save(Iterable<S> arg0);

	@Override
	@RestResource(exported = false)
	<S extends Facility> S save(S arg0);
}
