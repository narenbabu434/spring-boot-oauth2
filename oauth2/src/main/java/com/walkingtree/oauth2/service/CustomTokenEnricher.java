package com.walkingtree.oauth2.service;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.stereotype.Service;

@Service
public class CustomTokenEnricher extends TokenEnhancerChain{

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken token, OAuth2Authentication authentication) {
		// TODO Auto-generated method stub
		return super.enhance(token, authentication);
	}

}
