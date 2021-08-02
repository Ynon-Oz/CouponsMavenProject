package com.ynon.coupons.messages;

public class EmailMessages {


    public static final String SUBJECT_NEW_USER_REGISTRATION =
            "Register confirmation mail";

    public static final String REGISTRATION_CONFIRMATION_EMAIL_TEMPLATE =
            "Dear %s,\n" + "\n"
                    + "Thank you for choose becoming a member of Couponation,\n"
                    + "Please click the link below to activate your account:\n"
                    + " %s%s.\n"
                    + "right after activation you will be able to enjoy our services\n" +
                    "\n"
                    + "Always at your service,\n"
                    + "Couponation Team.";




    public static final String SUBJECT_PURCHASE_CONFIRMATION =
            "Register confirmation mail";

    public static final String PURCHASE_EMAIL_TEMPLATE =
            "Dear %s,\n" + "\n"
                    + "Thank you for purchasing %s, your purchase details:\n"
                    +"Qty: %s\n"
                    +"Price: %s\n"
                    +"Date of issue: %s\n"
                    +"the products will be shipped in the following 24 hrs, \n"
                    +"expect to receive them between 7-10 working days.\n"

                    + "\n"
                    + "Always at your service,\n"
                    + "CouponSystem Team.";

}