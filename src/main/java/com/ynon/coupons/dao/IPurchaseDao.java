package com.ynon.coupons.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ynon.coupons.beans.javabeans.PurchaseBean;
import com.ynon.coupons.entities.Purchase;
import com.ynon.coupons.enums.CouponsCategory.CouponCategory;
import org.springframework.stereotype.Repository;

@Repository
public interface IPurchaseDao extends JpaRepository<Purchase, Long> {


	@Query("SELECT new com.ynon.coupons.beans.javabeans.PurchaseBean(p.id, u.id, c.id, p.amount, p.timeStamp) FROM Purchase p LEFT JOIN p.customer u LEFT JOIN p.coupon c WHERE p.id= :purchaseId")
	PurchaseBean getPurchase(@Param("purchaseId")long purchaseId);

	@Query("SELECT new com.ynon.coupons.beans.javabeans.PurchaseBean(p.id, u.id, c.id, p.amount, p.timeStamp) FROM Purchase p LEFT JOIN p.customer u LEFT JOIN p.coupon c")
	List<PurchaseBean> getAllPurchases();

	@Query("SELECT new com.ynon.coupons.beans.javabeans.PurchaseBean(p.id, u.id, c.id, p.amount, p.timeStamp) FROM Purchase p LEFT JOIN p.customer u LEFT JOIN p.coupon c WHERE u.id= :customerId")
	List<PurchaseBean> findByUserId(long customerId);

	@Query("SELECT new com.ynon.coupons.beans.javabeans.PurchaseBean(p.id, u.id, c.id, p.amount, p.timeStamp) FROM Purchase p LEFT JOIN p.customer u LEFT JOIN p.coupon c WHERE u.id= :customerId AND c.type= :category")
	List<PurchaseBean> findByUserIdAndCategory(long customerId, CouponCategory category);

	@Query("SELECT new com.ynon.coupons.beans.javabeans.PurchaseBean(p.id, u.id, c.id, p.amount, p.timeStamp) FROM Purchase p LEFT JOIN p.customer u LEFT JOIN p.coupon c WHERE u.id= :customerId AND c.price<= :maxPrice")
	List<PurchaseBean> findByUserIdAndMaxPrice(long customerId, float maxPrice);

}
