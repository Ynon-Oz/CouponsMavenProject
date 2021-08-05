package com.ynon.coupons.services;

import com.ynon.coupons.entities.Company;
import com.ynon.coupons.entities.Coupon;
import com.ynon.coupons.enums.CouponsCategory;
import javafx.util.converter.LocalDateTimeStringConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.xml.ws.Response;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
public class FactoryService {


    //    private static int amountOfObjects = 40;
    public enum ObjType {
        COMPANY,
        COUPON,


    }

    public Response<?> createObject(ObjType type, int amount) {
        switch (type) {
            case COMPANY:
                return (Response<?>) companies(amount);
            case COUPON:
                return (Response<?>) coupons(amount);
        }
        return null;
    }

    public List<Coupon> coupons(int amountOfObjects) {
        List<Coupon> coupons = new ArrayList<>();
        for (int i = 0; i < amountOfObjects; i++) {
            coupons.add(coupon(i));
        }
//        coupons.forEach(c -> c.setCompanyWebSite(webAddGenerator(c.getCompanyName())));
        return coupons;
    }


    public List<Company> companies(int amountOfObjects) {
        List<Company> companies = new ArrayList<>();
        for (int i = 0; i < amountOfObjects; i++) {
            companies.add(company(i));
        }
        companies.forEach(c -> c.setCompanyWebSite(webAddGenerator(c.getCompanyName())));
        return companies;
    }

    private Company company(int i) {
        return Company.builder()
                .companyName(StringRandomValue(names, i))
                .companyAddress(StringRandomValue(addresses, null))
                .companyPhoneNumber(phoneFaxGenerator())
                .companyFaxNumber(phoneFaxGenerator())
//                .companyWebSite(webAddGenerator("A"))
                .coupons(coupons((int)(Math.random()*10)+1))
                .users(null)
                .build();
    }

    private static String phoneFaxGenerator() {
        StringBuilder a = new StringBuilder("0");
        for (int i = 0; i < 10; i++) {
            a = new StringBuilder(i == 2 ? (a.append('-')) : (a.append((int) (Math.random() * 9))));
        }
        return a.toString();
    }

    private static String webAddGenerator(String name) {

        return ("www." + name.toLowerCase().replaceAll("[^a-zA-Z0-9]", "") + ".com");
    }

    private static String StringRandomValue(String[] strArray, Integer i) {
        return i != null ? strArray[(int) (Math.random() * strArray.length)] + i : strArray[(int) (Math.random() * strArray.length)];
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


    private Coupon coupon(int i) {
        return Coupon.builder()
                .title(addCouponTitle())
                .description(addCouponDescription())
                .type(addCouponType())
                .amount((int) (Math.random() * 101)+10)
                .price((float) (Math.random() * 101)+10)
                .startDate(generateDate(0))
                .endDate(generateDate(1))
                .image("burger.jpg")
                .build();
    }

    private LocalDateTime generateDate(int i) {
        String str = "";
        switch (i) {
            case 0:
                str = "2021-01-01 12:30";
                break;
            default:
                str=
                        Integer.toString((int) ((Math.random()*2)+2021))+"-0"+
                        Integer.toString((int) (Math.random()*9)+1)+"-"+
                        Integer.toString((int) (Math.random()*20)+10)+" "+
                        Integer.toString((int) (Math.random()*13)+10)+":"+
                        Integer.toString((int) (Math.random()*49)+10)
                ;
        }
        return LocalDateTime.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

    }

    private CouponsCategory.CouponCategory addCouponType() {
        return CouponsCategory.CouponCategory.FOOD;
    }

    private String addCouponDescription() {
        String res = "burger";
        return res;
    }

    private String addCouponTitle() {
        String res = "burger";
        return res;
    }


}
