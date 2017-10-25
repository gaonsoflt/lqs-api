package com.gaonsoft.lqs.api.model.car;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.gaonsoft.lqs.api.model.facility.Facility;

import lombok.Data;

@Entity
@Data
@Table(name="lqs_car_disf_info")
public class DisfCar {
	
	@Id
	@SequenceGenerator(name="sq_lqs_car_disf_info", sequenceName="sq_lqs_car_disf_info", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sq_lqs_car_disf_info")
	@Column(name="car_disf_seq")
	private Long carDisfSeq;
	
	@Column(name="car_no")
	private String carNo;
	
	@Column(name="facility_seq")
	private Long facilitySeq;
	
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
