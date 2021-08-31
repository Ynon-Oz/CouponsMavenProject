package com.ynon.coupons.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ynon.coupons.enums.CouponCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class Coupon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@JsonProperty("id")
	private long id;
//	@NotNull
	@JoinColumn(name = "companyId")//, insertable = false, updatable = false)
	@ManyToOne (fetch = FetchType.LAZY , cascade = CascadeType.PERSIST)
	private Company company;

	//TODO Change CouponCategory to Entity

	@Column(name = "category", nullable = false)
	@Enumerated(EnumType.STRING)
	private CouponCategory type;
	@Size(min = 2, max=22)
	@Column(name = "title",  nullable = false, length = 30)
	private String title;
	@NotBlank
	@Column(name = "description", nullable = false, length = 50)
	private String description;
//	@PastOrPresent
	@Column(name = "startDate",  nullable = false)
	private LocalDateTime startDate;
//	@FutureOrPresent
	@Column(name = "endDate",  nullable = false)
	private LocalDateTime endDate;
	@Positive
	@Column(name = "amount",  nullable = false)
	private int amount;
	@Positive
	@Column(name = "price",  nullable = false)
	private float price;
	//TODO Change to object to enable saving images
	@Column(name = "image", length = 30)
	private String image;
//	@JsonProperty(value = "image")
//	private UUID image;

	@OneToMany(mappedBy = "coupon", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<Purchase> purchases;



	
	


	public Coupon(long id, Company company, CouponCategory type, String title, String description, LocalDateTime startDate,
				  LocalDateTime endDate, int amount, float price, String image) {
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




	public Coupon(Company company, CouponCategory type, String title, String description, LocalDateTime startDate, LocalDateTime endDate,
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


