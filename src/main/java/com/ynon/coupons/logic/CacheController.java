package com.ynon.coupons.logic;

import java.util.HashMap;
import java.util.Map;

import com.ynon.coupons.dao.CacheDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ynon.coupons.beans.SuccessfulLoginData;



@Component
public class CacheController {

	@Autowired
	private CacheDao map;


	public void put(SuccessfulLoginData successfulLoginData) {
		this.map.put(successfulLoginData.getToken(), successfulLoginData);
	} 

	public SuccessfulLoginData get(String key) {
		return this.map.getData(key);
	}
}
