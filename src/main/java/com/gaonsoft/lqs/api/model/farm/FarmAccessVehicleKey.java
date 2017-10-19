package com.gaonsoft.lqs.api.model.farm;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FarmAccessVehicleKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="farm_seq")
	private Long farmSeq;
	
	@Column(name="car_no")
	private String carNo;

	@Column(name="cap_dt")
	@Temporal(TemporalType.TIMESTAMP)
	private Date captureDt;
}