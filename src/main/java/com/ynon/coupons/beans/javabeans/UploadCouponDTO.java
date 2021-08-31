package com.ynon.coupons.beans.javabeans;

import com.ynon.coupons.enums.CouponCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
public class UploadCouponDTO {



        private long id;
        private long companyId;
        private CouponCategory type;
        private String title;
        private String description;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private int amount;
        private float price;
        private MultipartFile image;

    }
