package com.ynon.coupons.dao;


import com.ynon.coupons.beans.SuccessfulLoginData;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CacheDao {

    private Map<String, SuccessfulLoginData> cacheMap;

    public CacheDao() {
        this.cacheMap = new HashMap();
//        this.DataMap.put(1l, "First");
//        this.DataMap.put(2l, "Second");
//        this.DataMap.put(3l, "Thired");
//        this.DataMap.put(4l, "Fourth");


    }

    public SuccessfulLoginData getData(String key) {
        return this.cacheMap.get(key);
    }

    public void put( SuccessfulLoginData successfulLoginData) {
        this.cacheMap.put(successfulLoginData.getToken(), successfulLoginData);

    }

    public void printCache(){
        System.out.println(cacheMap.toString());
    }
}
