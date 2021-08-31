package com.ynon.coupons.beans.javabeans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerBean {

	private long id;
	private String firstName;
	private String lastName;
	private String phone;
	private String address;



	public CustomerBean( String firstName, String lastName, String phone, String address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.address = address;
	}




}
