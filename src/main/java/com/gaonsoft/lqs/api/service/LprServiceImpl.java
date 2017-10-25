package com.gaonsoft.lqs.api.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaonsoft.lqs.api.model.car.DisfCar;
import com.gaonsoft.lqs.api.model.config.ConfigKey;
import com.gaonsoft.lqs.api.model.farm.FarmAccessVehicle;
import com.gaonsoft.lqs.api.model.lpr.Lpr;
import com.gaonsoft.lqs.api.model.lpr.LprStatus;
import com.gaonsoft.lqs.api.model.request.AccessVehicleVo;
import com.gaonsoft.lqs.api.repository.ConfigRepository;
import com.gaonsoft.lqs.api.repository.DisfCarRepository;
import com.gaonsoft.lqs.api.repository.FarmAccessVehicleRepository;
import com.gaonsoft.lqs.api.repository.LprRepository;
import com.gaonsoft.lqs.api.repository.LprStatusRepository;

@Service
public class LprServiceImpl implements LprService {

	@Autowired
	private LprStatusRepository lprStatusRepository;
	
	@Autowired
	private LprRepository lprRepository;
	
	@Autowired
	private FarmAccessVehicleRepository farmAccessVehicleRepository;
	
	@Autowired
	private DisfCarRepository disfCarRepository;
	
	@Autowired
	private ConfigRepository configRepository;

	@Override
	public LprStatus saveLprStatus(LprStatus lprStatus) throws Exception {
		return lprStatusRepository.save(lprStatus);
	}

	@Override
	public AccessVehicleVo saveAccessVehicle(AccessVehicleVo requestVo) throws Exception {
		Lpr lpr = lprRepository.findOne(requestVo.getLprSeq());
		if(lpr.getLocSeq() != null) {
			// F = farm, L = facility
			if(lpr.getLocType().equals("F")) {
				FarmAccessVehicle vo = new FarmAccessVehicle();
				vo.setFarmSeq(lpr.getLocSeq());
				vo.setCarNo(requestVo.getCarNo());
				vo.setCapDt(requestVo.getCapDt());
				return new AccessVehicleVo(farmAccessVehicleRepository.save(vo));
			} else if(lpr.getLocType().equals("L")) {
				DisfCar vo = new DisfCar();
				vo.setFacilitySeq(lpr.getLocSeq());
				vo.setCarNo(requestVo.getCarNo());
				vo.setDisfDt(requestVo.getCapDt());
				int admitTime = Integer.valueOf(configRepository.findOne(new ConfigKey("LQS", "DISF_ADMIT_TIME")).getCfgValue());
				Calendar cal = Calendar.getInstance();
				cal.setTime(requestVo.getCapDt());
				cal.add(Calendar.MINUTE, admitTime);
				vo.setAdmitDt(cal.getTime());
				return new AccessVehicleVo(disfCarRepository.save(vo));
			}
		}
		return new AccessVehicleVo();
	}

	@Override
	public AccessVehicleVo updateAccessVehicle(AccessVehicleVo requestVo) throws Exception {
		Lpr lpr = lprRepository.findOne(requestVo.getLprSeq());
		if(lpr.getLocSeq() != null) {
			// F = farm, L = facility
			if(lpr.getLocType().equals("F")) {
				FarmAccessVehicle vo = farmAccessVehicleRepository.findAccessVehicleByFarmSeqAndCarNoAndMaxCapDt(lpr.getLocSeq(), requestVo.getCarNo());
				vo.setInDt(requestVo.getInDt());
				return new AccessVehicleVo(farmAccessVehicleRepository.save(vo));
			}
//			} else if(lpr.getLocType().equals("L")) {
//			}
		}
		return new AccessVehicleVo();
	}
}
