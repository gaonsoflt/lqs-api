package com.gaonsoft.lqs.api.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaonsoft.lqs.api.model.car.Car;
import com.gaonsoft.lqs.api.model.car.DisfCar;
import com.gaonsoft.lqs.api.model.config.ConfigKey;
import com.gaonsoft.lqs.api.model.farm.FarmAccessVehicle;
import com.gaonsoft.lqs.api.model.lpr.Lpr;
import com.gaonsoft.lqs.api.model.lpr.LprStatus;
import com.gaonsoft.lqs.api.model.request.AccessVehicleVo;
import com.gaonsoft.lqs.api.repository.CarRepository;
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
	private CarRepository carRepository;
	
	@Autowired
	private ConfigRepository configRepository;

	@Override
	public LprStatus saveLprStatus(LprStatus lprStatus) throws Exception {
		return lprStatusRepository.save(lprStatus);
	}

	@Override
	public void saveAccessVehicle(AccessVehicleVo requestVo) throws Exception {
		Lpr lpr = lprRepository.findOne(requestVo.getLprSeq());
		if(lpr.getLocSeq() != null) {
			// F = farm, L = facility
			if(lpr.getLocType().equals("F")) {
				// 방문 요청 일자
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				FarmAccessVehicle vo = farmAccessVehicleRepository
						.findAccessVehicleByFarmSeqAndCarNoAndVisitPlanDt(lpr.getLocSeq(), requestVo.getCarNo(), sdf.parse(sdf.format(new Date())));
				// 방문예정기록 있음
				if(vo != null) {
					if(vo.getCapDt() != null) {
						// save new access vehicle info
						vo = new FarmAccessVehicle();
						vo.setFarmSeq(lpr.getLocSeq());
						vo.setCarNo(requestVo.getCarNo());
						vo.setCapDt(requestVo.getCapDt());
					} else {
						// update access vehicle info
						vo.setCapDt(requestVo.getCapDt());
					}
				} else { // 방문예정기록 없음
					// save new access vehicle info
					Car car = carRepository.findOne(requestVo.getCarNo());
					if(car == null) {
						carRepository.save(new Car(requestVo.getCarNo(), "미등록차량"));
					}
					vo = new FarmAccessVehicle();
					vo.setFarmSeq(lpr.getLocSeq());
					vo.setCarNo(requestVo.getCarNo());
					vo.setCapDt(requestVo.getCapDt());
				}
				farmAccessVehicleRepository.save(vo);
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
				disfCarRepository.save(vo);
			} else {
				throw new Exception("Not support location(other).");
			}
		} else {
			throw new Exception("Can found a location installed lpr.");
		}
	}

	@Override
	public void updateAccessVehicle(AccessVehicleVo requestVo) throws Exception {
		Lpr lpr = lprRepository.findOne(requestVo.getLprSeq());
		if(lpr.getLocSeq() != null) {
			// F = farm, L = facility
			if(lpr.getLocType().equals("F")) {
				FarmAccessVehicle vo = farmAccessVehicleRepository
						.findAccessVehicleByFarmSeqAndCarNoAndMaxCapDt(lpr.getLocSeq(), requestVo.getCarNo());
				if(vo == null) {
					throw new Exception("Not found access record.");
				} else if(vo.getInDt() != null) {
					throw new Exception("Access record(in_dt) exists.");
				}
				vo.setInDt(requestVo.getInDt());
				farmAccessVehicleRepository.save(vo);
			} else if(lpr.getLocType().equals("L")) {
				throw new Exception("Not support location(L:facility).");
			} else {
				throw new Exception("Not support location(other).");
			}
		} else {
			throw new Exception("Not found a location installed lpr.");
		}
	}
}
