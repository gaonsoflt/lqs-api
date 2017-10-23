package com.gaonsoft.lqs.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gaonsoft.lqs.api.model.farm.Farm;
import com.gaonsoft.lqs.api.vo.SearchFarmVo;

public interface FarmRepositoryCustom {
	public Page<Farm> findFarmBySigunguCode(SearchFarmVo searchVo, Pageable pageable);

	public Page<Farm> findFarmBySigunguCodeAndBCode(SearchFarmVo searchVo, Pageable pageable);

	public Page<Farm> findFarmBySigunguCodeAndRoadnameCode(SearchFarmVo searchVo, Pageable pageable);
}
