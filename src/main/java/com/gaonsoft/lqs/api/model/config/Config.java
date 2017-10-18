package com.gaonsoft.lqs.api.model.config;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@IdClass(ConfigKey.class)
@Table(name="sys_config")
public class Config {
	
	@Id
	@Column(name="cfg_group")
	private String cfgGroup;
	
	@Id
	@Column(name="cfg_id")
	private String cfgId;
	
	@Column(name="cfg_name")
	private String cfgName;
	
	@Column(name="cfg_value")
	private String cfgValue;
}
