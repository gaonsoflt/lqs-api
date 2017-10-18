package com.gaonsoft.lqs.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.gaonsoft.lqs.api.model.farm.Farm;
import com.gaonsoft.lqs.api.model.farm.InlineLpr;

@RepositoryRestResource(excerptProjection = InlineLpr.class, exported= false)
public interface FarmRepository extends CrudRepository<Farm, Long>{
}
