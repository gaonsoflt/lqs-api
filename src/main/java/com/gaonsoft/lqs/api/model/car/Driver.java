package com.gaonsoft.lqs.api.model.car;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Data
@Table(name="lqs_driver_info")
public class Driver {
	
	@Id
	@Column(name="driver_seq")
	private Long driverSeq;
	
	@Column(name="dirver_name")
	private String driverName;
	
	@Column(name="gender")
	private int gender;
	
	@Column(name="age")
	private int age;
	
	@Column(name="phone")
	private String phone;
	
	@ApiModelProperty(hidden = true)
	@Column(name="fingerprint")
	private byte[] fingerprint;
}
