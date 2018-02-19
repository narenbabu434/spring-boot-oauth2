package com.walkingtree.oauth2.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.walkingtree.oauth2.enums.AddressType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "address")
@Getter
@Setter
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String houseNo;

	private String street;

	private String city;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
	private User user;

	private String state;

	private String Country;

	private boolean isAddressSame;

	@Enumerated(EnumType.STRING)
	private AddressType addressType;
}
