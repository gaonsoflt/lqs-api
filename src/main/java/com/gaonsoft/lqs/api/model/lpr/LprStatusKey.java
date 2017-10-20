package com.gaonsoft.lqs.api.model.lpr;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LprStatusKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="lpr_seq")
	private Long lprSeq;
	
	@Column(name="cre_dt")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creDt;
}
