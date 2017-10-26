package com.gaonsoft.lqs.api.model.request;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchFarmDiseaseVo {

	@ApiModelProperty(hidden = true)
	private Date from;
	
	@ApiModelProperty(hidden = true)
	private Date to;
	
	@ApiModelProperty(hidden = true)
	private Long seq;
	
	@ApiModelProperty(hidden = true)
	private Long farmSeq;
	
	@ApiModelProperty(hidden = true)
	@Column(name="disease_code")
	private String diseaseCode;
	
	@ApiModelProperty(hidden = true, required = false, notes = "진단기관")
	private String diagnosisAgy;
	
	@ApiModelProperty(hidden = true)
	@Column(name="occ_dt")
	@Temporal(TemporalType.TIMESTAMP)
	private Date occDt;
	
	@ApiModelProperty(required = false, notes = "해제일(default:당일)")
	@Column(name="ter_dt")
	@Temporal(TemporalType.TIMESTAMP)
	private Date terDt;
}
