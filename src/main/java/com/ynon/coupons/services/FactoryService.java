package com.ynon.coupons.services;

import com.ynon.coupons.entities.Company;
import com.ynon.coupons.entities.Coupon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
public class FactoryService {


    private static int amountOfObjects = 10;


    public List<Company> companies() {
        List<Company> companies = new ArrayList<>();
        for (int i = 0; i < amountOfObjects; i++) {
            companies.add(company());
        }
        return companies;
    }

    private Company company() {
        return Company.builder()
                .companyName(strValue(names))
                .companyAddress(strValue(addresses))
                .companyPhoneNumber(phoneFaxGenerator())
                .companyFaxNumber(phoneFaxGenerator())
                .companyWebSite(webAddGenerator(company().getCompanyName()))
                .coupons(null)
                .users(null)
                .build();
    }

    private static String phoneFaxGenerator() {

        return "077-7777777";
    }

    private static String webAddGenerator(String name) {
        return ("www." + name.toLowerCase().trim() + ".com");
    }

    private static String strValue(String[] strArray) {
        String result = strArray[(int) Math.random() * strArray.length];
        return result;
    }

    private static final String[] addresses = {
            "43 Steel st Ashdod",
            "2 Ainstein st Rehovot",
            "67 Shalom st Tel-Aviv",
            "78 Ela st Haifa",
            "26 Kalanit st Rishon",
            "13 Yefet st Ramat Hasharon",
            "59 Tamuz st Beer Sheva",
            "333 Bar-Kochva st Afula",
            "12 1230 st Tel-Aviv",
            "8 Brurya st Ramat-Gan",
            "190 Dizenguff Arad",
            "589 Arlozerov ev Netanya",
            "90 Bruklin rd Eilat",
    };

    private static final String[] names = {
            "Toys 'R' Us Inc",
            "Apple",
            "COSCO",
            "Lockheed Martin",
            "Boeing",
            "Zara",
            "Castro",
            "Nike",
            "Mc Donald's",
            "Local Zoo",
            "AquaSports",
            "LEGO",
            "SuperMarkets Group",
            "Cinematic",

    };


    private Coupon coupon() {
//        Date date = new Date();
        return Coupon.builder()
                .amount((int) Math.random() * 101)
                .price((float) Math.random() * 101)
//                .startDate(new Date())

                .build();
    }

}
