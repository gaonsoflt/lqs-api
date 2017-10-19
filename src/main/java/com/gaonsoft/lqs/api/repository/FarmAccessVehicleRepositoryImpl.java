package com.gaonsoft.lqs.api.repository;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import com.gaonsoft.lqs.api.model.farm.FarmAccessVehicle;
import com.gaonsoft.lqs.api.model.farm.QFarmAccessVehicle;
import com.gaonsoft.lqs.api.vo.SearchFarmAccessVehicle;
import com.querydsl.jpa.JPQLQuery;

public class FarmAccessVehicleRepositoryImpl extends QueryDslRepositorySupport implements FarmAccessVehicleRepositoryCustom {
    protected Log logger = LogFactory.getLog(FarmAccessVehicleRepositoryImpl.class);

	public FarmAccessVehicleRepositoryImpl() {
		super(FarmAccessVehicle.class);
	}

	@Override
	public Page<FarmAccessVehicle> findAccessVehicleByFarmSeq(SearchFarmAccessVehicle searchFarmAccessVehicle, Pageable pageable) {
		QFarmAccessVehicle farmAccessVehicle = QFarmAccessVehicle.farmAccessVehicle;
//		
		JPQLQuery query = from(farmAccessVehicle);
		if(searchFarmAccessVehicle.getFrom() != null || searchFarmAccessVehicle.getTo() != null) {
			query.where(farmAccessVehicle.farmSeq.eq(searchFarmAccessVehicle.getFarmSeq())
				.and(farmAccessVehicle.inDt.goe(searchFarmAccessVehicle.getFrom())
				.and(farmAccessVehicle.inDt.loe(searchFarmAccessVehicle.getTo()))));
		} else {
			query.where(farmAccessVehicle.farmSeq.eq(searchFarmAccessVehicle.getFarmSeq()));
		}
		List<FarmAccessVehicle> result = getQuerydsl().applyPagination(pageable, query).fetch();
		return new PageImpl<>(result, pageable, query.fetchCount());
	}
}
