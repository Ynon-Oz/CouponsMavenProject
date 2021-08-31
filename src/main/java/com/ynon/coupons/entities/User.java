package com.ynon.coupons.entities;




import javax.persistence.*;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ynon.coupons.enums.UserType;
import lombok.*;
import org.springframework.validation.annotation.Validated;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
@Entity
@Builder
@Table(name="Users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private long id;

	@Email
	@Column( unique = true, nullable = true, length = 40)
	private String email;

	@Column(name = "PASSWORD", nullable = false, length = 30)
	private String password;
	@JsonIgnore
	@ToString.Exclude
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "companyID", nullable = true, unique = false)
	private Company company;

	@Enumerated(EnumType.STRING)
	@Column (name = "TYPE", nullable = false)
	private UserType type;

	private boolean isActivated;



	public User(String email, String password, Company company, UserType type, boolean isActivated) {
		this.email = email;
		this.password = password;
		this.company = company;
		this.type = type;
		this.isActivated=isActivated;
	}



}
