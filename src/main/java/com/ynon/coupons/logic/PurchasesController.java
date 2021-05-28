package com.ynon.coupons.logic;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ynon.coupons.beans.javabeans.PurchaseBean;
import com.ynon.coupons.dao.IPurchaseDao;
import com.ynon.coupons.entities.Purchase;
import com.ynon.coupons.enums.CouponsCategory.CouponCategory;
import com.ynon.coupons.enums.ErrorType;
import com.ynon.coupons.exceptions.ApplicationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class PurchasesController {
    @Autowired
    private IPurchaseDao purchasesDao;
    @Autowired
    private CouponsController couponsController;
    @Autowired
    private CostumersController customersController;

    //CREATE /ADD
    public void addPurchase(PurchaseBean purchaseBaen) throws ApplicationException {
        Purchase purchase = new Purchase();
        purchase.setCoupon(this.couponsController.getCouponByCouponId(purchaseBaen.getCouponId()));
        purchase.setTimeStamp(purchaseBaen.getTimeStamp());
        purchase.setAmount(purchaseBaen.getAmount());
        purchase.setCustomer(this.customersController.getCustomer(purchaseBaen.getCostumerId()));

        validations(purchase);
        this.couponsController.purchaseCoupon(purchaseBaen.getCouponId(), purchaseBaen.getAmount());
        this.purchasesDao.save(purchase);
        //TODO Send purchace details by Email right after purchasing
    }

    // Validations
    private boolean validations(Purchase purchase) throws ApplicationException {
        if (purchase.getCoupon().getAmount() < purchase.getAmount()) {
            throw new ApplicationException(ErrorType.COUPON_AMOUNT_INVALID, ErrorType.COUPON_AMOUNT_INVALID.getErrorMessage());
        }
        Date today = new Date();
        if (purchase.getCoupon().getEndDate().before(today)) {
            throw new ApplicationException(ErrorType.PURCHASE_FAILED_COUPON_EXPIRED, " purchase process failed - coupon expired");
        }
        if (purchase.getCoupon().getStratDate().after(today)) {
            throw new ApplicationException(ErrorType.PURCHASE_FAILED_COUPON_EXPIRED, " purchase process failed - coupon is not valid yet");
        }
        List<PurchaseBean> customerPurchasesList = new ArrayList<PurchaseBean>();
        customerPurchasesList = getAllByCustomerId(purchase.getCustomer().getUser().getId());
        if (customerPurchasesList != null) {
            for (PurchaseBean p : customerPurchasesList) {
                if (p.getCouponId() == purchase.getCoupon().getId()) {
                    throw new ApplicationException(ErrorType.PURCHASE_FAILED_DOUBLE_PURCHASE, " purchase process failed - can not purchase the same coupon twice");
                }
            }
        }
        return true;
    }

    //GET/READ
    public PurchaseBean getPurchase(long id) throws ApplicationException {
        return this.purchasesDao.getPurchase(id);
    }

    public List<PurchaseBean> getAllByCustomerId(long id) {
        return this.purchasesDao.findByUserId(id);
    }

    public List<PurchaseBean> getAllByCustomerIdAndType(long id, CouponCategory type) {
        return this.purchasesDao.findByUserIdAndCategory(id, type);
    }


    public List<PurchaseBean> getAllByCustomerIdAndMaxPrice(long customerId, float maxPrice) {
        return this.purchasesDao.findByUserIdAndMaxPrice(customerId, maxPrice);
    }


    //DELETE
    public void deletePurchasesById(long id) throws ApplicationException {
        this.purchasesDao.deleteById(id);
    }

    public List<PurchaseBean> getAllPurchases() {
        return this.purchasesDao.getAllPurchases();
    }


}
