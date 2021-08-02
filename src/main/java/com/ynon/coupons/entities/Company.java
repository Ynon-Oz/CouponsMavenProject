package com.ynon.coupons.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;

@Entity
@Table(name = "Companies")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Builder
public class Company  {
	@Id
	@GeneratedValue
	@Column(name = "companyID")
	private long companyId;

	@Column(name = "Name", unique = true, nullable = false)
	private String companyName;

	@Column(name = "Address", nullable = false)
	private String companyAddress;

	@Column(name = "PhoneNum", unique = true, nullable = false)
	private String companyPhoneNumber;

	@Column(name = "FaxNum", unique = true)
	private String companyFaxNumber;

	@Column(name = "webSite", unique = true)
	private String companyWebSite;
	@JsonIgnore
	@OneToMany(mappedBy = "company", cascade = {CascadeType.REMOVE,CascadeType.PERSIST}, fetch = FetchType.LAZY)
	private List<Coupon> coupons;
	@JsonIgnore
	@OneToMany(mappedBy = "company", cascade = {CascadeType.REMOVE,CascadeType.PERSIST}, fetch = FetchType.LAZY)
	private List<User> users;



	public Company() {
	}
	
	

	public Company(String companyName, String companyAddress, String companyPhoneNumber, String companyFaxNumber,
			String companyWebSite, List<Coupon> coupons, List<User> users) {
		this.companyName = companyName;
		this.companyAddress = companyAddress;
		this.companyPhoneNumber = companyPhoneNumber;
		this.companyFaxNumber = companyFaxNumber;
		this.companyWebSite = companyWebSite;
		this.coupons = coupons;
		this.users = users;
	}



	public long getCompanyId() {
		return companyId;
	}

	public void setId(long id) {
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

	
	public List<Coupon> getCoupons() {
		return coupons;
	}



	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}



	public List<User> getUsers() {
		return users;
	}



	public void setUsers(List<User> users) {
		this.users = users;
	}



	public String toString() {
		return "Company [companyId=" + companyId + ", companyName=" + companyName + ", companyAddress=" + companyAddress
				+ ", companyPhoneNumber=" + companyPhoneNumber + ", companyFaxNumber=" + companyFaxNumber
				+ ", companyWebSite=" + companyWebSite + "]";
	}



}

