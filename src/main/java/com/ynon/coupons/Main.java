package com.ynon.coupons;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.ynon.coupons.services.FactoryService;
import com.ynon.coupons.utils.ArtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.ynon.coupons.dao.ICompaniesDao;
import com.ynon.coupons.entities.Company;
import com.ynon.coupons.entities.Coupon;
import com.ynon.coupons.entities.User;
import com.ynon.coupons.enums.UserType;
import com.ynon.coupons.exceptions.ApplicationException;
import com.ynon.coupons.logic.UsersController;
import com.ynon.coupons.enums.CouponCategory;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@SpringBootApplication
@EnableScheduling
public class Main {

    public static void main(String[] args) throws ApplicationException {
        SpringApplication.run(Main.class, args);
        log.info(ArtUtils.running);



    }



}
