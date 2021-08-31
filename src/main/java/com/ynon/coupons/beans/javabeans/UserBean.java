package com.ynon.coupons.beans.javabeans;

import com.ynon.coupons.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserBean {


	private Long id;
	private String userName;
	private String password;
	private Long companyId;
	private UserType type;

	public UserBean(String userName, String password, Long companyId, UserType type) {
		this.userName = userName;
		this.password = password;
		this.companyId = companyId;
		this.type = type;
	}

}
