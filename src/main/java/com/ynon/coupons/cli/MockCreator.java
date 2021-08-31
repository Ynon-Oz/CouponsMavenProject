package com.ynon.coupons.cli;

import com.ynon.coupons.dao.ICompaniesDao;
import com.ynon.coupons.entities.Company;
import com.ynon.coupons.entities.Coupon;
import com.ynon.coupons.entities.User;
import com.ynon.coupons.enums.CouponCategory;
import com.ynon.coupons.enums.UserType;
import com.ynon.coupons.logic.CompanysController;
import com.ynon.coupons.logic.UsersController;
import com.ynon.coupons.services.FactoryService;
import com.ynon.coupons.utils.ArtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class MockCreator implements CommandLineRunner {

    @Autowired
    private CompanysController companysController;
    @Autowired
    private FactoryService factoryService;
    @Autowired
    private ICompaniesDao companiesDao;
    @Autowired
    private UsersController usersController;

    @Override
    public void run(String... args) throws Exception {
        //		======================================
//				MOCKING
//		======================================
        log.info(ArtUtils.StartingMockProcess);
        // Creating  Companies
        log.info(ArtUtils.createCompanies);
        Company comp1 = new Company("Coca Cola", "32 Akiva st Bney Brak", "03-6432166", "03-6432100", "www.coca-cola.co.il");
        Company comp2 = new Company("ZARA", "57 Haruv st Herzlya", "09-9517654", "09-9517653", "www.zara.co.il");
        Company comp3 = new Company("ZARA", "57 Haruv st Herzlya", "09-9517654", "09-9517653", "www.zara.co.il");

//        String str1 = "2021-01-01 12:30";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startDate = LocalDateTime.now();
//        String str2 = "2021-01-01 12:30";
//        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime endDate = LocalDateTime.now().plusDays(365);
        // Creating Coupons
        log.info(ArtUtils.createCoupons);
        Coupon c1 = new Coupon(comp1, CouponCategory.FOOD, "Manceasss", "Fistukim", startDate, endDate, 150, 9.9f, "Fistukim.jpg");
        Coupon c2 = new Coupon(comp1, CouponCategory.FOOD, "Burger", "American burger with cheddar", startDate, endDate, 100, 49.5f, "burger.jpg");
        Coupon c3 = new Coupon(comp1, CouponCategory.FOOD, "Hot Dog", "Chicken HOT DOG", startDate, endDate, 200, 25f, "hotdog.jpg");
        Coupon c4 = new Coupon(comp1, CouponCategory.VEHICLE, "Air Craft", "F-35 Aircraft INCLUDES 8 ROCKETS FREE!", startDate, endDate, 2, 9999999.9f, "f35.jpg");
        Coupon c5 = new Coupon(comp2, CouponCategory.FASHION, "Men Clothes", "Blue T-Shirt with pocket", startDate, endDate, 300, 60.5f, "tshirt.jpg");
        Coupon c6 = new Coupon(comp2, CouponCategory.SPA, "SPA & Massage", "60 Minutes Sweden Massage", startDate, endDate, 50, 150f, "spa.jpg");

        List<Coupon> coupons1 = new ArrayList<Coupon>();
        List<Coupon> coupons2 = new ArrayList<Coupon>();

        coupons1.add(c1);
        coupons1.add(c2);
        coupons1.add(c3);
        coupons1.add(c4);
        coupons2.add(c5);
        coupons2.add(c6);
        log.info(ArtUtils.addCouponsToCompanies);
        comp1.setCoupons(coupons1);
        comp2.setCoupons(coupons2);
        // Creating Users
        log.info(ArtUtils.createUsers);
        User u1 = new User("admin@admin.com", "123456", null, UserType.ADMIN, true);
        User u2 = new User("company@company.com", "123456", comp1, UserType.COMPANY, true);
        // * Customer will register
        u2.setPassword(usersController.obfuscation(u2.getPassword()));
        List<User> users = new ArrayList<User>();
        users.add(u2);
        comp1.setUsers(users);
        // Add mock to DB
        log.info(ArtUtils.addMock);
        usersController.addUser(u1);
        companiesDao.save(comp1);
        companiesDao.save(comp2);

        //ADD MORE RANDOM COMPANIES AND COUPONS
        companiesDao.saveAll(factoryService.companies(40));
    }
}
