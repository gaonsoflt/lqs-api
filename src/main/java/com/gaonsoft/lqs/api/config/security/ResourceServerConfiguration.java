package com.gaonsoft.lqs.api.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@EnableResourceServer
@Configuration
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	@Value("${security.oauth2.resource.id}")
	private String resourceId;
	
	@Value("${security.oauth2.resource.jwt.key-value}")
	private String SIGNING_KEY;
	
	@Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }
	
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {		
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setVerifierKey(SIGNING_KEY);
		return converter;
	}
	
	@Bean
	@Primary
	public DefaultTokenServices tokenService() {
		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
		defaultTokenServices.setSupportRefreshToken(true);
		return defaultTokenServices;
	}

	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(resourceId);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
		.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
		.authorizeRequests()
			.antMatchers("/api/app/login").permitAll()
			.antMatchers("/api/open/**").permitAll()
			.antMatchers("/api/apiUserRoles/**").permitAll()
//			.antMatchers("/api/app/**").hasRole("FARM")
//			.antMatchers("/api/kiosk/**").hasRole("KIOSK")
//			.antMatchers("/api/lpr/**").hasRole("LPR")
			.antMatchers("/api/app/**").permitAll()
			.antMatchers("/api/kiosk/**").permitAll()
			.antMatchers("/api/lpr/**").permitAll()
			.anyRequest().authenticated()
			.and()
        .httpBasic()
			.and()
		.csrf().disable();
	}
}
