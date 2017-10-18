package com.gaonsoft.lqs.api.facility.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.gaonsoft.lqs.api.facility.model.Facility;
import com.gaonsoft.lqs.api.facility.model.InlineLpr;

@RepositoryRestResource(excerptProjection = InlineLpr.class, exported = false)
public interface FacilityRepository extends CrudRepository<Facility, Long>{
}
