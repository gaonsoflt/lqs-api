package com.gaonsoft.lqs.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.gaonsoft.lqs.api.model.disease.DiseaseWarnStage;

@RepositoryRestResource(exported= false)
public interface DiseaseWarnStageRepository extends CrudRepository<DiseaseWarnStage, Long>, DiseaseWarnStageRepositoryCustom {
}
