package com.gaonsoft.lqs.api.model.config;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfigKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="cfg_group")
	private String cfgGroup;
	
	@Column(name="cfg_id")
	private String cfgId;
}
