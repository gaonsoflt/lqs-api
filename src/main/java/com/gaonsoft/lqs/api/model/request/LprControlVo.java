package com.gaonsoft.lqs.api.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel
public class LprControlVo {

	@ApiModelProperty(required = true, notes = "동작구분", example = "open/close")
	private String action;	
	
	@ApiModelProperty(required = false)
	private Long lprSeq;	
}
