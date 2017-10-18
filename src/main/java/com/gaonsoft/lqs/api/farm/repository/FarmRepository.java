package com.gaonsoft.lqs.api.farm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.gaonsoft.lqs.api.farm.model.Farm;
import com.gaonsoft.lqs.api.farm.model.InlineLpr;

@RepositoryRestResource(excerptProjection = InlineLpr.class, exported= false)
public interface FarmRepository extends CrudRepository<Farm, Long>{
}
