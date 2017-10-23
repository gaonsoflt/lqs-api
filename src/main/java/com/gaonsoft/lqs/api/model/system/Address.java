package com.gaonsoft.lqs.api.model.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="sys_address")
public class Address {

	/*
	 * zonecode
jibun_address
road_address
detail_address
sido
sigungu
sigungu_code
roadname_code
roadname
building_code
building_name
bcode
bname
bname1
	 */
	
	@Id
	@Column(name="address_seq")
	private Long addressSeq;

	@Column(name="jibun_address")
	private String jibunAddress;
	
	@Column(name="road_address")
	private String roadAddress;
	
	@Column(name="sido")
	private String sido;
	
	@Column(name="sigungu")
	private String sigungu;
	
	@Column(name="sigungu_code")
	private String sigunguCode;
	
	@Column(name="bname")
	private String bname;
	
	@Column(name="bcode")
	private String bcode;
	
	@Column(name="roadname")
	private String roadname;
	
	@Column(name="roadname_code")
	private String roadnameCode;
	
}
