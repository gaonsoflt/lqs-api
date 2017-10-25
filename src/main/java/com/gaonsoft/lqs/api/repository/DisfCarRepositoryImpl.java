package com.gaonsoft.lqs.api.repository;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import com.gaonsoft.lqs.api.model.SearchDisfCarVo;
import com.gaonsoft.lqs.api.model.car.DisfCar;
import com.gaonsoft.lqs.api.model.car.QDisfCar;
import com.querydsl.jpa.JPQLQuery;

public class DisfCarRepositoryImpl extends QueryDslRepositorySupport implements DisfCarRepositoryCustom {
    protected Log logger = LogFactory.getLog(DisfCarRepositoryImpl.class);

	public DisfCarRepositoryImpl() {
		super(DisfCar.class);
	}

	@Override
	public Page<DisfCar> findDisfCarsByFacilitySeqAndCarNoAndBeforeAdminDt(SearchDisfCarVo searchVo, Pageable pageable) {
		QDisfCar disfCar = QDisfCar.disfCar;
		JPQLQuery<DisfCar> query = from(disfCar);;
		if(searchVo.getCarNo() != null) {
			query.where(disfCar.facilitySeq.eq(searchVo.getFacilitySeq())
					.and(disfCar.carNo.endsWith(searchVo.getCarNo())
					.and(disfCar.admitDt.after(searchVo.getAdmitDt()))));
		} else {
			query.where(disfCar.facilitySeq.eq(searchVo.getFacilitySeq())
					.and(disfCar.admitDt.after(searchVo.getAdmitDt())));
		}
		List<DisfCar> result = getQuerydsl().applyPagination(pageable, query).fetch();
		return new PageImpl<>(result, pageable, query.fetchCount());
	}
	
	@Override
	public Page<DisfCar> findDisfCarsByFacilitySeqAndCarNo(SearchDisfCarVo searchVo, Pageable pageable) {
		QDisfCar disfCar = QDisfCar.disfCar;
		JPQLQuery<DisfCar> query = from(disfCar);;
		if(searchVo.getCarNo() != null) {
			query.where(disfCar.facilitySeq.eq(searchVo.getFacilitySeq())
					.and(disfCar.carNo.endsWith(searchVo.getCarNo())));
		} else {
			query.where(disfCar.facilitySeq.eq(searchVo.getFacilitySeq()));
		}
		List<DisfCar> result = getQuerydsl().applyPagination(pageable, query).fetch();
		return new PageImpl<>(result, pageable, query.fetchCount());
	}
}
