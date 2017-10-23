package com.gaonsoft.lqs.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.gaonsoft.lqs.api.model.system.Address;

@RepositoryRestResource(exported= false)
public interface AddressRepository extends CrudRepository<Address, Long> {
	Page<Address> findBySigunguCode(String sigunguCode, Pageable pageable);
	
	Page<Address> findByBcode(String bcode, Pageable pageable);
	
	Page<Address> findByRoadnameCode(String roadnameCode, Pageable pageable);
}
