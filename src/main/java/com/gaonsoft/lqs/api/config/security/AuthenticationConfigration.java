package com.gaonsoft.lqs.api.config.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
public class AuthenticationConfigration extends AuthorizationServerConfigurerAdapter {
	static final String SCOPE_READ = "read";
	static final String SCOPE_WRITE = "write";
	static final Integer ENCODING_STRENGTH = 256;
	static final String SECURITY_REALM = "Spring Boot JWT Example Realm";

	@Value("${security.oauth2.client.authorized-grant-types}")
	private String[] GRANT_TYPES;
	
	@Value("${security.oauth2.client.id}")
	private String CLIENT_ID;
	
	@Value("${security.oauth2.client.access-token-validity-seconds}")
	private int ACCESS_TOKEN_VALIDITY_SECONDS;
	
	@Value("${security.oauth2.client.refresh-token-validity-seconds}")
	private int REFRESH_TOKEN_VALIDITY_SECONDS;
	
	@Value("${security.oauth2.resource.jwt.key-value}")
	private String SIGNING_KEY;
	
	@Value("${security.oauth2.resource.id}")
	private String RESOURCES_IDS;
	
	@Autowired
    private TokenStore tokenStore;
    
    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;

    @Autowired
    private AuthenticationManager authenticationManager;

	@Override
	public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
		// client에 대한 정보를 설정
		configurer.inMemory()
			.withClient(CLIENT_ID)
			.authorizedGrantTypes("password")
			.scopes(SCOPE_READ, SCOPE_WRITE)
			.authorities("ROLE_USER")
			.resourceIds(RESOURCES_IDS)
			.accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
			.secret(SIGNING_KEY);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		// OAuth2 서버가 동작하기 위한 endpoint에 대한 정보를 설정
		TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
		enhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter));
		endpoints
			.tokenStore(tokenStore)
			.accessTokenConverter(accessTokenConverter)
			.tokenEnhancer(enhancerChain)
			.authenticationManager(authenticationManager);
	}
}
