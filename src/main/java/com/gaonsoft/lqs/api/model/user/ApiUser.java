package com.gaonsoft.lqs.api.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(name="api_user")
public class ApiUser {

	@Id
	@Column(name="user_id")
	private String userId;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="user_type")
	private String userType;
	
	@JsonIgnore 
	private String password;
}
