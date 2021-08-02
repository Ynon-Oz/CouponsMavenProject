package com.ynon.coupons.cli;

import com.ynon.coupons.logic.CompanysController;
import com.ynon.coupons.services.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MockCreator implements CommandLineRunner {

@Autowired
    private CompanysController companysController;
@Autowired
    private FactoryService factoryService;

    @Override
    public void run(String... args) throws Exception {

    }
}
