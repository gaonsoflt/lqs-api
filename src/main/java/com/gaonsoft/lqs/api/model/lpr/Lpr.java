package com.gaonsoft.lqs.api.model.lpr;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="lqs_lpr_info")
public class Lpr {

	@Id
	@Column(name="lpr_seq")
	private Long lprSeq;
	
	@Column(name="model")
	private String model;

	@Column(name="loc_type")
	private String locType;
	
	@Column(name="loc_seq")
	private Long locSeq;
	
	@Column(name="description")
	private String description;
}
