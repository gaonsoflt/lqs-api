package com.gaonsoft.lqs.api.lpr.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.gaonsoft.lqs.api.lpr.model.Lpr;

@RepositoryRestResource(exported = false)
public interface LprRepository extends CrudRepository<Lpr, Long>{
}
