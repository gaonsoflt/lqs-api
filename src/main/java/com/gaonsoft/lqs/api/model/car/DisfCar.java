package com.gaonsoft.lqs.api.model.car;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.gaonsoft.lqs.api.model.facility.Facility;

import lombok.Data;

@Entity
@Data
@IdClass(DisfCarKey.class)
@Table(name="lqs_car_disf_info")
public class DisfCar {
	
	@Id
	@Column(name="car_no")
	private String carNo;
	
	@Id
	@Column(name="facility_seq")
	private Long facilitySeq;
	
	@Id
	@Column(name="disf_dt")
	@Temporal(TemporalType.TIMESTAMP)
	private Date disfDt;
	
	@Column(name="admit_dt")
	@Temporal(TemporalType.TIMESTAMP)
	private Date admitDt;
	
	@ManyToOne
	@JoinColumn(name="facility_seq", referencedColumnName="facility_seq", insertable=false, updatable=false, nullable=true)
	private Facility facility;
}
