package com.gaonsoft.lqs.api.model.farm.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MyinfoVo {

	@ApiModelProperty(hidden = true)
	private Long id;
	
	@ApiModelProperty(required = false, notes = "패스워드")
	private String password;
	
	@ApiModelProperty(required = false, notes = "알람수신허용")
	private Boolean allowAlert;
}
