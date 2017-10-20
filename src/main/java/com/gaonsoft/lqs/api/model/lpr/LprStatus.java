package com.gaonsoft.lqs.api.model.lpr;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Data
@IdClass(LprStatusKey.class)
@ApiModel
@Table(name="lqs_lpr_status")
public class LprStatus {

	@Id
	@ApiModelProperty(required = true)
	@Column(name="lpr_seq")
	private Long lprSeq;
	
	@Id
	@ApiModelProperty(required = true, dataType = "long")
	@Column(name="cre_dt")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creDt;
	
	@Column(name="is_alive")
	@ApiModelProperty(required = true)
	private Integer isAlive;

	@Column(name="status_msg")
	@ApiModelProperty(required = false)
	private String statusMsg;
}
