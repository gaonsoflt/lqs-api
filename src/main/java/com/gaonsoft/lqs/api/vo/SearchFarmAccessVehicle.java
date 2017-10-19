package com.gaonsoft.lqs.api.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchFarmAccessVehicle {

	private long farmSeq;
	
	private Date from;
	
	private Date to;
	
	public SearchFarmAccessVehicle(long farmSeq) {
		this.farmSeq = farmSeq;
	}
}
