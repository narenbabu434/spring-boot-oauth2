package com.walkingtree.oauth2.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import com.walkingtree.oauth2.service.CustomTokenEnricher;
import com.walkingtree.oauth2.service.CustomUserDetailService;

@EnableAuthorizationServer
@Configuration
public class AuthroizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	TokenStore tokenStore;
	@Autowired
	CustomUserDetailService userDetailsService;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private CustomTokenEnricher customTokenEnhancer;

	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient("walkingtree-client").secret("walkingtree-password")
				.accessTokenValiditySeconds(20000)
				.authorizedGrantTypes("autherization_code", "refresh_token", "password").scopes("read", "write")
				.refreshTokenValiditySeconds(20000);
	}

	public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
		endpoints.tokenStore(tokenStore).authenticationManager(authenticationManager)
				.userDetailsService(userDetailsService).tokenEnhancer(customTokenEnhancer);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()")
				.allowFormAuthenticationForClients();
	}

}
