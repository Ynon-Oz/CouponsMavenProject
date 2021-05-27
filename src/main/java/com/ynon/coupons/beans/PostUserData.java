package com.ynon.coupons.beans;

import com.ynon.coupons.enums.UserType;

public class PostUserData {


	private long id;
	private Long companyId;
	private UserType userType;


	public PostUserData(long id, Long companyId, UserType userType) {
		this(id,userType);
		this.companyId = companyId;
	}

	public PostUserData(long id, UserType userType) {
		this.id = id;
		this.companyId = null;
		this.userType = userType;
	}

	public long getId() {
		return id;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public UserType getUserType() {
		return userType;
	}



}
