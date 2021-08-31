package com.ynon.coupons.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ynon.coupons.beans.javabeans.UserBean;
import com.ynon.coupons.entities.User;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsersDao extends JpaRepository<User, Long> {

    public User findByEmail(String name);

    public List<User> findByCompanyOrderByEmail(long companyId);

    public List<User> findByCompany(String companyName);

    @Query("SELECT new com.ynon.coupons.beans.javabeans.UserBean(u.id, u.email, u.password, c.id, u.type) FROM User u LEFT JOIN u.company c WHERE u.id=  :userId")
    public UserBean getUserByUserId(@Param("userId") long userId);

    @Query("SELECT new com.ynon.coupons.beans.javabeans.UserBean(u.id, u.email, u.password, c.id, u.type) FROM User u LEFT JOIN u.company c")
    public List<UserBean> getAllUsers();




}
