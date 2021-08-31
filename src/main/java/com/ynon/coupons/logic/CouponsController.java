package com.ynon.coupons.logic;

import java.time.LocalDateTime;

import java.util.List;

import com.ynon.coupons.entities.Company;
import org.springframework.beans.factory.annotation.Autowired;

import com.ynon.coupons.beans.javabeans.CouponBean;
import com.ynon.coupons.dao.ICouponsDao;
import com.ynon.coupons.entities.Coupon;
import com.ynon.coupons.enums.ErrorType;
import com.ynon.coupons.exceptions.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
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
    public Coupon updateCoupon(CouponBean couponBean, Long id) throws ApplicationException {
        Coupon coupon = new Coupon();
        coupon = getCouponByCouponId(id);
        coupon.setTitle(couponBean.getTitle());
        coupon.setDescription(couponBean.getDescription());
        coupon.setAmount(couponBean.getAmount());
        coupon.setPrice(couponBean.getPrice());
        coupon.setImage(couponBean.getImage());
        coupon.setType(couponBean.getType());
        coupon.setStartDate(couponBean.getStartDate());
        coupon.setEndDate(couponBean.getEndDate());
        return this.couponsDao.saveAndFlush(coupon);
    }

    //GET
    public Coupon getCouponByCouponId(long couponId) throws ApplicationException {
        return this.couponsDao.findById(couponId);
    }

//    public List<Coupon> getByCompanyId(long companyId) {
//        return this.couponsDao.findByCompanyCompanyId(companyId);
//    }


    public List<Coupon> getByCompanyIdAndMaxPrice(long companyId, float maxPrice) {
        return this.couponsDao.findByCompanyIdAndMaxPrice(companyId, maxPrice);
    }

    public List<Coupon> getAllCoupons() throws ApplicationException {
        return (List<Coupon>) couponsDao.findAll();
    }

    //CREATE
    public ResponseEntity<?> addCoupon(CouponBean couponBean) throws ApplicationException {

        validations(couponBean);

        Coupon coupon = new Coupon();
        coupon.setTitle(couponBean.getTitle());
        coupon.setDescription(couponBean.getDescription());
        coupon.setAmount(couponBean.getAmount());
        coupon.setPrice(couponBean.getPrice());
        coupon.setImage(couponBean.getImage());
        coupon.setType(couponBean.getType());
        coupon.setStartDate(couponBean.getStartDate());
        coupon.setEndDate(couponBean.getEndDate());
        if (companiesController.isExistById(couponBean.getCompanyId())) {
            Company c = companiesController.getCompanyFindById(couponBean.getCompanyId());
            coupon.setCompany(c);
            System.out.println("company found: "+c);

        }else{
            System.out.println("No company found, id: "+couponBean.getCompanyId());
        }
        return new ResponseEntity<>(this.couponsDao.save(coupon), HttpStatus.CREATED);

    }

    // Validation
    private void validations(CouponBean couponBean) throws ApplicationException {

        if (couponBean == null) {
            throw new ApplicationException(ErrorType.NULL, "Missing coupon information");
        }

        if (couponBean.getPrice() <= 0) {
            throw new ApplicationException(ErrorType.COUPON_INVALID_PRICE, "Invalid coupon price");
        }
        if (couponBean.getAmount() < 1) {
            throw new ApplicationException(ErrorType.COUPON_AMOUNT_INVALID, "Invalid coupon amount");
        }
//        List<Coupon> companyCoupons = new ArrayList<>();
//        companyCoupons = getByCompanyId(couponBean.getCompanyId());
        //TODO WASTED! return company info for each coupon!!!!!!!
//        for (Coupon c : companyCoupons) {
//            if (c.getTitle().equalsIgnoreCase(couponBean.getTitle())) {
//                throw new ApplicationException(ErrorType.COUPON_TITLE_IS_ALREADY_EXISTS, "Coupon title is already exists, choose a different title");
//            }
//        }
    }

    //Purchase
    @Transactional
    public void purchaseCoupon(long couponId, int amount) {

        //TODO Create purchase more safe and generic
        /*
        Safe means: Avoid situation of purchasing unavailable amount, add option for payment process
        Generic means: Return ResponseEntity from repositories to services
     */

        Coupon coupon = new Coupon();
        coupon = couponsDao.findById(couponId);
        coupon.setAmount(coupon.getAmount() - amount);
        couponsDao.save(coupon);

    }

    @Transactional
    @Scheduled(fixedRate = 1000 * 60 * 60 * 24)

    public void removeOldCoupons() throws ApplicationException {
        try {
            couponsDao.removeOldCoupons(LocalDateTime.now());
        } catch (Exception e) {
            throw new ApplicationException(ErrorType.GENERAL_ERROR, ErrorType.GENERAL_ERROR.getErrorMessage());
        }
    }


}
