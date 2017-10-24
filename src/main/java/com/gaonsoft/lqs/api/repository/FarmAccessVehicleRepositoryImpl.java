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
import com.gaonsoft.lqs.api.vo.SearchFarmAccessVehicleVo;
import com.querydsl.jpa.JPQLQuery;

public class FarmAccessVehicleRepositoryImpl extends QueryDslRepositorySupport implements FarmAccessVehicleRepositoryCustom {
    protected Log logger = LogFactory.getLog(FarmAccessVehicleRepositoryImpl.class);

	public FarmAccessVehicleRepositoryImpl() {
		super(FarmAccessVehicle.class);
	}

	@Override
	public Page<FarmAccessVehicle> findAccessVehicleByFarmSeq(SearchFarmAccessVehicleVo searchFarmAccessVehicle, Pageable pageable) {
		QFarmAccessVehicle farmAccessVehicle = QFarmAccessVehicle.farmAccessVehicle;
		
		JPQLQuery<FarmAccessVehicle> query = from(farmAccessVehicle);
		query.where(farmAccessVehicle.farmSeq.eq(searchFarmAccessVehicle.getFarmSeq()));
		List<FarmAccessVehicle> result = getQuerydsl().applyPagination(pageable, query).fetch();
		return new PageImpl<>(result, pageable, query.fetchCount());
	}
	

	@Override
	public Page<FarmAccessVehicle> findAccessVehicleByFarmSeqAndPeriod(
			SearchFarmAccessVehicleVo searchFarmAccessVehicle, Pageable pageable) {
		QFarmAccessVehicle farmAccessVehicle = QFarmAccessVehicle.farmAccessVehicle;
		
		JPQLQuery<FarmAccessVehicle> query = from(farmAccessVehicle);
		if(searchFarmAccessVehicle.getFrom() != null || searchFarmAccessVehicle.getTo() != null) {
			query.where(farmAccessVehicle.farmSeq.eq(searchFarmAccessVehicle.getFarmSeq())
				.and(farmAccessVehicle.capDt.goe(searchFarmAccessVehicle.getFrom())
				.and(farmAccessVehicle.capDt.loe(searchFarmAccessVehicle.getTo()))));
		}
		List<FarmAccessVehicle> result = getQuerydsl().applyPagination(pageable, query).fetch();
		return new PageImpl<>(result, pageable, query.fetchCount());
	}

	@Override
	public FarmAccessVehicle findAccessVehicleByFarmSeqAndCarNoAndMaxCapDt(Long farmSeq, String carNo) {
		QFarmAccessVehicle farmAccessVehicle = QFarmAccessVehicle.farmAccessVehicle;
		
		JPQLQuery<?> query = from(farmAccessVehicle)
				.where(farmAccessVehicle.farmSeq.eq(farmSeq)
				.and(farmAccessVehicle.carNo.eq(carNo)))
				.orderBy(farmAccessVehicle.capDt.desc());
		FarmAccessVehicle result = (FarmAccessVehicle) query.fetchFirst();
		return result;
	}

}
