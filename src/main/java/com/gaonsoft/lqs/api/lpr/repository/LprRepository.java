package com.gaonsoft.lqs.api.lpr.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.gaonsoft.lqs.api.lpr.model.Lpr;

@RepositoryRestResource
public interface LprRepository extends CrudRepository<Lpr, Long>{

	@Override
	@RestResource(exported = false)
	void delete(Lpr arg0);
	
	@Override
	@RestResource(exported = false)
	void delete(Iterable<? extends Lpr> arg0);

	@Override
	@RestResource(exported = false)
	void delete(Long arg0);

	@Override
	@RestResource(exported = false)
	void deleteAll();

	@Override
	@RestResource(exported = false)
	<S extends Lpr> Iterable<S> save(Iterable<S> arg0);

	@Override
	@RestResource(exported = false)
	<S extends Lpr> S save(S arg0);
	
//	@RestResource(path = "location", rel = "location")
//	List<Lpr> findByLocSeq(Long locSeq);
}
