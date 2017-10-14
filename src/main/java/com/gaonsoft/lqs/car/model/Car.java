package com.gaonsoft.lqs.car.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.rest.core.annotation.RestResource;

import com.gaonsoft.lqs.farm.lpr.model.Lpr;

import lombok.Data;

@Entity
@Data
@Table(name="lqs_car_info")
public class Car {
	
	@Id
	@Column(name="car_no")
	private String carNo;
	
	@Column(name="owner")
	private String owner;
}
