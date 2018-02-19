/*package com.walkingtree.oauth2.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

import com.walkingtree.oauth2.oauth.CustomAuthentcationProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	CustomAuthentcationProvider authenticationProvider;

	public void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(authenticationProvider);

	}

	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
	}

}*/