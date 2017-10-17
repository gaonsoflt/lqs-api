package com.gaonsoft.lqs.api.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.gaonsoft.lqs.api.security.service.LoginService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	static final Integer ENCODING_STRENGTH = 256;
	static final String SECURITY_REALM = "Spring Boot JWT Example Realm";
	
	@Value("${security.oauth2.resource.jwt.key-value}")
	private String SIGNING_KEY;
	
	@Autowired 
	LoginService loginService;

	@Override
	// configure(HttpSecurity http) 보다 우선순위가 높음, 보통 리소스 위주의 경로를 설정함
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers("/resources/**")
			.antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**", "/swagger-ui.html", "/webjars/**");
	}

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		/**
//		 * sample
//		 http
//		 	.csrf().disable() // csrf 토근 사용여부
//		 	.authorizeRequests()
//				.antMatchers("/admin/**").access("ROLE_ADMIN"); // ROLE_ADMIN 권한으로만 접근할수 있음
//				.antMatchers("/test").permitAll() // /test 경로는 권한에 상관없이 모두 접근 가능
//				.antMatchers("/api/**").authenticated(); // /api 하위의 모든 리소스 접근 불가
//				.anyRequest().authenticated() // 나머지 요청은 모두 접근 불가
//		 */
//		
//		http
//			.sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//				.and()
//			.authorizeRequests()
//				.antMatchers("/login").permitAll()
//				.antMatchers("/test").permitAll()
//				.antMatchers("/api/**").authenticated()
//				.anyRequest().authenticated()
//				.and()
//            .httpBasic()
//            	.realmName(SECURITY_REALM)
//				.and()
//			.csrf().disable();
//	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(loginService).passwordEncoder(new ShaPasswordEncoder(ENCODING_STRENGTH));
		auth.userDetailsService(loginService).passwordEncoder(loginService.getPasswordEncoder());
	}
	
	@Bean 
	@Override 
	public AuthenticationManager authenticationManagerBean() throws Exception { 
		return super.authenticationManagerBean(); 
	}
	
	@Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//      //http://m.blog.naver.com/wndrlf2003/220649843082
////    KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource("server.jks"), "passtwo".toCharArray())
////			.getKeyPair("auth", "passone".toCharArray());
////	converter.setKeyPair(keyPair)
        converter.setSigningKey(SIGNING_KEY);
        return converter;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }
}
