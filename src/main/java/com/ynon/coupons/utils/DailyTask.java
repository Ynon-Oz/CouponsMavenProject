package com.ynon.coupons.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Timer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DailyTask {

	@Autowired
	private MyTimerTask timerTask;
	@PostConstruct
	public void timerCreator() {
		Timer timer = new Timer();


		Long midnight = LocalDateTime.now().until(LocalDate.now().plusDays(1).atStartOfDay(), ChronoUnit.MINUTES);
		System.out.println(midnight);
		timer.schedule(timerTask, midnight,  1000*60*60*24);
	}

}
