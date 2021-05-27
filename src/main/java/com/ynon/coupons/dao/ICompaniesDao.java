package com.ynon.coupons.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ynon.coupons.beans.javabeans.CompanyBean;
import com.ynon.coupons.entities.Company;

public interface ICompaniesDao extends JpaRepository<Company, Long> {

	@Query("SELECT new com.ynon.coupons.beans.javabeans.CompanyBean(companyId, companyName, companyAddress, companyPhoneNumber,companyFaxNumber,companyWebSite) FROM Company c WHERE c.companyId= :companyId")
	CompanyBean getCompanyById(@Param("companyId")long companyId);

	@Query("SELECT new com.ynon.coupons.beans.javabeans.CompanyBean(companyId, companyName, companyAddress, companyPhoneNumber,companyFaxNumber,companyWebSite) FROM Company")
	List<CompanyBean> getAllCompanies();

	Company findByCompanyName(String companyName);

//	boolean existsByName(String name);



	

}
