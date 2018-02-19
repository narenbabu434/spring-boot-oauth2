package com.walkingtree.oauth2.filters;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.walkingtree.oauth2.models.SecureUser;
import com.walkingtree.oauth2.service.TokenService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthFilter extends ZuulFilter {
	private static final String INPUT_STREAM_CONVERSION_FAILED_MESSAGE = "Failed to convert to input stream";

	@Autowired
	ProxyRequestHelper helper;

	@Autowired
	TokenService tokenService;

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return RequestContext.getCurrentContext().getBoolean("shouldDoAuth");
	}

	@Override
	public Object run() {
		// TODO Auto-generated method stub
		RequestContext ctx = RequestContext.getCurrentContext();
		String authToken = (String) ctx.get("authToken");
		try {
			SecureUser user = tokenService.getSecureUser(authToken);
		} catch (HttpClientErrorException ex) {
			abortWithException(ctx, ex);
		}
		return null;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * private UserTO getUser(String authToken, RequestContext ctx) { String
	 * authURL = String.format("%s%s%s", authServiceHost, authUri, authToken);
	 * final HttpHeaders headers = new HttpHeaders(); final HttpEntity<Object>
	 * httpEntity = new HttpEntity<>(null, headers); return
	 * restTemplate.postForObject(authURL, httpEntity, UserTO.class); }
	 */

	private void abortWithException(RequestContext ctx, HttpClientErrorException ex) {
		ctx.setSendZuulResponse(false);
		try {
			helper.setResponse(ex.getStatusCode().value(), IOUtils.toInputStream(ex.getResponseBodyAsString()),
					ex.getResponseHeaders());
		} catch (IOException e) {
			log.error(INPUT_STREAM_CONVERSION_FAILED_MESSAGE, e);
			throw new RuntimeException(e);
		}
	}
}
