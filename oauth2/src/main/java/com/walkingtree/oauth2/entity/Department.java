package com.walkingtree.oauth2.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="department")
@Getter
@Setter
public class Department {
@Id
@GeneratedValue (strategy=GenerationType.AUTO)
private long id;

private String name;

private String departmentCode;


}
