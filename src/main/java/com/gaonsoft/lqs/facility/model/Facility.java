package com.gaonsoft.lqs.facility.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.rest.core.annotation.RestResource;

import com.gaonsoft.lqs.farm.lpr.model.Lpr;

import lombok.Data;

@Entity
@Data
@Table(name="lqs_disf_facility")
public class Facility {
	
	@Id
	@Column(name="facility_seq")
	private Long facilitySeq;
	
	@Column(name="facility_name")
	private String facilityName;
	
	@OneToMany
	@JoinColumn(name="loc_seq")
	@RestResource(path = "lpr", rel="lpr")
	private List<Lpr> lpr = new ArrayList<>(); 
}
