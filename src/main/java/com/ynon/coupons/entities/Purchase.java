package com.ynon.coupons.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="Purchases")

public class Purchase {
	@Id
	@GeneratedValue
	@Column(name = "purchaseID", nullable = false, unique = true)
	private long id;
	@JsonIgnore
	@JoinColumn(name = "userId", nullable = false, unique = false)
	@ManyToOne (fetch = FetchType.LAZY)
	private Customer customer;
	@JsonIgnore
	@JoinColumn(name = "couponId", nullable = false, unique = false)
	@ManyToOne (fetch = FetchType.LAZY)
	private Coupon coupon;

	@Column(name = "amount", nullable = false)
	private int amount;

	@Column(name = "timeStamp", nullable = false)
	private Date timeStamp;


	public Purchase() {
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public Coupon getCoupon() {
		return coupon;
	}


	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public Date getTimeStamp() {
		return timeStamp;
	}


	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}


	@Override
	public String toString() {
		return "Purchase [id=" + id + ", customer=" + customer + ", coupon=" + coupon + ", amount=" + amount
				+ ", timeStamp=" + timeStamp + "]";
	}


}
