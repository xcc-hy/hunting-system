package com.study.hunting.config;

import com.study.hunting.controller.CityController;
import com.study.hunting.controller.IndustryDetailController;
import com.study.hunting.controller.PositionDetailController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AutoRunner implements CommandLineRunner {

    @Autowired
    private CityController cityController;

    @Autowired
    private IndustryDetailController industryDetailController;

    @Autowired
    private PositionDetailController positionDetailController;

    @Override
    public void run(String... args) throws Exception {
        cityController.initCityInfo();
        industryDetailController.initIndustryInfo();
        positionDetailController.initPositionInfo();
    }
}
