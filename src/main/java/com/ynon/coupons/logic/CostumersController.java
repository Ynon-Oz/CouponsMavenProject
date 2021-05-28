package com.ynon.coupons.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ynon.coupons.beans.javabeans.CustomerBean;
import com.ynon.coupons.dao.ICustomersDao;
import com.ynon.coupons.entities.Customer;
import com.ynon.coupons.enums.ErrorType;
import com.ynon.coupons.exceptions.ApplicationException;


@Controller
public class CostumersController {

	@Autowired
	private ICustomersDao customersDao;
	@Autowired
	private UsersController usersController;


	//CREATE
	public void addCostumer(Customer customer) throws ApplicationException {
		if(this.usersController.isUserExist(customer.getUser().getUserName())) {
			throw new ApplicationException(ErrorType.USER_ALLREADY_EXIST, ErrorType.USER_ALLREADY_EXIST.getErrorMessage());
		}
		//TODO set first letter of first and last name to capital letter
		customer.getUser().setPassword(usersController.obfuscation(customer.getUser().getPassword()));

		this.customersDao.save(customer);
		//TODO register confirmation mail
	}



	//GET
	public CustomerBean getCustomerBean(long costumerId) throws ApplicationException {
		return  this.customersDao.getCustomer(costumerId);
	}

	public Customer getCustomer(long costumerId) throws ApplicationException {
		return  this.customersDao.findById(costumerId);
	}

	//GET ALL
	public List<CustomerBean> getAllCustomers() throws ApplicationException{
		return this.customersDao.getAllCustomers();
	}


	//UPDATE
	public long updateCostumer(Customer customer) throws ApplicationException {
		Customer updatedCustomer = new Customer();
		updatedCustomer = this.customersDao.getOne(customer.getId());
		updatedCustomer.setFirstName(customer.getFirstName());
		updatedCustomer.setLastName(customer.getLastName());
		updatedCustomer.setAddress(customer.getAddress());
		updatedCustomer.setPhone(customer.getPhone());
		updatedCustomer.getUser().setPassword(usersController.obfuscation(customer.getUser().getPassword()));
		return this.customersDao.saveAndFlush(updatedCustomer).getUser().getId();
	}



	//DELETE
	public void deleteCustomerByCustomerId(long costumerId) throws ApplicationException {
		this.customersDao.deleteById(costumerId);

	}
}
