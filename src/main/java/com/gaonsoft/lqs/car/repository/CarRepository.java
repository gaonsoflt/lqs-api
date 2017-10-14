package com.gaonsoft.lqs.car.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.gaonsoft.lqs.car.model.Car;

public interface CarRepository extends CrudRepository<Car, String>{

	@Override
	@RestResource(exported = false)
	void delete(Car arg0);
	
	@Override
	@RestResource(exported = false)
	void delete(Iterable<? extends Car> arg0);

	@Override
	@RestResource(exported = false)
	void delete(String arg0);

	@Override
	@RestResource(exported = false)
	void deleteAll();

	@Override
	@RestResource(exported = false)
	<S extends Car> Iterable<S> save(Iterable<S> arg0);

	@Override
	@RestResource(exported = false)
	<S extends Car> S save(S arg0);
}
