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

	@ApiModelProperty(required = true, notes="차량번호")
	private String carNo;	
	
	@ApiModelProperty(required = true, notes="차량소독Seq")
	private Long carDisfSeq;	
	
	@ApiModelProperty(required = true, notes="운전자Seq")
	private Long driverSeq;	
	
	@ApiModelProperty(required = true, notes="방문농장Seq")
	private List<Long> farmSeq;	
}
