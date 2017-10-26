package com.gaonsoft.lqs.api.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginVo {

	@ApiModelProperty(required = true, notes = "농장아이디")
	private String id;
	
	@ApiModelProperty(required = true, notes = "패스워드")
	private String password;
	
	@ApiModelProperty(required = true, notes = "fcm용토큰")
	private String mobile_token;
}
