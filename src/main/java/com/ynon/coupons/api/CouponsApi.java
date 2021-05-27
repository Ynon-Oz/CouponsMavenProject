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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ynon.coupons.beans.javabeans.CouponBean;
import com.ynon.coupons.entities.Coupon;
import com.ynon.coupons.exceptions.ApplicationException;
import com.ynon.coupons.logic.CouponsController;


@RestController
@RequestMapping("/coupons")
public class CouponsApi {

	@Autowired
	CouponsController couponsController;


	//CREATE
	@PostMapping
	public void addCoupon(@RequestBody CouponBean coupon) throws ApplicationException {
		this.couponsController.addCoupon(coupon);	
	}


	//UPDATE
	@PutMapping
	public void updateCoupon(@RequestBody CouponBean coupon) throws ApplicationException {
		this.couponsController.updateCoupon(coupon, coupon.getId());
	}


	//GET
	@GetMapping("/{id}")
	public Coupon getCoupon(@PathVariable("id") long id) throws ApplicationException {
		return this.couponsController.getCouponByCouponId(id);
	}

	//GET ALL
	@GetMapping
	public List<Coupon> getAllCoupons() throws ApplicationException{
		return this.couponsController.getAllCoupons();
	}

	//GET ALL BY COMPANT ID
	@GetMapping("/byCompany")
	public List<Coupon> getCouponsByCompanyId(@RequestParam("company") long comapnyId)throws ApplicationException{
		return this.couponsController.getByCompanyId(comapnyId);
	}

	//GET ALL BY MAX PRICE
	@GetMapping("/byPrice")
	public List<Coupon> getCouponsByMaxPrice(@RequestParam("company") long companyId,@RequestParam("price") float maxPrice){
		return this.couponsController.getByCompanyIdAndMaxPrice(companyId, maxPrice);
	}
	


	//DELETE
	@DeleteMapping("/{id}")
	public void deleteCoupon(@PathVariable("id")long id) throws ApplicationException {
		this.couponsController.deleteCoupon(id);
	}

}




