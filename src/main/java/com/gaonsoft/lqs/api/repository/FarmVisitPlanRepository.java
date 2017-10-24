package com.gaonsoft.lqs.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.gaonsoft.lqs.api.model.farm.FarmVisitPlan;
import com.gaonsoft.lqs.api.model.farm.FarmVisitPlanKey;

@RepositoryRestResource(exported= false)
public interface FarmVisitPlanRepository extends CrudRepository<FarmVisitPlan, FarmVisitPlanKey> {
}
