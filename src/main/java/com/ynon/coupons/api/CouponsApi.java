package com.ynon.coupons.api;

import java.io.IOException;
import java.util.List;

import com.ynon.coupons.beans.javabeans.UploadCouponDTO;
import com.ynon.coupons.logic.ImagesController;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ynon.coupons.beans.javabeans.CouponBean;
import com.ynon.coupons.entities.Coupon;
import com.ynon.coupons.exceptions.ApplicationException;
import com.ynon.coupons.logic.CouponsController;


@RestController
@RequestMapping("/coupon")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")

public class CouponsApi {

	private final CouponsController couponsController;
	private final ImagesController imageService;

	//CREATE
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> addCoupon(@RequestBody CouponBean payload) throws ApplicationException {
		return this.couponsController.addCoupon(payload);
	}

//	@PostMapping(
//			consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
//			produces = {MediaType.APPLICATION_JSON_VALUE}
//	)
//	@ResponseStatus(HttpStatus.CREATED)
//	public  @ResponseBody CouponBean addCoupon(@ModelAttribute UploadCouponDTO payload) throws ApplicationException, IOException {
//	void addCoupon(@RequestBody CouponBean coupon) throws ApplicationException {
//		return null;//this.couponsController.addCoupon(payload);
//	}


	//UPDATE
	@PutMapping("/{id}")
	public void updateCoupon(@PathVariable long id, @RequestBody CouponBean coupon) throws ApplicationException {
		this.couponsController.updateCoupon(coupon, id);
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
//	@GetMapping("/byCompany")
//	public List<Coupon> getCouponsByCompanyId(@RequestParam("company") long comapnyId)throws ApplicationException{
//		return this.couponsController.getByCompanyId(comapnyId);
//	}

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




