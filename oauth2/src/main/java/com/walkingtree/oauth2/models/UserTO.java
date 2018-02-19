package com.walkingtree.oauth2.models;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserTO  implements Serializable{

	
	private long id;

	private String name;

	private String email;

	private String mobileNumber;

	//private List<AddressTO> address;

	private DepartmentTO department;

	private String username;

	private String password;

}
