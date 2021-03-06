package com.gaonsoft.lqs.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.gaonsoft.lqs.api.model.facility.Facility;
import com.gaonsoft.lqs.api.model.facility.InlineLpr;

@RepositoryRestResource(excerptProjection = InlineLpr.class, exported = false)
public interface FacilityRepository extends CrudRepository<Facility, Long>{
}
