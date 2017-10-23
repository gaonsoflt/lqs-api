package com.gaonsoft.lqs.api.repository;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import com.gaonsoft.lqs.api.model.farm.Farm;
import com.gaonsoft.lqs.api.model.farm.QFarm;
import com.gaonsoft.lqs.api.model.system.Address;
import com.gaonsoft.lqs.api.model.system.QAddress;
import com.gaonsoft.lqs.api.vo.SearchFarmVo;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;

public class FarmRepositoryImpl extends QueryDslRepositorySupport implements FarmRepositoryCustom {
    protected Log logger = LogFactory.getLog(FarmRepositoryImpl.class);

	public FarmRepositoryImpl() {
		super(Farm.class);
	}

	@Override
	public Page<Farm> findFarmBySigunguCode(SearchFarmVo searchVo, Pageable pageable) {
		QFarm farm = QFarm.farm;
		QAddress address = QAddress.address;
		
		JPQLQuery query = from(farm);
		if(searchVo.getSigunguCode() != null) {
			query.innerJoin(farm.address, address)
			.where(address.sigunguCode.eq(searchVo.getSigunguCode()));
		}
		
		List<Farm> result = getQuerydsl().applyPagination(pageable, query).fetch();
		return new PageImpl<>(result, pageable, query.fetchCount());
	}
	
	@Override
	public Page<Farm> findFarmBySigunguCodeAndBCode(SearchFarmVo searchVo, Pageable pageable) {
		QFarm farm = QFarm.farm;
		QAddress address = QAddress.address;
		
		JPQLQuery query = from(farm);
		if(searchVo.getSigunguCode() != null && searchVo.getBcode() != null) {
			query.innerJoin(farm.address, address)
			.where(address.sigunguCode.eq(searchVo.getSigunguCode())
					.and(address.bcode.eq(searchVo.getBcode())));
		}
		
		List<Farm> result = getQuerydsl().applyPagination(pageable, query).fetch();
		return new PageImpl<>(result, pageable, query.fetchCount());
	}
	
	
	@Override
	public Page<Farm> findFarmBySigunguCodeAndRoadnameCode(SearchFarmVo searchVo, Pageable pageable) {
		QFarm farm = QFarm.farm;
		QAddress address = QAddress.address;
		
		JPQLQuery query = from(farm);
		if(searchVo.getSigunguCode() != null && searchVo.getRoadnameCode() != null) {
			query.innerJoin(farm.address, address)
			.where(address.sigunguCode.eq(searchVo.getSigunguCode())
					.and(address.roadnameCode.eq(searchVo.getRoadnameCode())));
		}
		
		List<Farm> result = getQuerydsl().applyPagination(pageable, query).fetch();
		return new PageImpl<>(result, pageable, query.fetchCount());
	}
}
