package com.ynon.coupons.utils;

import java.util.Date;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ynon.coupons.exceptions.ApplicationException;
import com.ynon.coupons.logic.CouponsController;



@Component
public class MyTimerTask extends TimerTask {
	@Autowired
	CouponsController couponsController ;


	@Override
	public void run() {
		Date date = new Date();
		try {
			couponsController.removeOldCoupons(date);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}


}
