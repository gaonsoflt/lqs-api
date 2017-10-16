package com.gaonsoft.lqs.api.farm.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gaonsoft.lqs.api.lpr.model.Lpr;

import lombok.Data;

@Entity
@Data
@Table(name="lqs_farm_info")
public class Farm {

	@Id
	@Column(name="farm_seq")
	private Long farmSeq;
	
	@Column(name="farm_name")
	private String farmName;
	
	@Column(name="run_yn")
	private int runnabled;
	
	@JsonIgnore 
	private String password;
	
	@OneToMany
	@JoinColumn(name="loc_seq")
	@RestResource(path = "lpr", rel="lpr")
	private List<Lpr> lpr = new ArrayList<>();
	
	public boolean isRunnabled() {
		if(this.runnabled > 0) {
			return true; 
		}
		else {
			return false;
		}
	}
}