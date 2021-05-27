package com.ynon.coupons.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "customers")

public class Customer  {
	@Id
	@Column(name="id")
	private long id;
	
	@MapsId
//	@JsonIgnore
//	@JoinColumn(name = "userId")
	@OneToOne (fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
	private User user;

	@Column(name = "firstName",  nullable = false)
	private String firstName;

	@Column(name = "lastName",  nullable = false)
	private String lastName;

	@Column(name = "phone", nullable = false)
	private String phone;

	@Column(name = "address", nullable = false)
	private String address;
	
	@JsonIgnore
	@OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
	private List<Purchase> purchases;

	public Customer() {
	}

	public long getUserId() {
		return id;
	}

	public void setUserId(long userId) {
		this.id = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public String getUserName() {
		return user.getUserName();
	}

	public void setUserName(String userName) {
		this.user.setUserName(userName); 
	}
	public String getUserPassword() {
		return user.getPassword();
	}

	public void setUserPassword(String password) {
		this.user.setPassword(password);
	}
	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

	@Override
	public String toString() {
		return "Customer [userId=" + id + ", user=" + user  + ", firstName="
				+ firstName + ", lastName=" + lastName + ", phone=" + phone + ", address=" + address + ", purchases="
				+ purchases + "]";
	}



}
