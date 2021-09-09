package com.ynon.coupons.beans;

import com.ynon.coupons.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor

public class SuccessfulLoginData {
	private String name;
	private UserType userType;
	private long userId;
	private String token;


	

	

}
