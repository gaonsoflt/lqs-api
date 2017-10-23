package com.gaonsoft.lqs.api.vo.request;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel
public class SubmitVo {

	@ApiModelProperty(required = true)
	private String carNo;	
	
	@ApiModelProperty(required = true)
	private List<String> farmSeq;	
	
	@ApiModelProperty(required = true)
	private byte[] pfTemplete ;	
}
