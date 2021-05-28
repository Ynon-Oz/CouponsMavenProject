package com.ynon.coupons.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.ynon.coupons.beans.javabeans.CouponBean;
import com.ynon.coupons.dao.ICouponsDao;
import com.ynon.coupons.entities.Coupon;
import com.ynon.coupons.enums.ErrorType;
import com.ynon.coupons.exceptions.ApplicationException;

import javax.transaction.Transactional;


@Controller
public class CouponsController {

	@Autowired
	private ICouponsDao couponsDao;
	@Autowired
	private CompanysController companiesController;

	//DELETE
	public void deleteCoupon(Long couponId) throws ApplicationException {
		this.couponsDao.deleteById(couponId);
	}


	//UPDATE
	public Coupon updateCoupon(CouponBean couponBean, Long id)  throws ApplicationException{
		Coupon coupon = new Coupon();
		coupon = getCouponByCouponId(id);
		coupon.setTitle(couponBean.getTitle());
		coupon.setDescription(couponBean.getDescription());
		coupon.setAmount(couponBean.getAmount());
		coupon.setPrice(couponBean.getPrice());
		coupon.setImage(couponBean.getImage());
		coupon.setType(couponBean.getType());
		coupon.setStratDate(couponBean.getStratDate());
		coupon.setEndDate(couponBean.getEndDate());
		return this.couponsDao.saveAndFlush(coupon);
	}
	//GET
	public Coupon getCouponByCouponId (long couponId)  throws ApplicationException{
		return this.couponsDao.findById(couponId);
	}

	public List<Coupon> getByCompanyId (long companyId){
		return this.couponsDao.findByCompanyCompanyId(companyId);
	}



	public List<Coupon> getByCompanyIdAndMaxPrice(long companyId, float maxPrice){
		return this.couponsDao.findByCompanyIdAndMaxPrice(companyId, maxPrice);
	}

	public List<Coupon> getAllCoupons() throws ApplicationException{
		return (List<Coupon>) couponsDao.findAll();	
	}

	//CREATE
	public long addCoupon(CouponBean couponBean) throws ApplicationException {
		
		validations(couponBean);
		
		Coupon coupon = new Coupon();
		coupon.setTitle(couponBean.getTitle());
		coupon.setDescription(couponBean.getDescription());
		coupon.setAmount(couponBean.getAmount());
		coupon.setPrice(couponBean.getPrice());
		coupon.setImage(couponBean.getImage());
		coupon.setType(couponBean.getType());
		coupon.setStratDate(couponBean.getStratDate());
		coupon.setEndDate(couponBean.getEndDate());
		coupon.setCompany(companiesController.getCompanyFindById(couponBean.getCompanyId()));
		return this.couponsDao.save(coupon).getId();

	}

	// Validation
	private void validations(CouponBean couponBean) throws ApplicationException {
		
		if (couponBean == null) {
			throw new ApplicationException(ErrorType.NULL,"Missing coupon information");
		}
		
		if (couponBean.getPrice() <= 0) {
			throw new ApplicationException(ErrorType.COUPON_INVALID_PRICE,"Invalid coupon price");
		}
		if (couponBean.getAmount() < 1) {
			throw new ApplicationException(ErrorType.COUPON_AMOUNT_INVALID,"Invalid coupon amount");
		}
		List<Coupon> companyCoupons = new ArrayList<>();
		companyCoupons = getByCompanyId(couponBean.getCompanyId());
		//TODO WASTED! return company info for each coupon!!!!!!!
		for (Coupon c : companyCoupons) {
			if (c.getTitle().equalsIgnoreCase(couponBean.getTitle())) {
				throw new ApplicationException(ErrorType.COUPON_TITLE_IS_ALLREADY_EXISTS,"Coupon title is allready exists, choose a differente title");
			}
		}
	}
	
	//Purchase
	@Transactional
	public void purchaseCoupon(long couponId, int amount) {
		Coupon coupon = new Coupon();
		coupon = couponsDao.findById(couponId);
		coupon.setAmount(coupon.getAmount()-amount);
		couponsDao.save(coupon);

	}

	@Transactional
//	@Scheduled (fixedRate = )
	public void removeOldCoupons(Date date) throws ApplicationException {
		try {
			couponsDao.removeOldCoupons(date);
		} catch (Exception e) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, ErrorType.GENERAL_ERROR.getErrorMessage());
		}
	}








}
