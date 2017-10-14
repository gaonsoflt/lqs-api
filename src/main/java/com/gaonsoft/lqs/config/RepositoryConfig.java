package com.gaonsoft.lqs.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import com.gaonsoft.lqs.car.model.Car;
import com.gaonsoft.lqs.facility.model.Facility;
import com.gaonsoft.lqs.farm.lpr.model.Lpr;
import com.gaonsoft.lqs.farm.model.Farm;

@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter  {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Farm.class);
		config.exposeIdsFor(Facility.class);
		config.exposeIdsFor(Lpr.class);
		config.exposeIdsFor(Car.class);
	}
}
