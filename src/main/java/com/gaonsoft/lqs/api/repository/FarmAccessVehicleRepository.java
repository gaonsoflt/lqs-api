package com.gaonsoft.lqs.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.gaonsoft.lqs.api.model.farm.FarmAccessVehicle;

@RepositoryRestResource(exported = false)
public interface FarmAccessVehicleRepository extends CrudRepository<FarmAccessVehicle, Long>, FarmAccessVehicleRepositoryCustom{
}
