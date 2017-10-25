package com.gaonsoft.lqs.api.model.request;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchFarmAccessVehicleVo {

	private long farmSeq;
	
	private Date from;
	
	private Date to;
	
	public SearchFarmAccessVehicleVo(long farmSeq) {
		this.farmSeq = farmSeq;
	}
}
