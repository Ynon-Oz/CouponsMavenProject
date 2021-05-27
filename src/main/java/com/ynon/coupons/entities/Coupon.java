package com.ynon.coupons.entities;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ynon.coupons.enums.CouponsCategory.CouponCategory;
import com.ynon.coupons.utils.DateUtils;


@Entity
@Table(name = "Coupons")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Coupon {

	@Id
	@GeneratedValue
	@Column(name = "id")
	@JsonProperty("id")
	private long id;

	@JoinColumn(name = "companyId")//, insertable = false, updatable = false)
	@ManyToOne (fetch = FetchType.LAZY)
	private Company company;

	@Column(name = "category", nullable = false)
	@Enumerated(EnumType.STRING)
	private CouponCategory type; 

	@Column(name = "title",  nullable = false)
	private String title;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "startDate",  nullable = false)
	private Date stratDate;

	@Column(name = "endDate",  nullable = false)
	private Date endDate;

	@Column(name = "amount",  nullable = false)
	private int amount;

	@Column(name = "price",  nullable = false)
	private float price;

	@Column(name = "image")
	private String image;

	@OneToMany(mappedBy = "coupon", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<Purchase> purchases;


	public Coupon() {

	}
	
	


	public Coupon(long id, Company company, CouponCategory type, String title, String description, Date stratDate,
			Date endDate, int amount, float price, String image) {
		this.id = id;
		this.company = company;
		this.type = type;
		this.title = title;
		this.description = description;
		this.stratDate = stratDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
		this.purchases = null;
	}

	public Coupon(long id, Company company, CouponCategory type, String title, String description, java.util.Date stratDate,
			java.util.Date endDate, int amount, float price, String image) {
		this.id = id;
		this.company = company;
		this.type = type;
		this.title = title;
		this.description = description;
		this.stratDate = DateUtils.convetDateFromUtilToSql(stratDate);
		this.endDate = DateUtils.convetDateFromUtilToSql(endDate);
		this.amount = amount;
		this.price = price;
		this.image = image;
		this.purchases = null;
	}


	public Coupon(Company company, CouponCategory type, String title, String description, Date startDate, Date endDate,
			int amount, float price, String image) {
		this.company = company;
		this.type = type;
		this.title = title;
		this.description = description;
		this.stratDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
		this.purchases = null;
	}





	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Company getCompany() {
		return company;
	}


	public void setCompany(Company company) {
		this.company = company;
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


	public Date getStratDate() {
		return stratDate;
	}


	public void setStratDate(Date stratDate) {
		this.stratDate = stratDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
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


	public List<Purchase> getPurchases() {
		return purchases;
	}


	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}


	@Override
	public String toString() {
		return "Coupon [id=" + id + ", company=" + company + ", type=" + type + ", title=" + title + ", description="
				+ description + ", stratDate=" + stratDate + ", endDate=" + endDate + ", amount=" + amount + ", price="
				+ price + ", image=" + image + ", purchases=" + purchases + "]";
	}



}


