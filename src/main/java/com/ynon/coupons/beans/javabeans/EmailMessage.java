package com.ynon.coupons.beans.javabeans;

import lombok.Data;

@Data
public class EmailMessage {

    private String fromName;
    private String toName;
    private String toEmail;
    private String subject;
    private String message;

}

