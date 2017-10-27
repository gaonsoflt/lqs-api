package com.gaonsoft.lqs.api.model.disease;

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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="lqs_disease_warn_stage")
public class DiseaseWarnStage {
	
	@Id
	@SequenceGenerator(name="sq_lqs_disease_warn_stage", sequenceName="sq_lqs_disease_warn_stage", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sq_lqs_disease_warn_stage")
	@Column(name="seq")
	private Long seq;
	
	@Column(name="disease_code")
	private String diseaseCode;
	
	@ManyToOne
	@JoinColumn(name="disease_code", referencedColumnName="disease_code", insertable=false, updatable=false, nullable=true)
	private MetaDisease disease;
	
	@Column(name="stage")
	private Integer stage;
	
	@Column(name="cre_dt")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creDt;
	
	public DiseaseWarnStage(String diseaseCode, Date creDt) {
		this.diseaseCode = diseaseCode;
		this.creDt = creDt;
	}
	
	public String getStageName() {
		return WarnStage.valueOf(this.stage).getStageName();
	}
}
