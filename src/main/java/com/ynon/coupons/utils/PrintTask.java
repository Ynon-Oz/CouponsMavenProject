package com.ynon.coupons.utils;

import com.ynon.coupons.logic.CacheController;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Ynon on  08/09/2021
 */
@Component
@RequiredArgsConstructor
public class PrintTask {

    private final CacheController cacheController;

//    @Scheduled(fixedRate = 1000 * 10)
    private void printCacheMap() {
        cacheController.printMap();
    }
}
