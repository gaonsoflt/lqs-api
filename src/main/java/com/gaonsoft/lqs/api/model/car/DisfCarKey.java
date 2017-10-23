package com.gaonsoft.lqs.api.model.car;

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
public class DisfCarKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="car_no")
	private String carNo;
	
	@Column(name="facility_seq")
	private Long facilitySeq;
	
	@Column(name="disf_dt")
	@Temporal(TemporalType.TIMESTAMP)
	private Date disfDt;
}