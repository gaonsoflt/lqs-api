package com.gaonsoft.lqs.api.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gaonsoft.lqs.api.model.SearchDisfCarVo;
import com.gaonsoft.lqs.api.model.SearchFarmVo;
import com.gaonsoft.lqs.api.model.car.DisfCar;
import com.gaonsoft.lqs.api.model.car.Driver;
import com.gaonsoft.lqs.api.model.farm.Farm;
import com.gaonsoft.lqs.api.model.farm.FarmAccessVehicle;
import com.gaonsoft.lqs.api.model.request.FarmVisitPlanVo;
import com.gaonsoft.lqs.api.model.request.FpAuthVo;
import com.gaonsoft.lqs.api.repository.DisfCarRepository;
import com.gaonsoft.lqs.api.repository.DriverRepository;
import com.gaonsoft.lqs.api.repository.FarmAccessVehicleRepository;
import com.gaonsoft.lqs.api.repository.FarmRepository;

@Service
public class KioskServiceImpl implements KioskService {

	@Autowired
	private DisfCarRepository disfCarRepository;
	
	@Autowired
	private FarmRepository farmRepository;
	
	@Autowired
	private DriverRepository driverRepository;
	
	@Autowired
	private FarmAccessVehicleRepository farmAccessVehicleRepository;
	
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

	@Override
	public void saveFarmVisitPlan(FarmVisitPlanVo vo) throws Exception {
		Date date = new Date();
		for (long farmSeq : vo.getFarmSeq()) {
			FarmAccessVehicle fav = new FarmAccessVehicle();
			fav.setFarmSeq(farmSeq);
			fav.setCarNo(vo.getCarNo());
			fav.setDriverSeq(vo.getDriverSeq());
			fav.setCarDisfSeq(vo.getCarDisfSeq());
			fav.setVisitPlanDt(date);
			farmAccessVehicleRepository.save(fav);
		}
	}

	@Override
	public Driver loginDriver(FpAuthVo vo) throws Exception {
		driverRepository.findByFingerprint(vo.getPfTemplete());
		// TODO: confirm fp
		return new Driver();
	}
}
