package com.ynon.coupons.beans;

import com.ynon.coupons.enums.UserType;

public class SuccessfulLoginData {

	private UserType userType;
	private long userId;
	private String token;

	
	
	public SuccessfulLoginData(UserType userType, long userId, String token) {
		this.userType = userType;
		this.userId = userId;
		this.token = token;
	}



	public UserType getUserType() {
		return userType;
	}



	public void setUserType(UserType userType) {
		this.userType = userType;
	}



	public long getUserId() {
		return userId;
	}



	public void setUserId(long userId) {
		this.userId = userId;
	}



	public String getToken() {
		return token;
	}



	public void setToken(String token) {
		this.token = token;
	}



	@Override
	public String toString() {
		return "SuccessfulLoginData [userType=" + userType + ", userId=" + userId 
				+ ", token=" + token + "]";
	}
	
	

	

}
