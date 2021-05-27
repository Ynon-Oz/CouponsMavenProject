package com.ynon.coupons.beans.javabeans;

import java.util.Date;

public class PurchaseBean {
	private long id;
	private Long costumerId;
	private Long couponId;
	private int amount;
	private Date timeStamp;

	public PurchaseBean(long costumerId, long couponId, int amount, Date timeStamp) {
		this.costumerId = costumerId;
		this.couponId = couponId;
		this.amount = amount;
		this.timeStamp = timeStamp;
	}

	public PurchaseBean(long id, long costumerId, long couponId, int amount, Date timeStamp) {
		this(costumerId, couponId, amount, timeStamp);
		this.id = id;
	}

	public PurchaseBean() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCostumerId() {
		return costumerId;
	}

	public void setCostumerId(long costumerId) {
		this.costumerId = costumerId;
	}

	public long getCouponId() {
		return couponId;
	}

	public void setCouponId(long couponId) {
		this.couponId = couponId;
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

	public String toString() {
		return "Purchase [id=" + id + ", costumerId=" + costumerId + ", couponId=" + couponId + ", amount=" + amount
				+ ", timeStamp=" + timeStamp + "]";
	}


}
