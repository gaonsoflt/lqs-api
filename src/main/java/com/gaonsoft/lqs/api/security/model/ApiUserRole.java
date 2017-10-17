package com.gaonsoft.lqs.api.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@IdClass(ApiUserRoleKey.class)
@Table(name="api_user_role")
public class ApiUserRole {

	@Id
	@Column(name="user_seq")
	private Long userSeq;
	
	@Id
	@Column(name="role_id")
	private String roleId;
	
	@ManyToOne
	@JoinColumn(name="role_id", referencedColumnName="role_id", insertable=false, updatable=false, nullable=true)
	private ApiRole roleName;
}
