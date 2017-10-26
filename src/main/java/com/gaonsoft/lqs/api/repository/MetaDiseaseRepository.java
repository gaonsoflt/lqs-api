package com.gaonsoft.lqs.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.gaonsoft.lqs.api.model.meta.MetaDisease;

@RepositoryRestResource(exported= false)
public interface MetaDiseaseRepository extends CrudRepository<MetaDisease, String> {
}
