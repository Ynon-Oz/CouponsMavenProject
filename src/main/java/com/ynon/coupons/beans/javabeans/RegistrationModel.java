package com.ynon.coupons.beans.javabeans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Ynon on  26/08/2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationModel {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

}
