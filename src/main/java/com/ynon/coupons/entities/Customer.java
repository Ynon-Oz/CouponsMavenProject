package com.ynon.coupons.entities;

import java.time.LocalDate;
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
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer  {
	@Id
	@Column(name="id")
	private long id;
	
	@MapsId
//	@JsonIgnore
//	@JoinColumn(name = "userId")
	@OneToOne (fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
	private User user;
	@Size (min=2,max=20,message = "First name should contain 2-20 characters")
	@Column(name = "firstName",  nullable = false)
	private String firstName;

	@Column(name = "lastName",  nullable = false)
	private String lastName;

	@Column(name = "phone", nullable = false)
	private String phone;
	//TODO divide to City, Street, House#, Apartment, Zip and use Third Party API to detect inputs
	@Column(name = "address", nullable = false)
	private String address;
	
	@JsonIgnore
	@OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
	private List<Purchase> purchases;

//	@PastOrPresent
	private LocalDate birthday;




}
