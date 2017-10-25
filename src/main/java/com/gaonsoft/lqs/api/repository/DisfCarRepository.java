package com.gaonsoft.lqs.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.gaonsoft.lqs.api.model.car.DisfCar;

@RepositoryRestResource(exported = false)
public interface DisfCarRepository extends CrudRepository<DisfCar, Long>, DisfCarRepositoryCustom{
}
