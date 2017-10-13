package com.gaonsoft.lqs.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import com.gaonsoft.lqs.farm.lpr.model.Lpr;
import com.gaonsoft.lqs.farm.model.Farm;

@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter  {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Farm.class);
		config.exposeIdsFor(Lpr.class);
	}
}
