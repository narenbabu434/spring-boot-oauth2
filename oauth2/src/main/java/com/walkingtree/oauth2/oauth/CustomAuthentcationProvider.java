package com.walkingtree.oauth2.oauth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Component;

import com.walkingtree.oauth2.models.SecureUser;
import com.walkingtree.oauth2.models.UserTO;
import com.walkingtree.oauth2.service.UserService;

@Component

public class CustomAuthentcationProvider implements AuthenticationProvider {

	@Autowired
	UserService userService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UserTO user;
		String userName = authentication.getName();
        String password = authentication.getCredentials().toString();
        if (userName.contains("@") && userName.contains(".")) {
            user = userService.getUserByEmailId(userName);
        } else {
            user = userService.getUserByUsername(userName);
        }
        if (user == null) {
            throw new OAuth2Exception("Invalid login credentials");
        }

        
        BCryptPasswordEncoder bcrypt=new BCryptPasswordEncoder();
      

        if (bcrypt.matches(password, user.getPassword())) {

            List<GrantedAuthority> grantedAuths = new ArrayList<>();
            SecureUser secureUser=new SecureUser(user);
            UsernamePasswordAuthenticationToken  usernamePasswordAuthenticationToken =new UsernamePasswordAuthenticationToken(secureUser, password,
                    grantedAuths);
            return usernamePasswordAuthenticationToken;
	}
		return null;
	}
	@Override
	public boolean supports(final Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}


	
}
