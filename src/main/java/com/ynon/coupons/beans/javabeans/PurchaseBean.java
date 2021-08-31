package com.ynon.coupons.beans.javabeans;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
@Data
public class PurchaseBean {
	private long id;
	private Long costumerId;
	private Long couponId;
	private int amount;
	private LocalDateTime timeStamp;

	public PurchaseBean(long costumerId, long couponId, int amount, LocalDateTime timeStamp) {
		this.costumerId = costumerId;
		this.couponId = couponId;
		this.amount = amount;
		this.timeStamp = timeStamp;
	}

	public PurchaseBean(long id, long costumerId, long couponId, int amount, LocalDateTime timeStamp) {
		this(costumerId, couponId, amount, timeStamp);
		this.id = id;
	}

	public PurchaseBean() {
	}




}
