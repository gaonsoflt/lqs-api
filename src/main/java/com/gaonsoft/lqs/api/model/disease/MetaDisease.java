package com.gaonsoft.lqs.api.model.disease;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="lqs_meta_disease")
public class MetaDisease {

	@Id
	@Column(name = "disease_code")
	private String diseaseCode;
	
	@Column(name = "disease_name")
	private String diseaseName;
}
