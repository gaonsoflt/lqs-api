package com.gaonsoft.lqs.api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.gaonsoft.lqs.api.model.user.ApiUserRole;
import com.gaonsoft.lqs.api.model.user.ApiUserRoleKey;

@RepositoryRestResource(exported = false)
public interface ApiUserRoleRepository extends CrudRepository<ApiUserRole, ApiUserRoleKey>{
	
	List<ApiUserRole> findByUserId(@Param("userId") String userId);
}
