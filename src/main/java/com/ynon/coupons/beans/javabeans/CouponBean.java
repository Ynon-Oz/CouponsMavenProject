 package com.ynon.coupons.beans.javabeans;



import java.sql.Date;
import java.time.LocalDateTime;

import com.ynon.coupons.enums.CouponsCategory.CouponCategory;
import com.ynon.coupons.utils.DateUtils;



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





	public CouponBean(long id, long companyId, CouponCategory type, String title, String description, LocalDateTime startDate,
					  LocalDateTime endDate, int amount, float price, String image) {
		super();
		this.id = id;
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





	public CouponBean() {

	}



	

	public String toString() {
		return "Coupon [id=" + id + ", companyId=" + companyId + ", type=" + type + ", title=" + title
				+ ", description=" + description + ", startDate=" + startDate + ", endDate=" + endDate + ", amount="
				+ amount + ", price=" + price + ", image=" + image + "]";
	}



	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public CouponCategory getType() {
		return type;
	}
	public void setType(CouponCategory type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	public LocalDateTime getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}


