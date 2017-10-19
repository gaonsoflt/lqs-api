package com.gaonsoft.lqs.api.model.farm;

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

import com.gaonsoft.lqs.api.model.car.Car;

import lombok.Data;

@Entity
@Data
@IdClass(FarmAccessVehicleKey.class)
@Table(name="lqs_farm_access_vehicle")
public class FarmAccessVehicle {

	@Id
	@Column(name="farm_seq")
	private Long farmSeq;
	
	@Id
	@Column(name="car_no")
	private String carNo;

	@ManyToOne
	@JoinColumn(name="car_no", referencedColumnName="car_no", insertable=false, updatable=false, nullable=true)
	private Car carInfo;
	
	@Id
	@Column(name="cap_dt")
	@Temporal(TemporalType.TIMESTAMP)
	private Date captureDt;

	@Column(name="reject_reason")
	private String rejectReason;

	@Column(name="in_dt")
	@Temporal(TemporalType.TIMESTAMP)
	private Date inDt;

	@Column(name="out_dt")
	@Temporal(TemporalType.TIMESTAMP)
	private Date outDt;
}
