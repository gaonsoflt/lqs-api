package com.gaonsoft.lqs.api.model.farm;

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

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.gaonsoft.lqs.api.model.car.Car;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="lqs_farm_access_vehicle")
public class FarmAccessVehicle {

	@Id
	@SequenceGenerator(name="sq_lqs_farm_access_vehicle",sequenceName="sq_lqs_farm_access_vehicle", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sq_lqs_farm_access_vehicle")
	@Column(name="seq")
	private Long seq;
	
	@Column(name="farm_seq")
	private Long farmSeq;
	
	@Column(name="car_no")
	private String carNo;
	
	@Column(name="driver_seq")
	private Long driverSeq;
	
	@Column(name="car_disf_seq")
	private Long carDisfSeq;

	@ApiModelProperty(readOnly = true)
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name="car_no", referencedColumnName="car_no", insertable=false, updatable=false, nullable=true)
	private Car carInfo;
	
	@Column(name="cap_dt")
	@Temporal(TemporalType.TIMESTAMP)
	private Date capDt;
	
	@Column(name="visit_plan_dt")
	@Temporal(TemporalType.DATE)
	private Date visitPlanDt;

	@Column(name="reject_reason")
	private String rejectReason;

	@Column(name="in_dt")
	@Temporal(TemporalType.TIMESTAMP)
	private Date inDt;

	@Column(name="out_dt")
	@Temporal(TemporalType.TIMESTAMP)
	private Date outDt;
}
