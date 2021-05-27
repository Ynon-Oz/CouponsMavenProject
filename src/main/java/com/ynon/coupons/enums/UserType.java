package com.ynon.coupons.enums;

public enum UserType {
	ADMIN("ADMIN"),
	CUSTOMER("CUSTOMER"),
	COMPANY("COMPANY");

	private String name;

	UserType(String type){
		this.name = type;
	}

	public String getName() {
		return this.name;
	}


}
