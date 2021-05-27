package com.ynon.coupons.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ynon.coupons.beans.javabeans.CompanyBean;
import com.ynon.coupons.entities.Company;
import com.ynon.coupons.exceptions.ApplicationException;
import com.ynon.coupons.logic.CompanysController;


@RestController
@RequestMapping("/company")
public class CompanysApi {
	@Autowired
	CompanysController companysController;



	//CREATE
	@PostMapping
	public long addCompany(@RequestBody Company company) throws ApplicationException {
		return this.companysController.addCompany(company);	
	}


	//UPDATE
	@PutMapping
	public long updateCompany(@RequestBody Company company) throws ApplicationException {
		return this.companysController.updateCompany(company);
	}


	//GET
	@GetMapping("/{id}")
	public CompanyBean getCompany(@PathVariable("id") long id) throws ApplicationException {
		return this.companysController.getCompanyById(id);
	}

	//GET ALL
	@GetMapping
	public List<CompanyBean> getAllCompanies() throws ApplicationException{
		return this.companysController.getAllCompanies();
	}



	//DELETE
	@DeleteMapping("/{id}")
	public void deleteCompany(@PathVariable("id")long id) throws ApplicationException {
		this.companysController.deleteCompany(id);
	}

}



