package com.ynon.coupons.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ynon.coupons.beans.javabeans.CompanyBean;
import com.ynon.coupons.dao.ICompaniesDao;
import com.ynon.coupons.entities.Company;
import com.ynon.coupons.enums.ErrorType;
import com.ynon.coupons.exceptions.ApplicationException;
import org.springframework.stereotype.Service;

@Service
public class CompanysController {

    @Autowired
    private ICompaniesDao companiesDao;


    public CompanysController() {
    }

    //ADD
    public long addCompany(Company company) throws ApplicationException {
        validations(company);
        return this.companiesDao.save(company).getCompanyId();
    }


    //GET
    public CompanyBean getCompanyById(long companyId) throws ApplicationException {
        return this.companiesDao.getCompanyById(companyId);
    }

    public Company getCompanyFindById(long companyId) throws ApplicationException {
        return this.companiesDao.findById(companyId).get();
    }

    public List<CompanyBean> getAllCompanies() throws ApplicationException {
        return this.companiesDao.getAllCompanies();
    }

    //UPDATE
    public long updateCompany(Company company) throws ApplicationException {
        Company updated = new Company();
        updated = this.companiesDao.getOne(company.getCompanyId());
        //TODO ???????  -----> Where do i get the id from? <------ ???????
        updated.setCompanyAddress(company.getCompanyAddress());
        updated.setCompanyFaxNumber(company.getCompanyFaxNumber());
        updated.setCompanyPhoneNumber(company.getCompanyPhoneNumber());
        updated.setCompanyWebSite(company.getCompanyWebSite());
        return this.companiesDao.saveAndFlush(updated).getCompanyId();
    }


    //DELETE

    public void deleteCompany(long companyId) throws ApplicationException {
        this.companiesDao.deleteById(companyId);

    }

    //Validations
    private void validations(Company company) throws ApplicationException {
        if (company == null) {
            throw new ApplicationException(ErrorType.COMPANY_MISMATCH, "Company details are missing");
        }

        if (company.getCompanyName().length() < 2) {
            throw new ApplicationException(ErrorType.COMPANY_NAME_SHORT, "Company name too short");

        }

        if (existsByName(company.getCompanyName())) {
            throw new ApplicationException(ErrorType.INVALID_COMPANY, "Company already exist");
        }
    }

    private boolean existsByName(String companyName) {
        Company c = new Company();
        c = companiesDao.findByCompanyName(companyName);
        if (c == null) {
            return false;
        }
        return true;
    }


}
