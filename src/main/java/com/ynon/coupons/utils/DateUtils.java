package com.ynon.coupons.utils;

import java.util.Date;

public class DateUtils {

	public static java.sql.Date convetDateFromUtilToSql(Date Date) {
		java.util.Date utilStartDate = Date;
		java.sql.Date sqlDate = new java.sql.Date(utilStartDate.getTime());

		return sqlDate;
	}
}
