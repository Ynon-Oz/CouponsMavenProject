package com.ynon.coupons.api;

import com.ynon.coupons.beans.javabeans.RegistrationModel;
import com.ynon.coupons.entities.Customer;
import com.ynon.coupons.entities.User;
import com.ynon.coupons.enums.UserType;
import com.ynon.coupons.exceptions.ApplicationException;
import com.ynon.coupons.logic.CostumersController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Ynon on  26/08/2021
 */
@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterApi {

    private final CostumersController customersController;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void customerRegistration(@RequestBody RegistrationModel regModel) throws ApplicationException {
        System.out.println("****************************");
        System.out.println("RegModel: "+regModel);
        System.out.println("****************************");

        Customer customer = new Customer();
        User user = new User();
        user.setEmail(regModel.getEmail());
        user.setPassword(regModel.getPassword());
        user.setType(UserType.CUSTOMER);
        customer.setFirstName(regModel.getFirstName());
        customer.setLastName(regModel.getLastName());
        customer.setAddress(regModel.getAddress());
        customer.setPhone(regModel.getPhone());
        customer.setUser(user);
        System.out.println("CustomerModel: "+customer);
        System.out.println("****************************");
        customersController.addCostumer(customer);

    }
}
