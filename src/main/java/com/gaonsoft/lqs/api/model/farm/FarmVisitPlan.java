package com.gaonsoft.lqs.api.model.farm;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(FarmVisitPlanKey.class)
@Table(name="lqs_farm_visit_plan")
public class FarmVisitPlan {

	@Id
	@Column(name="farm_seq")
	private Long farmSeq;

	@Id
	@Column(name="car_no")
	private String carNo;
	
	@Id
	@Column(name="visit_plan_dt")
	@Temporal(TemporalType.DATE)
	private Date visitPlanDt;
	
	@Column(name="driver_seq")
	private Long driverSeq;
}
