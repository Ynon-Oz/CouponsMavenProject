package com.ynon.coupons.api;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

	public void addCustomer(@RequestBody Customer customer) throws ApplicationException {
		log.debug("Customer added to DB: "+customer);
		this.customersController.addCostumer(customer);
	}


	//UPDATE
	@PutMapping
	public void updateCustomer(@RequestBody Customer customer) throws ApplicationException {
		this.customersController.updateCostumer(customer);
	}


	//GET
	@GetMapping("/{id}")
	public Customer getCustomer(@PathVariable("id") long id) throws ApplicationException {
		return this.customersController.getCustomer(id);
	}

	//GET ALL
	@GetMapping
	public List<CustomerBean> getAllCustomers() throws ApplicationException{
		return this.customersController.getAllCustomers();
	}



	//DELETE
	@DeleteMapping("/{id}")
	public void deleteCustomer(@PathVariable("id")long id) throws ApplicationException {
		log.debug("Deleting customer id#: "+id);
		this.customersController.deleteCustomerByCustomerId(id);
	}

}



