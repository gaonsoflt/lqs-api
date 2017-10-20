package com.gaonsoft.lqs.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel
public class LprControlVo {

	@ApiModelProperty(required = true, value = "open/close")
	private String action;	
	
	@ApiModelProperty(required = false)
	private Long lprSeq;	
}
