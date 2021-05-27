package com.ynon.coupons.beans.javabeans;

import com.ynon.coupons.enums.UserType;

public class UserBean {


	private Long id;
	private String userName;
	private String password;
	private Long companyId;
	private UserType type;

	public UserBean(String userName, String password, Long companyId, UserType type) {
		this.userName = userName;
		this.password = password;
		this.companyId = companyId;
		this.type = type;
	}

	public UserBean(long id, String userName, String password, Long companyId, UserType type) {
		this(userName, password, companyId, type);
		this.id = id;
	}

	public UserBean() {
	}

	public long getUserId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName +  ", companyId=" + companyId
				+ ", type=" + type + "]";
	}


}
