package com.gaonsoft.lqs.farm.lpr.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	
	@Column(name="last_alive_dt")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastAliveDt;
	
	@Column(name="is_alive")
	private int isAlive;
}
