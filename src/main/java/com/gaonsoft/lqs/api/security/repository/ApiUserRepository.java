package com.gaonsoft.lqs.api.security.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.gaonsoft.lqs.api.security.model.ApiUser;

@RepositoryRestResource(exported= false)
public interface ApiUserRepository extends CrudRepository<ApiUser, String>{
}
