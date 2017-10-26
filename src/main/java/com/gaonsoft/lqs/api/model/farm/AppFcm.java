package com.gaonsoft.lqs.api.model.farm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="lqs_app_fcm")
public class AppFcm {

	@Id
	@Column(name="token")
	private String token;
	
	@Column(name="app_id")
	private Long appId;
	
	@Column(name="allow_alert")
	private int allowAlert;
	
	public AppFcm(String token, Long appId) {
		this.token = token;
		this.appId = appId;
	}
}
