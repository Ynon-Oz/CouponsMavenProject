package com.ynon.coupons.beans;

import com.ynon.coupons.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostUserData {


	private long id;
	private Long companyId;
	private UserType userType;




	public PostUserData(long id, UserType userType) {
		this.id = id;
		this.companyId = null;
		this.userType = userType;
	}


}
