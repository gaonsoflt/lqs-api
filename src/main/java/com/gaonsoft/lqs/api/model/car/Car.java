package com.gaonsoft.lqs.api.model.car;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="lqs_car_info")
public class Car {
	
	@Id
	@Column(name="car_no")
	private String carNo;
	
	@Column(name="owner")
	private String owner;
}
