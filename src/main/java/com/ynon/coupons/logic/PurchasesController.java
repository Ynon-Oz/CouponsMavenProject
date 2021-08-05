package com.ynon.coupons.logic;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ynon.coupons.beans.javabeans.EmailMessage;
import com.ynon.coupons.config.Config;
import com.ynon.coupons.messages.EmailMessages;
import com.ynon.coupons.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;

import com.ynon.coupons.beans.javabeans.PurchaseBean;
import com.ynon.coupons.dao.IPurchaseDao;
import com.ynon.coupons.entities.Purchase;
import com.ynon.coupons.enums.CouponsCategory.CouponCategory;
import com.ynon.coupons.enums.ErrorType;
import com.ynon.coupons.exceptions.ApplicationException;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
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
@Autowired
private EmailService emailService;
    //CREATE /ADD
    @Transactional
    public void addPurchase(PurchaseBean purchaseBean) throws ApplicationException {
        Purchase purchase = new Purchase();
        purchase.setCoupon(this.couponsController.getCouponByCouponId(purchaseBean.getCouponId()));
        purchase.setTimeStamp(purchaseBean.getTimeStamp());
        purchase.setAmount(purchaseBean.getAmount());
        purchase.setCustomer(this.customersController.getCustomer(purchaseBean.getCostumerId()));
        Integer amount = new Integer(purchase.getAmount());
        float totalPrice = amount.floatValue() * purchase.getCoupon().getPrice();
        validations(purchase);
        this.couponsController.purchaseCoupon(purchaseBean.getCouponId(), purchaseBean.getAmount());
        this.purchasesDao.save(purchase);
        //TODO Send purchase details by Email right after purchasing
        EmailMessage PurchaseEmail = new EmailMessage();
        PurchaseEmail.setFromName(Config.ORGANIZATION_NAME+" Team");
        PurchaseEmail.setSubject(EmailMessages.SUBJECT_PURCHASE_CONFIRMATION);
        PurchaseEmail.setToEmail(purchase.getCustomer().getUser().getUserName());
        PurchaseEmail.setToName(purchase.getCustomer().getFirstName() + " " + purchase.getCustomer().getLastName());
        String message =
                String.format(
                        EmailMessages.PURCHASE_EMAIL_TEMPLATE,
                        purchase.getCustomer().getFirstName() + " " + purchase.getCustomer().getLastName(),
                        purchase.getCoupon().getTitle(),
                        purchase.getAmount(),
                        totalPrice,
                        purchase.getTimeStamp());

        PurchaseEmail.setMessage(message);
        emailService.sendEmail(PurchaseEmail);
    }

    // Validations
    private boolean validations(Purchase purchase) throws ApplicationException {
        if (purchase.getCoupon().getAmount() < purchase.getAmount()) {
            throw new ApplicationException(ErrorType.COUPON_AMOUNT_INVALID, ErrorType.COUPON_AMOUNT_INVALID.getErrorMessage());
        }
        LocalDateTime today =  LocalDateTime.now();
        if (purchase.getCoupon().getEndDate().isBefore(today)) {
            throw new ApplicationException(ErrorType.PURCHASE_FAILED_COUPON_EXPIRED, " purchase process failed - coupon expired");
        }
        if (purchase.getCoupon().getStartDate().isAfter(today)) {
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
