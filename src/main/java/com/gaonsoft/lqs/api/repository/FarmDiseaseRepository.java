package com.gaonsoft.lqs.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.gaonsoft.lqs.api.model.farm.FarmDisease;

@RepositoryRestResource(exported= false)
public interface FarmDiseaseRepository extends CrudRepository<FarmDisease, Long>, FarmDiseaseRepositoryCustom {
}
