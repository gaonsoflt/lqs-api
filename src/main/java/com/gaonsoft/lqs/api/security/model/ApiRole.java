package com.gaonsoft.lqs.api.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="api_role")
public class ApiRole {

	@Id
	@Column(name="role_id")
	private String roleId;
	
	@Column(name="role_name")
	private String roleName;
}
