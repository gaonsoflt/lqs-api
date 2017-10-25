package com.gaonsoft.lqs.api.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchDisfCarVo {

	private long facilitySeq;
	
	private String carNo;
	
	private Date admitDt;
	
	public SearchDisfCarVo(long facilitySeq) {
		this.facilitySeq = facilitySeq;
	}
	
	public SearchDisfCarVo(long facilitySeq, String carNo) {
		this.facilitySeq = facilitySeq;
		this.carNo = carNo;
	}
}
