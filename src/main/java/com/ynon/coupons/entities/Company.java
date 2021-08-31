package com.ynon.coupons.entities;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Entity
@Table(name = "Companies")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Company {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "companyID")
    private long id;

    @Column(name = "Name", /*unique = true,*/ nullable = false, length = 30)
    private String name;

    @Column(name = "Address", nullable = false, length = 30)
    private String address;

    @Column(name = "PhoneNum", unique = true, nullable = false, length = 30)
    private String phone;

    @Column(name = "Email", unique = true, length = 40)
    private String email;

    @Column(name = "webSite", unique = true, length = 30)
    private String website;
    @Singular
    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "company", cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<Coupon> coupons;
    @ToString.Exclude
    @Singular
    @JsonIgnore
    @OneToMany(mappedBy = "company", cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<User> users;


    public Company(String name, String address, String phone, String fax, String web) {
        super();
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = fax;
        this.website = web;
    }
}

