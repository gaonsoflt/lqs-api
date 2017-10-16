package com.gaonsoft.lqs.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import com.gaonsoft.lqs.api.car.model.Car;
import com.gaonsoft.lqs.api.facility.model.Facility;
import com.gaonsoft.lqs.api.farm.model.Farm;
import com.gaonsoft.lqs.api.lpr.model.Lpr;

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
