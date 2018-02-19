package com.walkingtree.oauth2.models;

import com.walkingtree.oauth2.enums.AddressType;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AddressTO {


	private long id;

	private String houseNo;

	private String street;

	private String city;

	
	private UserTO user;

	private String state;

	private String Country;

	private boolean isAddressSame;

	
	private AddressType addressType;
}
