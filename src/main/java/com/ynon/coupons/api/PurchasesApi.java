package com.ynon.coupons.api;

import java.util.List;

import com.ynon.coupons.enums.CouponCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ynon.coupons.beans.javabeans.PurchaseBean;
import com.ynon.coupons.enums.CouponCategory;
import com.ynon.coupons.exceptions.ApplicationException;
import com.ynon.coupons.logic.PurchasesController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/purchase")
public class PurchasesApi {


	private final PurchasesController purchasesController;

	@PostMapping
	public void addPurchase(@RequestBody PurchaseBean purchase) throws ApplicationException {
		System.out.println(purchase);
		this.purchasesController.addPurchase(purchase);
	}


	@GetMapping("/{id}")
	public PurchaseBean getPurchase(@PathVariable("id") long id) throws ApplicationException{
		return this.purchasesController.getPurchase(id);
	}

	@GetMapping
	public List<PurchaseBean> getAllPurchases()throws ApplicationException{
		return this.purchasesController.getAllPurchases();
	}
	
	@GetMapping("/byCustomer")
	public List<PurchaseBean> getAllByCustomerId(@RequestParam("id")long id) throws ApplicationException{
		return this.purchasesController.getAllByCustomerId(id);
	}
	
	@GetMapping("/byCustomerAndType")
	public List<PurchaseBean> getAllByCustomerIdAndType(@RequestParam("customerId")long id, @RequestParam("category") CouponCategory type) throws ApplicationException{
		return this.purchasesController.getAllByCustomerIdAndType(id,type);
	}
	
	@GetMapping("/byCustomerAndPrice")
	public List<PurchaseBean> getAllByCustomerIdAndPrice(@RequestParam("id")long customerId, @RequestParam("price")float maxPrice) throws ApplicationException{
		return this.purchasesController.getAllByCustomerIdAndMaxPrice(customerId,maxPrice);
	}
	

	@DeleteMapping("/{id}")
	public void deletePurchase(@PathVariable("id")long id)throws ApplicationException {
		this.purchasesController.deletePurchasesById(id);
	}

}

/*
 *


	// http://localhost:8080/users/byAge?age=18&x=99
	@GetMapping("/byAge")
	public List<User> getUsersByMinimalAge(@RequestParam("age") int age,@RequestParam("x") int x){
		System.out.println(age + " " + x);
		return null;
	}


	@PostMapping("/batch")
	public void addUsersBatch(@RequestBody User user) {
		System.out.println(user);
	}
 */

