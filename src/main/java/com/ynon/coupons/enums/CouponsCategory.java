package com.ynon.coupons.enums;

public class CouponsCategory {
	public enum CouponCategory {
		FOOD("Food"),
		ELECTICITY("Electricity"),
		RESTURANT("Restaurant"),
		VACATION("Vacation"),
		SPA("Spa"),
		ATTRACTION("Attraction"),
		FURNITURE("Furniture"),
		FASHION("Fashion"),
		SPORT("Sport"),
		CHILDREN("Children"),
		VEHICLE("Vehicle");

		private String name;

		CouponCategory(String type){
			this.name = type;
		}

		public String getName() {
			return this.name;
		}


	}
}
