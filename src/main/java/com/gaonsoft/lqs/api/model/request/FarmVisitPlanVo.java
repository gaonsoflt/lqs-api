package com.gaonsoft.lqs.api.model.request;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel
public class FarmVisitPlanVo {

	@ApiModelProperty(required = true)
	private String carNo;	
	
	@ApiModelProperty(required = true)
	private Long driverSeq;	
	
	@ApiModelProperty(required = true)
	private List<Long> farmSeq;	
}
