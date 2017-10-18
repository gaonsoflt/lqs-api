package com.gaonsoft.lqs.api.car.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.gaonsoft.lqs.api.car.model.Car;

@RepositoryRestResource(exported = false)
public interface CarRepository extends CrudRepository<Car, String>{
}
