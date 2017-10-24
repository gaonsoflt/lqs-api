package com.gaonsoft.lqs.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.gaonsoft.lqs.api.model.car.Driver;

@RepositoryRestResource(exported= false)
public interface DriverRepository extends CrudRepository<Driver, Long> {
	
	Driver findByFingerprint(byte[] fp);
}
