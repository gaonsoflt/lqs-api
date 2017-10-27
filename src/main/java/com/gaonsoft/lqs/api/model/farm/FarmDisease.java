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

import com.gaonsoft.lqs.api.model.disease.MetaDisease;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Data
@Table(name="lqs_farm_disease")
public class FarmDisease {

	@Id
	@ApiModelProperty(hidden = true)
	@SequenceGenerator(name="sq_lqs_farm_disease", sequenceName="sq_lqs_farm_disease", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sq_lqs_farm_disease")
	@Column(name="seq")
	private Long seq;
	
	@ApiModelProperty(hidden = true)
	@Column(name="farm_seq")
	private Long farmSeq;
	
	@ApiModelProperty(required = true, notes = "질병코드")
	@Column(name="disease_code")
	private String diseaseCode;
	
	@ManyToOne
	@JoinColumn(name="disease_code", referencedColumnName="disease_code", insertable=false, updatable=false, nullable=true)
	private MetaDisease disease;
	
	@ApiModelProperty(required = false, notes = "진단기관")
	@Column(name="diagnosis_agy")
	private String diagnosisAgy;
	
	@ApiModelProperty(required = false, notes = "발병일(default:당일)")
	@Column(name="occ_dt")
	@Temporal(TemporalType.TIMESTAMP)
	private Date occDt;
	
	@ApiModelProperty(hidden = true, required = false, notes = "해제일(default:당일)")
	@Column(name="ter_dt")
	@Temporal(TemporalType.TIMESTAMP)
	private Date terDt;
}
