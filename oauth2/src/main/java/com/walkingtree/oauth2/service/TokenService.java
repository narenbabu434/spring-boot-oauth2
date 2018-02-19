package com.walkingtree.oauth2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import com.walkingtree.oauth2.models.SecureUser;
@Service
public class TokenService {

	@Autowired
	TokenStore tokenStore;

	public SecureUser getSecureUser(String accessToken) {
		OAuth2Authentication authentication = tokenStore.readAuthentication(accessToken);
		if(authentication!=null){
			SecureUser secureUser = ((SecureUser) authentication.getPrincipal());
			return secureUser;
		}
		else
		{
			return null;
		}
	}
}
