package com.walkingtree.oauth2.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class OAuthUser {

	private Long id;
	private String userName;
	private String name;
	private String mobileNumber;
	private String emailId;
	private String locale;
	private String type;
	private List<Role> roles;
	private boolean active;
	private String tenantId;

}
