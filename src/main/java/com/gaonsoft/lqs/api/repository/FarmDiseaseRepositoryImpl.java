package com.gaonsoft.lqs.api.repository;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import com.gaonsoft.lqs.api.model.farm.FarmDisease;
import com.gaonsoft.lqs.api.model.farm.QFarmDisease;
import com.querydsl.jpa.JPQLQuery;

public class FarmDiseaseRepositoryImpl extends QueryDslRepositorySupport implements FarmDiseaseRepositoryCustom {
	protected Log logger = LogFactory.getLog(FarmDiseaseRepositoryImpl.class);

	public FarmDiseaseRepositoryImpl() {
		super(FarmDisease.class);
	}

	@Override
	public Page<FarmDisease> findFarmDisease(String id, Pageable pageable) {
		QFarmDisease farmDisease = QFarmDisease.farmDisease;

		JPQLQuery<FarmDisease> query = from(farmDisease);
		query.where(farmDisease.farmSeq.eq(Long.valueOf(id)));
		List<FarmDisease> result = getQuerydsl().applyPagination(pageable, query).fetch();
		return new PageImpl<>(result, pageable, query.fetchCount());
	}

	@Override
	public Page<FarmDisease> findFarmDiseaseByDiseased(String id, Pageable pageable) {
		QFarmDisease farmDisease = QFarmDisease.farmDisease;

		JPQLQuery<FarmDisease> query = from(farmDisease);
		query.where(farmDisease.farmSeq.eq(Long.valueOf(id))
				.and(farmDisease.terDt.isNull()));
		List<FarmDisease> result = getQuerydsl().applyPagination(pageable, query).fetch();
		return new PageImpl<>(result, pageable, query.fetchCount());
	}
}
