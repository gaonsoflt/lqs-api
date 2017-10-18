package com.gaonsoft.lqs.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import com.gaonsoft.lqs.api.model.car.Car;
import com.gaonsoft.lqs.api.model.facility.Facility;
import com.gaonsoft.lqs.api.model.farm.Farm;
import com.gaonsoft.lqs.api.model.lpr.Lpr;

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
