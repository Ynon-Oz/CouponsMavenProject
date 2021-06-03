package com.ynon.coupons;


import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

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
import com.ynon.coupons.enums.CouponsCategory.CouponCategory;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@SpringBootApplication
@EnableScheduling
public class Main {

	public static void main(String[] args) throws ApplicationException {
		ApplicationContext ctx = SpringApplication.run(Main.class, args);

//		======================================
//				MOCKING
//		======================================
		log.info("Starting mocking process...");
		ICompaniesDao companiesDao = ctx.getBean(ICompaniesDao.class);
		UsersController usersController = ctx.getBean(UsersController.class);
		// Creating  Companies
		log.info("Creating companies");
		Company comp1 = new Company("Coca Cola","32 Akiva st Bney Brak","03-6432166","03-6432100","www.coca-cola.co.il",null,null);
		Company comp2 = new Company("ZARA","57 Haruv st Herzlya","09-9517654","09-9517653","www.zara.co.il",null,null);

		Date startDate = new Date(120, 0, 1); //(years since 1900, month 0-11, day 1-31)
		Date endDate = new Date(122, 11, 31);
		// Creating Coupons
		log.info("Creating coupons");
		Coupon c1 = new Coupon(comp1,CouponCategory.FOOD,"Manceasss","Fistukim",startDate,endDate,150,9.9f,"fistuk.jpg");
		Coupon c2 = new Coupon(comp1,CouponCategory.FOOD,"Burger","American burger with cheddar",startDate,endDate,100,49.5f,"burger.jpg");
		Coupon c3 = new Coupon(comp1,CouponCategory.FOOD,"Hot Dog","Chicken HOT DOG",startDate,endDate,200,25f,"hotdog.jpg");
		Coupon c4 = new Coupon(comp1,CouponCategory.VEHICLE,"Air Craft","F-35 Aircraft INCLUDES 8 ROCKETS FREE!",startDate,endDate,2,9999999.9f,"f35.jpg");
		Coupon c5 = new Coupon(comp2,CouponCategory.FASHION,"Men Clothes","Blue T-Shirt with pocket",startDate,endDate,300,60.5f,"shirt.jpg");
		Coupon c6 = new Coupon(comp2,CouponCategory.SPA,"SPA & Massage","60 Minutes Sweden Massage",startDate,endDate,50,150f,"spa.jpg");

		List<Coupon> coupons1 = new ArrayList<Coupon>();
		List<Coupon> coupons2 = new ArrayList<Coupon>();

		coupons1.add(c1);
		coupons1.add(c2);
		coupons1.add(c3);
		coupons1.add(c4);
		coupons2.add(c5);
		coupons2.add(c6);
		log.info("Adding coupons to companies");
		comp1.setCoupons(coupons1);
		comp2.setCoupons(coupons2);
		// Creating Users
		log.info("Creating users");
		User u1 = new User("admin@admin.com", "123456", null, UserType.ADMIN,true);
		User u2 = new User("company@company.com", "123456", comp1, UserType.COMPANY,true);
		// * Customer will register
		u2.setPassword(usersController.obfuscation(u2.getPassword()));
		List<User> users = new ArrayList<User>();
		users.add(u2);
		comp1.setUsers(users);
		
		// Add mock to DB
		log.info("Adding mock to DB");
		usersController.addUser(u1);
		companiesDao.save(comp1);
		companiesDao.save(comp2);

		log.info("***** Loading completed *****");


	}

}
