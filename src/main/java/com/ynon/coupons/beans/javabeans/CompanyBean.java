package com.ynon.coupons.beans.javabeans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyBean {

	private Long id;
	private String name;
	private String address;
	private String phone;
	private String email;
	private String webSite;


	public CompanyBean(String companyName,  String companyAddress, String companyPhoneNumber,
			String companyFaxNumber, String companyWebSite) {
		super();
		this.name = companyName;
		this.address = companyAddress;
		this.phone = companyPhoneNumber;
		this.email = companyFaxNumber;
		this.webSite = companyWebSite;
	}

}

