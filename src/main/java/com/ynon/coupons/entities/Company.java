package com.ynon.coupons.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Companies")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Company {
    @Id
    @GeneratedValue
    @Column(name = "companyID")
    private long companyId;

    @Column(name = "Name", /*unique = true,*/ nullable = false)
    private String companyName;

    @Column(name = "Address", nullable = false)
    private String companyAddress;

    @Column(name = "PhoneNum", unique = true, nullable = false)
    private String companyPhoneNumber;

    @Column(name = "FaxNum", unique = true)
    private String companyFaxNumber;

    @Column(name = "webSite", unique = true)
    private String companyWebSite;

    @JsonIgnore
    @OneToMany(mappedBy = "company", cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<Coupon> coupons;

    @JsonIgnore
    @OneToMany(mappedBy = "company", cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<User> users;


    public Company(String name, String address, String phone, String fax, String web) {
        super();
        this.companyName = name;
        this.companyAddress = address;
        this.companyPhoneNumber = phone;
        this.companyFaxNumber = fax;
        this.companyWebSite = web;
    }
}

