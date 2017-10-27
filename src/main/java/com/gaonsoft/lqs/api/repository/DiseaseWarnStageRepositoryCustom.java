package com.gaonsoft.lqs.api.repository;

import java.util.List;

import com.gaonsoft.lqs.api.model.disease.DiseaseWarnStage;

public interface DiseaseWarnStageRepositoryCustom {
	
	List<DiseaseWarnStage> findDiseaseWarnStagesByMaxCreDtGroupDiseaseCode();
}
