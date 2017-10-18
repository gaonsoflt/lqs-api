package com.gaonsoft.lqs.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.gaonsoft.lqs.api.model.car.Car;

@RepositoryRestResource(exported = false)
public interface CarRepository extends CrudRepository<Car, String>{
}
