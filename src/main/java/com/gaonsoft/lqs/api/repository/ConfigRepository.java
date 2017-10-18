package com.gaonsoft.lqs.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.gaonsoft.lqs.api.model.config.Config;
import com.gaonsoft.lqs.api.model.config.ConfigKey;

@RepositoryRestResource(exported = false)
public interface ConfigRepository extends CrudRepository<Config, ConfigKey>{
}
