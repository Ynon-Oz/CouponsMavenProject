package com.ynon.coupons.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ynon.coupons.entities.User;
import com.ynon.coupons.mapper.CompaniesMapper;
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
    @Autowired
    private CompaniesMapper companiesMapper;


    public CompanysController() {
    }

    //ADD
    public Company addCompany(Company company) throws ApplicationException {
        validations(company);
        System.out.println("************************"+company+"******************");
        return this.companiesDao.save(company);
    }


    //GET
    public CompanyBean getCompanyById(long companyId) throws ApplicationException {
        return companiesMapper.toDto(this.companiesDao.getOne(companyId));
    }

    public Company getCompanyFindById(long companyId) throws ApplicationException {
        return this.companiesDao.findById(companyId).get();
    }

    public List<CompanyBean> getAllCompanies() throws ApplicationException {
        List<Company> entities = this.companiesDao.findAll();
        List<CompanyBean> resList = (ArrayList)entities.stream().collect(Collectors.toCollection(ArrayList::new));
        return resList;
    }

    //UPDATE
    public Company updateCompany(long id, Company company) throws ApplicationException {
        Company updated = this.companiesDao.getOne(id);
        //TODO ???????  -----> Where do i get the id from? <------ ???????
        updated.setAddress(company.getAddress());
        updated.setEmail(company.getEmail());
        updated.setPhone(company.getPhone());
        updated.setWebsite(company.getWebsite());
        return this.companiesDao.saveAndFlush(updated);
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

        if (company.getName().length() < 2) {
            throw new ApplicationException(ErrorType.COMPANY_NAME_SHORT, "Company name too short");

        }

        if (existsByName(company.getName())) {
            throw new ApplicationException(ErrorType.INVALID_COMPANY, "Company already exist");
        }
    }

    private boolean existsByName(String companyName) {
        Company c = new Company();
        c = companiesDao.findByName(companyName);
        if (c == null) {
            return false;
        }
        return true;
    }


    public boolean isExistById(long companyId) {
        return companiesDao.existsById(companyId);
    }
}
