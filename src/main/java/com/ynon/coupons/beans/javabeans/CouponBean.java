 package com.ynon.coupons.beans.javabeans;



import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ynon.coupons.enums.CouponCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


 @Data
 @Builder
 @NoArgsConstructor
 @AllArgsConstructor
public class CouponBean {

	private long id;
	private long companyId;
	private CouponCategory type; 
	private String title;
	private String description;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private int amount;
	private float price;
	 @JsonProperty(value = "image")
	 private String image;

	


	public CouponBean(long companyId, CouponCategory type, String title, String description, LocalDateTime startDate,
					  LocalDateTime endDate, int amount, float price, String image) {
		super();
		this.companyId = companyId;
		this.type = type;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
	}


 }


