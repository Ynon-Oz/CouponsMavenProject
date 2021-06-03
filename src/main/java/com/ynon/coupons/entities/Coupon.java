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
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ynon.coupons.enums.CouponsCategory.CouponCategory;
import com.ynon.coupons.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Coupons")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Validated
public class Coupon {

	@Id
	@GeneratedValue
	@Column(name = "id")
	@JsonProperty("id")
	private long id;
	@NotNull
	@JoinColumn(name = "companyId")//, insertable = false, updatable = false)
	@ManyToOne (fetch = FetchType.LAZY)
	private Company company;

	//TODO Change CouponCategory to Entity

	@Column(name = "category", nullable = false)
	@Enumerated(EnumType.STRING)
	private CouponCategory type; 
	@Size(min = 2, max=22)
	@Column(name = "title",  nullable = false)
	private String title;
	@NotBlank
	@Column(name = "description", nullable = false)
	private String description;
//	@PastOrPresent
	@Column(name = "startDate",  nullable = false)
	private Date startDate;
//	@FutureOrPresent
	@Column(name = "endDate",  nullable = false)
	private Date endDate;
	@Positive
	@Column(name = "amount",  nullable = false)
	private int amount;
	@Positive
	@Column(name = "price",  nullable = false)
	private float price;
	//TODO Change to object to enable saving images
	@Column(name = "image")
	private String image;

	@OneToMany(mappedBy = "coupon", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<Purchase> purchases;



	
	


	public Coupon(long id, Company company, CouponCategory type, String title, String description, Date startDate,
			Date endDate, int amount, float price, String image) {
		this.id = id;
		this.company = company;
		this.type = type;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
		this.purchases = null;
	}

	public Coupon(long id, Company company, CouponCategory type, String title, String description, java.util.Date startDate,
			java.util.Date endDate, int amount, float price, String image) {
		this.id = id;
		this.company = company;
		this.type = type;
		this.title = title;
		this.description = description;
		this.startDate = DateUtils.convetDateFromUtilToSql(startDate);
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
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
		this.purchases = null;
	}






}


