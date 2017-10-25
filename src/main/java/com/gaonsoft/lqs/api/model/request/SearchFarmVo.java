package com.gaonsoft.lqs.api.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchFarmVo {

	private long farmSeq;
	
	private String sigunguCode;
	
	private String bcode;
	
	private String roadnameCode;

	public SearchFarmVo(String sigunguCode, String bcode, String roadnameCode) {
		this.sigunguCode = sigunguCode;
		this.bcode = bcode;
		this.roadnameCode = roadnameCode;
	}
}
