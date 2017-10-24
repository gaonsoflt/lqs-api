package com.gaonsoft.lqs.api.vo.request;

import java.util.Date;

import com.gaonsoft.lqs.api.model.car.DisfCar;
import com.gaonsoft.lqs.api.model.farm.FarmAccessVehicle;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccessVehicleVo {

	@ApiModelProperty(hidden = true)
	private Long lprSeq;
	
	@ApiModelProperty(readOnly = true, notes = "인식기 위치")
	private Long locSeq;
	
	@ApiModelProperty(readOnly = true)
	private String locType;
	
	@ApiModelProperty(required = true, notes = "차량번호")
	private String carNo;
	
	@ApiModelProperty(required = true, notes = "인식 일시(일자+시간)")
	private Date capDt;
	
	@ApiModelProperty(readOnly = true, notes = "소독허용시간")
	private Date admitDt;
	
	@ApiModelProperty(required = false, notes = "입차시간")
	private Date inDt;
	
	@ApiModelProperty(required = false, notes = "출차시간")
	private Date outDt;
	
	public AccessVehicleVo(FarmAccessVehicle vo) {
		this.carNo = vo.getCarNo();
		this.locSeq = vo.getFarmSeq();
		this.capDt = vo.getCapDt();
		this.inDt = vo.getInDt();
		this.outDt = vo.getOutDt();
	}
	
	public AccessVehicleVo(DisfCar vo) {
		this.carNo = vo.getCarNo();
		this.locSeq = vo.getFacilitySeq();
		this.capDt = vo.getDisfDt();
		this.admitDt = vo.getAdmitDt();
	}
}
