package com.gaonsoft.lqs.api.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel
public class FpAuthVo {

	@ApiModelProperty(required = true)
	private byte[] pfTemplete ;	
}
