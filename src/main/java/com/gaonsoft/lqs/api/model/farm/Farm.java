package com.gaonsoft.lqs.api.model.farm;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gaonsoft.lqs.api.model.lpr.Lpr;
import com.gaonsoft.lqs.api.model.system.Address;

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
	
	@Column(name="address_seq")
	private Long addressSeq;
	
	@JsonIgnore 
	private String password;
	
	@OneToOne
	@JoinColumn(name="address_seq", referencedColumnName="address_seq", insertable=false, updatable=false, nullable=true)
	private Address address;
	
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
