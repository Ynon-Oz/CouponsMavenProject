package com.ynon.coupons.api;

import java.util.List;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ynon.coupons.beans.javabeans.CustomerBean;
import com.ynon.coupons.entities.Customer;
import com.ynon.coupons.exceptions.ApplicationException;
import com.ynon.coupons.logic.CostumersController;

@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomersApi {
    @Autowired
    CostumersController customersController;

    //CREATE
    @PostMapping("/register")
    @ApiOperation(value = "Registry process",
            notes = "Add a new client to DB")
    @ApiResponses({
            @ApiResponse(code = 201, message = "The client have been added successfully to DB"),
            @ApiResponse(code = 400, message = "Details not matches rules")
    })
    public void addCustomer(@RequestBody Customer customer) throws ApplicationException {
        log.debug("Customer added to DB: " + customer);
        this.customersController.addCostumer(customer);
    }


    //UPDATE
    @PutMapping
    public void updateCustomer(@RequestBody Customer customer) throws ApplicationException {
        this.customersController.updateCostumer(customer);
    }


    //GET
    @ApiOperation(value = "Get Customer details",
            notes = "Get Customer details from DB")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ask for details successfully"),
            @ApiResponse(code = 400, message = "Details not matches rules")
    })
    @GetMapping("/{id}")
    public Customer getCustomer(@ApiParam("Id of customer to get") @PathVariable("id") long id) throws ApplicationException {
        return this.customersController.getCustomer(id);
    }

    //GET ALL
    @GetMapping
    public List<CustomerBean> getAllCustomers() throws ApplicationException {
        return this.customersController.getAllCustomers();
    }


    //DELETE
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") long id) throws ApplicationException {
        log.debug("Deleting customer id#: " + id);
        this.customersController.deleteCustomerByCustomerId(id);
    }

}



