package com.ynon.coupons.entities;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ynon.coupons.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
@Entity
@Table(name="Users")
public class User {

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false, unique = true)
	private long id;

	@Email
	@Column( unique = true, nullable = true)
	private String userName;

	@Column(name = "PASSWORD", nullable = false)
	private String password;
	@JsonIgnore
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "companyID", nullable = true, unique = false)
	private Company company;

	@Enumerated(EnumType.STRING)
	@Column (name = "TYPE", nullable = false)
	private UserType type;

	private boolean isActivated;



	public User(String userName, String password, Company company, UserType type,boolean isActivated) {
		this.userName = userName;
		this.password = password;
		this.company = company;
		this.type = type;
		this.isActivated=isActivated;
	}



}
