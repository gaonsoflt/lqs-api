package com.gaonsoft.lqs.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.gaonsoft.lqs.api.model.lpr.LprStatus;
import com.gaonsoft.lqs.api.model.lpr.LprStatusKey;

@RepositoryRestResource(exported = false)
public interface LprStatusRepository extends CrudRepository<LprStatus, LprStatusKey>{
}
