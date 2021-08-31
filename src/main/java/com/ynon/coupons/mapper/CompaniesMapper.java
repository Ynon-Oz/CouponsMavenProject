package com.ynon.coupons.mapper;

import com.ynon.coupons.beans.javabeans.CompanyBean;
import com.ynon.coupons.entities.Company;
import com.ynon.coupons.exceptions.ApplicationException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Ynon on  23/08/2021
 */
@Component

public class CompaniesMapper implements Mapper<Company,CompanyBean>{

    @Override
    public Company toDao(CompanyBean companyBean) throws ApplicationException {
        return null;
    }

    @Override
    public CompanyBean toDto(Company company) {
        return CompanyBean.builder()
                .id(company.getId())
                .name(company.getName())
                .email(company.getEmail())
                .address(company.getAddress())
                .webSite(company.getWebsite())
                .phone(company.getPhone())
                .build();
    }

    @Override
    public List<Company> toDaoList(List<CompanyBean> companyBeans) throws ApplicationException {
        return null;
    }

    @Override
    public List<CompanyBean> toDtoList(List<Company> companies) {
        return null;
    }
}
