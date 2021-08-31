package com.ynon.coupons.entities;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Entity
@Validated
@Table(name = "Purchases")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchaseID", nullable = false, unique = true)
    private long id;

    @JsonIgnore
    @JoinColumn(name = "userId", nullable = false, unique = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @JsonIgnore
    @JoinColumn(name = "couponId", nullable = false, unique = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Coupon coupon;

    @Positive
    @Column(name = "amount", nullable = false)
    private int amount;

    @Column(name = "timeStamp", nullable = false)
    private LocalDateTime timeStamp;


}
