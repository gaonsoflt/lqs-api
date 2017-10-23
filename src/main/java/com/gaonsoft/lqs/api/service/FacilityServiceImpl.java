package com.gaonsoft.lqs.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gaonsoft.lqs.api.model.car.DisfCar;
import com.gaonsoft.lqs.api.model.farm.Farm;
import com.gaonsoft.lqs.api.repository.DisfCarRepository;
import com.gaonsoft.lqs.api.repository.FarmRepository;
import com.gaonsoft.lqs.api.vo.SearchDisfCarVo;
import com.gaonsoft.lqs.api.vo.SearchFarmVo;

@Service
public class FacilityServiceImpl implements FacilityService {

	@Autowired
	private DisfCarRepository disfCarRepository;
	
	@Autowired
	private FarmRepository farmRepository;
	
	@Override
	public Page<DisfCar> findDisfCar(SearchDisfCarVo searchVo, Pageable pageable) throws Exception {
		if(searchVo.getAdmitDt() != null) {
			return disfCarRepository.findDisfCarsByFacilitySeqAndCarNoAndBeforeAdminDt(searchVo, pageable);
		}
		return disfCarRepository.findDisfCarsByFacilitySeqAndCarNo(searchVo, pageable);
	}

	@Override
	public Page<Farm> findFarm(SearchFarmVo searchVo, Pageable pageable) throws Exception {
		if(searchVo.getBcode() != null) {
			return farmRepository.findFarmBySigunguCodeAndBCode(searchVo, pageable);
		} else if(searchVo.getRoadnameCode() != null) {
			return farmRepository.findFarmBySigunguCodeAndRoadnameCode(searchVo, pageable);
		}
		return farmRepository.findFarmBySigunguCode(searchVo, pageable);
	}
}
