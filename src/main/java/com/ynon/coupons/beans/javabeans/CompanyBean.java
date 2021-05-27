package com.ynon.coupons.beans.javabeans;


public class CompanyBean {

	private Long companyId;
	private String companyName;
	private String companyAddress;
	private String companyPhoneNumber;
	private String companyFaxNumber;
	private String companyWebSite;


	public CompanyBean(String companyName,  String companyAddress, String companyPhoneNumber,
			String companyFaxNumber, String companyWebSite) {
		super();
		this.companyName = companyName;
		this.companyAddress = companyAddress;
		this.companyPhoneNumber = companyPhoneNumber;
		this.companyFaxNumber = companyFaxNumber;
		this.companyWebSite = companyWebSite;
	}

	public CompanyBean(Long id, String companyName,  String companyAddress, String companyPhoneNumber,
			String companyFaxNumber, String companyWebSite) {
		this(companyName,  companyAddress, companyPhoneNumber, companyFaxNumber, companyWebSite);
		this.companyId = id;
	}

	public CompanyBean() {
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setId(Long id) {
		this.companyId = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getCompanyPhoneNumber() {
		return companyPhoneNumber;
	}

	public void setCompanyPhoneNumber(String companyPhoneNumber) {
		this.companyPhoneNumber = companyPhoneNumber;
	}

	public String getCompanyFaxNumber() {
		return companyFaxNumber;
	}

	public void setCompanyFaxNumber(String companyFaxNumber) {
		this.companyFaxNumber = companyFaxNumber;
	}

	public String getCompanyWebSite() {
		return companyWebSite;
	}

	public void setCompanyWebSite(String companyWebSite) {
		this.companyWebSite = companyWebSite;
	}

	public String toString() {
		return "Company [companyId=" + companyId + ", companyName=" + companyName + ", companyAddress=" + companyAddress
				+ ", companyPhoneNumber=" + companyPhoneNumber + ", companyFaxNumber=" + companyFaxNumber
				+ ", companyWebSite=" + companyWebSite + "]";
	}



}

