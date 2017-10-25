package com.gaonsoft.lqs.api.repository;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import com.gaonsoft.lqs.api.model.farm.FarmAccessVehicle;
import com.gaonsoft.lqs.api.model.farm.QFarmAccessVehicle;
import com.gaonsoft.lqs.api.model.request.SearchFarmAccessVehicleVo;
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
		
		JPQLQuery<FarmAccessVehicle> query = from(farmAccessVehicle)
				.where(farmAccessVehicle.farmSeq.eq(farmSeq)
				.and(farmAccessVehicle.carNo.eq(carNo)))
				.orderBy(farmAccessVehicle.capDt.desc());
		FarmAccessVehicle result = query.fetchFirst();
		return result;
	}

	@Override
	public FarmAccessVehicle findAccessVehicleByFarmSeqAndCarNoAndVisitPlanDt(Long farmSeq, String carNo, Date visitPlanDt) {
		QFarmAccessVehicle farmAccessVehicle = QFarmAccessVehicle.farmAccessVehicle;
		
		// visitPlanDt 중에 Seq 높은거 하나만 리턴 = 가장 최근 방문 요청 기록 
		JPQLQuery<FarmAccessVehicle> query = from(farmAccessVehicle)
				.where(farmAccessVehicle.farmSeq.eq(farmSeq)
				.and(farmAccessVehicle.carNo.eq(carNo))
				.and(farmAccessVehicle.visitPlanDt.eq(visitPlanDt)))
				.orderBy(farmAccessVehicle.seq.desc());
		FarmAccessVehicle result = query.fetchFirst();
		return result;
	}

	@Override
	public List<FarmAccessVehicle> findAccessVehicleByFarmSeqAndCarNoAndVisitPlanDtOrCapDt(
			SearchFarmAccessVehicleVo searchFarmAccessVehicle) {
		
		QFarmAccessVehicle farmAccessVehicle = QFarmAccessVehicle.farmAccessVehicle;
		
		JPQLQuery<FarmAccessVehicle> query = from(farmAccessVehicle)
				.where(farmAccessVehicle.farmSeq.eq(searchFarmAccessVehicle.getFarmSeq())
						.and(farmAccessVehicle.visitPlanDt.goe(searchFarmAccessVehicle.getFrom())
								.and(farmAccessVehicle.visitPlanDt.lt(searchFarmAccessVehicle.getTo()))
						).or(farmAccessVehicle.capDt.goe(searchFarmAccessVehicle.getFrom())
								.and(farmAccessVehicle.capDt.lt(searchFarmAccessVehicle.getTo()))
						)
				).orderBy(farmAccessVehicle.capDt.asc())
				.orderBy(farmAccessVehicle.visitPlanDt.asc());
		
		List<FarmAccessVehicle> result = query.fetch();
		return result;
	}
}
