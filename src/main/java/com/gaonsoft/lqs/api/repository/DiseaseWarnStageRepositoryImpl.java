package com.gaonsoft.lqs.api.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import com.gaonsoft.lqs.api.model.disease.DiseaseWarnStage;
import com.gaonsoft.lqs.api.model.disease.QDiseaseWarnStage;
import com.querydsl.core.types.Projections;

public class DiseaseWarnStageRepositoryImpl extends QueryDslRepositorySupport
		implements DiseaseWarnStageRepositoryCustom {
	protected Log logger = LogFactory.getLog(DiseaseWarnStageRepositoryImpl.class);

	public DiseaseWarnStageRepositoryImpl() {
		super(DiseaseWarnStage.class);
	}

	@Override
	public List<DiseaseWarnStage> findDiseaseWarnStagesByMaxCreDtGroupDiseaseCode() {
		QDiseaseWarnStage stage = QDiseaseWarnStage.diseaseWarnStage;
		// TODO: can not make under code
		/*
		 	select
				A.*
			from 
				lqs_disease_warn_stage A
			right join(
				select 
					disease_code, max(cre_dt) as cre_dt
				from
					lqs_disease_warn_stage
				group by disease_code
			) B on A.disease_code = B.disease_code and A.cre_dt = B.cre_dt
		 */
		List<DiseaseWarnStage> subResult = from(stage)
				.select(Projections.constructor(DiseaseWarnStage.class, stage.diseaseCode, stage.creDt.max()))
				.groupBy(stage.diseaseCode)
				.fetch();
		
		List<DiseaseWarnStage> result = new ArrayList<>();
		for (DiseaseWarnStage diseaseWarnStage : subResult) {
			result.add(from(stage)
					.where(stage.diseaseCode.eq(diseaseWarnStage.getDiseaseCode())
							.and(stage.creDt.eq(diseaseWarnStage.getCreDt()))).fetchOne());
		}
		return result;
	}
}
