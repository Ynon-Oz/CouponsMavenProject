package com.ynon.coupons.enums;

/**
 * Created by Ynon on  25/08/2021
 */
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

    CouponCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
