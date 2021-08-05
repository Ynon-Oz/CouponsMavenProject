package com.ynon.coupons.utils;

import java.time.LocalDateTime;
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
		LocalDateTime date =  LocalDateTime.now();
		try {
			couponsController.removeOldCoupons(date);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}


}
