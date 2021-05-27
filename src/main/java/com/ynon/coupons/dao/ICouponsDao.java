package com.ynon.coupons.dao;


import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ynon.coupons.beans.javabeans.CouponBean;
import com.ynon.coupons.entities.Coupon;

public interface ICouponsDao extends JpaRepository<Coupon, Long> {

	public Coupon findById(long id);
	
	public List<Coupon> findByCompanyCompanyId (long id);

	@Modifying
	@Transactional
	@Query("DELETE FROM Coupon c WHERE c.endDate < :date")
	public void removeOldCoupons(@Param("date")Date date);

	@Query("SELECT new com.ynon.coupons.beans.javabeans.CouponBean(c.id, c.company.companyId, c.type, c.title, c.description,  c.stratDate, c.endDate , c.amount, c.price,  c.image) FROM Coupon c WHERE c.company.companyId= :compId")
	public List<CouponBean> getCouponsByCompanyId(@Param("compId") long companyId);
	
	@Query("SELECT new com.ynon.coupons.entities.Coupon(c.id, c.company, c.type, c.title, c.description,  c.stratDate, c.endDate , c.amount, c.price,  c.image) FROM Coupon c WHERE c.company.companyId= :compId AND c.price<= :mxPrice")
	public List<Coupon> findByCompanyIdAndMaxPrice(@Param("compId")long companyId,@Param("mxPrice") float maxPrice);

	
}
