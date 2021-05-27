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

		customer.setUserPassword(usersController.obfuscation(customer.getUserPassword()));
		this.customersDao.save(customer);
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
		Customer update = new Customer();
		update = this.customersDao.getOne(customer.getUserId());
		update.setFirstName(customer.getFirstName());
		update.setLastName(customer.getLastName());
		update.setAddress(customer.getAddress());
		update.setPhone(customer.getPhone());
		update.setUserPassword(usersController.obfuscation(customer.getUserPassword()));
		return this.customersDao.saveAndFlush(update).getUser().getUserId();
	}



	//DELETE
	public void deleteCustomerByCustomerId(long costumerId) throws ApplicationException {
		this.customersDao.deleteById(costumerId);

	}
}
