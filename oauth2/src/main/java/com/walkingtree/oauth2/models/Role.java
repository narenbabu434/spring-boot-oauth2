package com.walkingtree.oauth2.models;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode(of = "code")
public class Role {
	
	private Long id;
    private String name;
    private String code;
    private String description;
    private Long createdBy;
    private Date createdDate;
    private Long lastModifiedBy;
    private Date lastModifiedDate;
    private String tenantId;

}
