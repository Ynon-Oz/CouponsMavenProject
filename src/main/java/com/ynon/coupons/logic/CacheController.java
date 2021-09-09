package com.ynon.coupons.logic;


import com.ynon.coupons.dao.CacheDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.ynon.coupons.beans.SuccessfulLoginData;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class CacheController {

    @Autowired
    private CacheDao map;

    public void put(SuccessfulLoginData successfulLoginData) {

        this.map.put(successfulLoginData);
        log.info("UserId: " + successfulLoginData.getUserId() + " have been add to cache");
    }

    public SuccessfulLoginData get(String key) {
        return this.map.getData(key);
    }
    public void printMap(){
        System.out.println("*********** CACHE *******");
        this.map.printCache();
    }
}
