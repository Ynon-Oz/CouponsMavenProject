package com.ynon.coupons.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorBean {

	private int errNum;
	private String errMsg;
	private boolean errorName;


}
