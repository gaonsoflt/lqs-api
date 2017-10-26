package com.gaonsoft.lqs.api.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gaonsoft.lqs.api.common.PwdEncryptor;
import com.gaonsoft.lqs.api.common.exception.BadRequestException;
import com.gaonsoft.lqs.api.common.util.DateUtil;
import com.gaonsoft.lqs.api.model.FarmAccessVehicleSummary;
import com.gaonsoft.lqs.api.model.farm.AppFcm;
import com.gaonsoft.lqs.api.model.farm.Farm;
import com.gaonsoft.lqs.api.model.farm.FarmAccessVehicle;
import com.gaonsoft.lqs.api.model.farm.request.MyinfoVo;
import com.gaonsoft.lqs.api.model.request.SearchFarmAccessVehicleVo;
import com.gaonsoft.lqs.api.model.user.ApiUser;
import com.gaonsoft.lqs.api.repository.ApiUserRepository;
import com.gaonsoft.lqs.api.repository.AppFcmRepository;
import com.gaonsoft.lqs.api.repository.FarmAccessVehicleRepository;
import com.gaonsoft.lqs.api.repository.FarmRepository;

/***
 * 
 * @author woonsungbaek
 *
 */
@Service
public class FarmServiceImpl implements FarmService {

	@Autowired
	private FarmRepository farmRepository;
	
	@Autowired
	private FarmAccessVehicleRepository accessVehicleRepository;
	
	@Autowired
	private ApiUserRepository apiUserRepository;
	
	@Autowired
	private AppFcmRepository appFcmRepository;

	@Override
	public Farm findFarmById(long id) throws Exception {
		return Optional.ofNullable(farmRepository.findOne(id)).orElseGet(Farm::new);
	}

	/**
	 * change app login password(id=farm_seq)
	 */
	@Override
	public void updatePassword(String id, String password) throws Exception {
		ApiUser user = apiUserRepository.findOne(id);
		user.setPassword(new PwdEncryptor().encode(password));
		apiUserRepository.save(user);
	}
	
	@Override
	public Farm updateMyinfo(MyinfoVo vo) throws Exception {
		if(vo.getAllowAlert() != null) {
			List<AppFcm> result = appFcmRepository.findByAppId(vo.getId());
			for (AppFcm appFcm : result) {
				// true : 1, false : 0
				appFcm.setAllowAlert((vo.getAllowAlert()) ? 1 : 0);
			}
			appFcmRepository.save(result);
		}
		
		if(vo.getPassword() != null) {
			if(!vo.getPassword().isEmpty()) {
				ApiUser user = apiUserRepository.findOne(vo.getId().toString());
				user.setPassword(new PwdEncryptor().encode(vo.getPassword()));
				apiUserRepository.save(user);
			} else {
				throw new BadRequestException("Password can not empty.");
			}
		}
		
		return this.findFarmById(vo.getId());
	}

	@Override
	public boolean closeGate(String id) {
		// TODO: call api(close gate) to lpr server
		return true;
	}

	@Override
	public boolean openGate(String id) {
		// TODO: call api(open gate) to lpr server
		return true;
	}

	@Override
	public Page<FarmAccessVehicle> findFarmAccessVehicles(String id, Pageable pageable) throws Exception {
		return accessVehicleRepository.findAccessVehicleByFarmSeq(new SearchFarmAccessVehicleVo(Long.valueOf(id)), pageable);
	}	
	
	@Override
	public Page<FarmAccessVehicle> findFarmAccessVehicles(String id, Date from, Date to, Pageable pageable) throws Exception {
		return accessVehicleRepository.findAccessVehicleByFarmSeqAndPeriod(new SearchFarmAccessVehicleVo(Long.valueOf(id), from, to), pageable);
	}
	
	@Override
	public FarmAccessVehicleSummary findFarmAccessVehiclesSummary(String id, Date from, Date to) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		List<FarmAccessVehicle> result = accessVehicleRepository
			.findAccessVehicleByFarmSeqAndCarNoAndVisitPlanDtOrCapDt(
					new SearchFarmAccessVehicleVo(Long.valueOf(id)
							, sdf.parse(sdf.format(from))
							, DateUtil.addDateFieldFromDate(1, sdf.parse(sdf.format(to))))
					);
		
		return new FarmAccessVehicleSummary(result);
	}
}
