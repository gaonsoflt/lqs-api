package com.gaonsoft.lqs.api.security.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.gaonsoft.lqs.api.security.model.ApiUserRole;
import com.gaonsoft.lqs.api.security.model.ApiUserRoleKey;
import java.lang.Long;
import java.util.List;

@RepositoryRestResource(exported = false)
public interface ApiUserRoleRepository extends CrudRepository<ApiUserRole, ApiUserRoleKey>{
	
	List<ApiUserRole> findByUserSeq(@Param("userSeq") Long userSeq);
}
