package com.ynon.coupons.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ynon.coupons.beans.javabeans.CustomerBean;
import com.ynon.coupons.entities.Customer;

public interface ICustomersDao extends JpaRepository<Customer, Long> {
	public Customer findById(long id);

	@Query("SELECT new com.ynon.coupons.beans.javabeans.CustomerBean(id,  firstName, lastName, phone, address) FROM Customer WHERE id= :userId")
	public CustomerBean getCustomer(@Param("userId")long userId);

	@Query("SELECT new com.ynon.coupons.beans.javabeans.CustomerBean(id,  firstName, lastName, phone, address) FROM Customer ")
	public List<CustomerBean> getAllCustomers();



}
